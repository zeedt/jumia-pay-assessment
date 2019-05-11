package com.jumia.pay.assessment.service;

import com.jumia.pay.assessment.dto.PurchaseDTO;
import com.jumia.pay.assessment.entity.Audit;
import com.jumia.pay.assessment.entity.Purchase;
import com.jumia.pay.assessment.enums.ActionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private Util util;

    private Logger logger = LoggerFactory.getLogger(PurchaseService.class.getName());

    private static final String PAN_STAR = "******";

    private static final String CVC_STAR = "***";

    @Autowired
    private MongoTemplate mongoTemplate;

    public String purchaseGoods(PurchaseDTO purchaseDto, HttpServletRequest servletRequest) {

        try {

            Purchase purchase = purchaseDto.retrieveEntity();

            if (userService.getExistingUser(purchase.getBuyer()) == null)
                return "Buyer's profile not found";

            debitCustomer(purchase);

            purchase.setPurchaseDate(new Date());
            purchase.setPan(maskPan(purchase.getPan()));
            purchase.setCvc(CVC_STAR);
            purchase = mongoTemplate.insert(purchase);
            logger.info(String.format("Purchase saved with id %s", purchase.getId()));
            preareAndSendAuditDataToQueue(null, purchase, servletRequest);

        } catch (Exception e) {
            logger.error("Error occurred while purchasing goods due to ", e);
            return "Purchase request no complete. Please try later or contact Admin";
        }

        return "Good successfully purchased";

    }

    private void preareAndSendAuditDataToQueue (Purchase initialData, Purchase finaldata, HttpServletRequest servletRequest) {
        Audit audit = util.prepareAuditObject(finaldata.getBuyer(), ActionType.PURCHASE.toString(), "Customer purchased goods", initialData, finaldata, servletRequest);
        auditService.sendToQueue(audit);
    }


    private String maskPan(String fullPan) {
        return fullPan.replace(fullPan.substring(6,12), PAN_STAR );
    }

    /**
     * This methos should make call to external party to debit customer account based on supplied information
     */
    private void debitCustomer(Purchase purchase) {
        // Do something
    }

}
