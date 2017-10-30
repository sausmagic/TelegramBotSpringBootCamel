package it.umberto.palo.apache.jms.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import it.umberto.palo.apache.jms.route.MainRouter;

public class ConsumerToQueue extends MainRouter implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String message = exchange.getIn().getBody(String.class);
        log.info("--------------------------------");
        log.info("Receive message '{}' from queue.", message);

	}

}
