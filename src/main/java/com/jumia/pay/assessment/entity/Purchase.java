package com.jumia.pay.assessment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Date;

@Document(collection = "purchase")
public class Purchase implements Serializable {

    @JsonIgnore
    private String id;

    private String buyer;

    private String productName;

    private String pan;

    private String cvc;

    private double amount;

    private Date purchaseDate;

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"buyer\":\"" + buyer + '\"' +
                ", \"productName\":\"" + productName + '\"' +
                ", \"pan\":\"" + pan + '\"' +
                ", \"cvc\":\"" + cvc + '\"' +
                ", \"amount\":" + amount +
                ", \"purchaseDate\":\"" + purchaseDate + '\"' +
                '}';
    }
}
