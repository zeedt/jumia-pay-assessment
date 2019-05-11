package com.jumia.pay.assessment.service;

import com.jumia.pay.assessment.entity.Audit;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class Util {

    public String getIpAddress(HttpServletRequest servletRequest) {

        if (servletRequest == null)
            return "";

        String xForwardHeader = servletRequest.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(xForwardHeader)) {
            return servletRequest.getRemoteAddr();
        }
        return xForwardHeader.split(",")[0];
    }

    public Audit prepareAuditObject(String actor, String actionType, String actionDescription, Object initialData, Object finalData, HttpServletRequest servletRequest) {

        Audit audit = new Audit();
        audit.setIpAddress(getIpAddress(servletRequest));
        audit.setInitialData(initialData);
        audit.setFinalData(finalData);
        audit.setActionType(actionType);
        audit.setActionDescription(actionDescription);
        audit.setActor(actor);
        audit.setDatePerformed(new Date());

        return audit;
    }

}
