package com.jumia.pay.assessment.service;

import com.jumia.pay.assessment.entity.Audit;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class Util {

    public String getIpAddress(HttpServletRequest servletRequest) {
        if (servletRequest == null)
            return "";

        return servletRequest.getRemoteAddr();
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
