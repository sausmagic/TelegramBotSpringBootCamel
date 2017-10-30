package it.umberto.palo.apache.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FtpToJMS {
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static String ftpLocation = "ftp://localhost/incoming?username=umberto&password=sausmagic";
	private static boolean loaded = false;

	public void start() throws Exception {

		CamelContext context = new DefaultCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from(ftpLocation).process(executeFirstProcessor()).to("jms:queuetest");
				
			}
		});

		System.out.println("start context!");
		context.start();

		System.out.println("wait");
		System.out.println(loaded);
	

		while (loaded != true) {
			System.out.println("in attesa\n");
		}
		
		context.stop();

		System.out.println("stop context!");
		
		
	}

	private Processor executeFirstProcessor() {
		return new Processor() {
			@Override
			public void process(Exchange exchange) {
				System.out.println("We just downloaded : " + exchange.getIn().getHeader("CamelFileName"));
				loaded=true;
			}
		};
	}

	public static void main(String args[]) throws Exception {
		FtpToJMS j = new FtpToJMS();
		j.start();
	}
}
