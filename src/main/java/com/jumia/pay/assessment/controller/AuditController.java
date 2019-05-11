package com.jumia.pay.assessment.controller;


import com.jumia.pay.assessment.dto.AuditDTO;
import com.jumia.pay.assessment.entity.Audit;
import com.jumia.pay.assessment.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @PostMapping
    public Page<Audit> findAudit(@RequestBody AuditDTO auditDTO) {
        return auditService.searchAndFilterAudit(auditDTO);
    }

}
