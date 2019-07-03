package com.jumia.pay.assessment.service;


import com.jumia.pay.assessment.dto.UserDTO;
import com.jumia.pay.assessment.entity.Audit;
import com.jumia.pay.assessment.entity.User;
import com.jumia.pay.assessment.enums.ActionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    @Qualifier("customPasswordEncoder")
    private PasswordEncoder customPasswordEncoder;

    @Autowired
    private AuditService auditService;

    @Autowired
    private Util util;

    private Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    public String createUser (UserDTO userDTO, HttpServletRequest servletRequest) {

        try {
            User user = userDTO.retrieveEntity();
            User existingUser = getExistingUser(user.getUsername());

            if (existingUser != null)
                return String.format("User with username %s already exist", user.getUsername());

            user.setPassword(customPasswordEncoder.encode(user.getPassword()));
            user.setDateCreated(new Date());
            user = mongoTemplate.insert(user);

            logger.info(String.format("User successfully saved with object id %s ", user.getId()));
            preareAndSendAuditDataToQueue(null, user, servletRequest);
        } catch (Exception e) {
            logger.error("Error occurred while creating user due to ", e);
            return "Error occurred while creating user... Please try later or contact Admin";
        }

        return "User successfully registered";
    }

    public User getExistingUser(String username) {
        return mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class);
    }

    public void preareAndSendAuditDataToQueue (User initialData, User finalData, HttpServletRequest servletRequest) {
        Audit audit = util.prepareAuditObject(finalData.getUsername(), ActionType.USER_REGISTRATION.toString(), "Customer purchased goods", initialData, finalData, servletRequest);
        auditService.sendToQueue(audit);
    }

}
