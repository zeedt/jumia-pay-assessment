package com.jumia.pay.assessment.dto;


import com.jumia.pay.assessment.enums.SortField;
import org.springframework.data.domain.Sort;

import java.util.Date;

public class AuditDTO {

    private String ipAddress;

    private String actor;

    private String actionType;

    private String actionDescription;

    private Date fromDate;

    private Date toDate;

    private Integer pageNo = 0;

    private Integer pageSize = 10;

    private SortField sortField = SortField.datePerformed;

    private Sort.Direction sortOrder = Sort.Direction.ASC;


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public SortField getSortField() {
        return sortField;
    }

    public void setSortField(SortField sortField) {
        this.sortField = sortField;
    }

    public Sort.Direction getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Sort.Direction sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
