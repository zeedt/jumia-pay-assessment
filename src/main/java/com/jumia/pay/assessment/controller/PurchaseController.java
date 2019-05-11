package com.jumia.pay.assessment.controller;

import com.jumia.pay.assessment.dto.PurchaseDTO;
import com.jumia.pay.assessment.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public String purchaseGoods (@Validated @RequestBody PurchaseDTO purchaseDTO, HttpServletRequest servletRequest) {
        return purchaseService.purchaseGoods(purchaseDTO, servletRequest);
    }

}
