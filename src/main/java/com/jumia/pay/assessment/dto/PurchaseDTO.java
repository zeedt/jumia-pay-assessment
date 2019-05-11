package com.jumia.pay.assessment.dto;

import com.jumia.pay.assessment.entity.Purchase;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PurchaseDTO {

    @NotBlank(message = "Buyer cannot be blank")
    @Size(min = 3, message = "Buyer username cannot be less than 3 characters")
    private String buyer;

    @NotBlank(message = "Product name cannot be blank")
    private String productName;

    @NotBlank(message = "Pan cannot be blank")
    @Size(min = 16, max = 16, message = "Length of pan must be 16 characters")
    private String pan;

    @NotBlank(message = "Pan cannot be blank")
    @Size(min = 3, max = 3, message = "Length of cvc must be 3 characters")
    private String cvc;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 100, message = "Minimum amount value is 100 naira ")
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

    public Purchase retrieveEntity() {
        Purchase purchase = new Purchase();
        purchase.setCvc(this.cvc);
        purchase.setPan(this.pan);
        purchase.setAmount(this.amount);
        purchase.setBuyer(this.buyer);
        purchase.setProductName(this.productName);

        return purchase;
    }
}
