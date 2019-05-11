package com.jumia.pay.assessment.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jumia.pay.assessment.service.CustomDateDeserializer;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "audit")
public class Audit {

    @JsonIgnore
    private String id;

    private String actor;

    private String actionType;

    private String actionDescription;

    private Object initialData;

    private Object finalData;

    private String ipAddress;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date datePerformed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public Object getInitialData() {
        return initialData;
    }

    public void setInitialData(Object initialData) {
        this.initialData = initialData;
    }

    public Object getFinalData() {
        return finalData;
    }

    public void setFinalData(Object finalData) {
        this.finalData = finalData;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getDatePerformed() {
        return datePerformed;
    }

    public void setDatePerformed(Date datePerformed) {
        this.datePerformed = datePerformed;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"actor\":\"" + actor + '\"' +
                ", \"actionType\":\"" + actionType + '\"' +
                ", \"actionDescription\":\"" + actionDescription + '\"' +
                ", \"initialData\":" + initialData +
                ", \"finalData\":" + finalData +
                ", \"ipAddress\":\"" + ipAddress + '\"' +
                ", \"datePerformed\":\"" + datePerformed + '\"' +
                '}';
    }
}
