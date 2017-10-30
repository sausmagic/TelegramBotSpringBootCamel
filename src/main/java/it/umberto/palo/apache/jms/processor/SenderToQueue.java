package it.umberto.palo.apache.jms.processor;

import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import it.umberto.palo.apache.jms.route.MainRouter;

public class SenderToQueue extends MainRouter implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String message = UUID.randomUUID().toString();
        log.info("**********************************");
        log.info("Send message '{}' to queue....", message);
        producerTemplate.sendBody("activemq://queuetestp", message);

	}

}
