package it.umberto.palo.apache.jms.route;

import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.umberto.palo.apache.jms.processor.ConsumerToQueue;
import it.umberto.palo.apache.jms.processor.SenderToQueue;

@Component
public class MainRouter extends RouteBuilder{

	@Autowired
	protected ProducerTemplate producerTemplate;
	
	@Override
    public void configure() throws Exception {


        //Producer route
        from("timer://test?period=5000")
        .bean(SenderToQueue.class);

        //==========================================================================//

        //Consumer queue
        from("activemq://queuetestp")
        .bean(ConsumerToQueue.class);

    }
}
