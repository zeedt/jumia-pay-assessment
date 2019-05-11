package com.jumia.pay.assessment.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.pay.assessment.dto.AuditDTO;
import com.jumia.pay.assessment.entity.Audit;
import com.jumia.pay.assessment.enums.AuditFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AuditService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Logger logger = LoggerFactory.getLogger(AuditService.class.getName());

    private ObjectMapper objectMapper = new ObjectMapper();

    private ExecutorService executorService = Executors.newFixedThreadPool(50);

    @Autowired
    private JmsTemplate jmsTemplate;

    private static final String QUEUE_NAME = "jumia-pay.inbound.queue";

    @PostConstruct
    public void init() {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public String persistAudit(Message jsonMessage) throws IOException {

        logger.info(String.format("Payload received ==> %s ", jsonMessage.getPayload().toString()));

        Audit audit = objectMapper.readValue(jsonMessage.getPayload().toString(), Audit.class);
        audit = mongoTemplate.insert(audit);

        logger.info(String.format("Audit logged successfully with collection id %s", audit.getId()));

        return "Audit persisted successfully";
    }


    public Page<Audit> searchAndFilterAudit(AuditDTO auditDTO) {

        Pageable pageable = new PageRequest(auditDTO.getPageNo(), auditDTO.getPageSize(), auditDTO.getSortOrder(), auditDTO.getAuditFilter().toString());

        Query query = new Query().with(pageable);

        if (!StringUtils.isEmpty(auditDTO.getIpAddress())) {
            query.addCriteria(Criteria.where(AuditFilter.ipAddress.toString()).regex(auditDTO.getIpAddress(), "i"));
        }

        if (!StringUtils.isEmpty(auditDTO.getActor())) {
            query.addCriteria(Criteria.where(AuditFilter.actor.toString()).regex(String.format(".*%s.*",auditDTO.getActor()), "i"));
        }

        if (!StringUtils.isEmpty(auditDTO.getActionDescription())) {
            query.addCriteria(Criteria.where(AuditFilter.actionDescription.toString()).regex(String.format(".*%s.*",auditDTO.getActionDescription())));
        }

        if (!StringUtils.isEmpty(auditDTO.getActionType())) {
            query.addCriteria(Criteria.where(AuditFilter.actionType.toString()).regex(auditDTO.getActionType(), "i"));
        }

        if (auditDTO.getFromDate() != null && auditDTO.getToDate() != null) {
            query.addCriteria(Criteria.where(AuditFilter.datePerformed.toString()).lte(auditDTO.getToDate()).
                    andOperator(Criteria.where(AuditFilter.datePerformed.toString()).gte(auditDTO.getFromDate())));
        }

        return PageableExecutionUtils.getPage(mongoTemplate.find(query, Audit.class), pageable,
                () -> mongoTemplate.count(query, Audit.class));

    }

    public void sendToQueue(Audit audit) {
        executorService.submit( () ->
                jmsTemplate.convertAndSend(QUEUE_NAME, audit.toString())
        );
    }

}
