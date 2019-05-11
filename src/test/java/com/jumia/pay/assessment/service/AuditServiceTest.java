package com.jumia.pay.assessment.service;

import com.jumia.pay.assessment.dto.AuditDTO;
import com.jumia.pay.assessment.entity.Audit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AuditServiceTest {

    @Mock
    private AuditService auditService;

    @Mock
    private org.springframework.messaging.Message message;

    private AuditDTO auditDTO = new AuditDTO();

    @Before
    public void setUpSearch() throws Exception {

        MockitoAnnotations.initMocks(this);

        List<Audit> audits = new ArrayList<>();
        for (int i=0;i<5;i++) {
            Audit audit = new Audit();
            audit.setIpAddress(String.format("172.32.12.12%d",i));
            audit.setActionDescription(String.format("Operation %d", i));
            audit.setDatePerformed(new Date());
            audit.setActionType(String.format("Action_Type_%s", i));
            audit.setFinalData("{\"id\":\"5cd6eea8af28d10c5dd678b6\", \"buyer\":\"olamide\", \"productName\":\"Bag\", \"pan\":\"223432******4322\", \"cvc\":\"***\", \"amount\":200.0, \"purchaseDate\":\"Sat May 11 16:47:52 WAT 2019\"}");
            audit.setInitialData("{\"id\":\"5cd6eea8af28d10c5dd678b6\", \"buyer\":\"olamide\", \"productName\":\"Bag\", \"pan\":\"223432******4322\", \"cvc\":\"***\", \"amount\":200.0, \"purchaseDate\":\"Sat May 11 16:47:52 WAT 2019\"}");
            audit.setId(String.format("Object____%d", i));
            audits.add(audit);
        }

        Pageable pageable = new PageRequest(0,10, Sort.Direction.DESC, "id");

        Page<Audit> page = new PageImpl<Audit>(audits, pageable, 10);


        Mockito.when(auditService.searchAndFilterAudit(auditDTO))
                .thenReturn(page);

        Mockito.when(auditService.persistAudit(any(org.springframework.messaging.Message.class)))
                .thenReturn("Audit persisted successfully");

    }


    @Test
    public void persistAudit() throws IOException {

        String persistenceResponse = auditService.persistAudit(message);
        assertEquals("Persistence must be successful","Audit persisted successfully", persistenceResponse);
    }

    @Test
    public void searchAndFilterAudit() {
        Page<Audit> auditPage = auditService.searchAndFilterAudit(auditDTO);
        Assert.assertNotNull( "Audit page cannot be null", auditPage);
        Assert.assertTrue( "Total elements must not be less than elements fetched ", auditPage.getTotalElements() >= auditPage.getNumberOfElements());
    }
}