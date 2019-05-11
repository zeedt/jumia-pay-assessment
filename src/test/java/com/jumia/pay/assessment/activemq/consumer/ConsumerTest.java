package com.jumia.pay.assessment.activemq.consumer;

import com.jumia.pay.assessment.service.AuditService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConsumerTest {

    @Mock
    AuditService auditService;

    @InjectMocks
    private Consumer consumer;

    @Mock
    private Message message;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Mockito.when(auditService.persistAudit(any(Message.class)))
                .thenReturn("Audit persisted successfully");
    }

    @Test
    public void receiveMessage() {
        consumer.receiveMessage(message);
    }
}