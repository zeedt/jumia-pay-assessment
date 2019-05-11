package com.jumia.pay.assessment.activemq.consumer;


import com.jumia.pay.assessment.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {

	@Autowired
	private AuditService auditService;

	private Logger logger = LoggerFactory.getLogger(Consumer.class.getName());

	@JmsListener(destination = "jumia-pay.inbound.queue")
	public void receiveMessage(final Message jsonMessage) {

		try {
			auditService.persistAudit(jsonMessage);
		} catch (IOException e) {
			logger.error("Error occurred while processing message due to ", e);
		}
	}

}
