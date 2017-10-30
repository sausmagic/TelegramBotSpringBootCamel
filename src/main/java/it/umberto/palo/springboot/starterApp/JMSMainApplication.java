package it.umberto.palo.springboot.starterApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages= {"it.umberto.palo.apache.jms.route","it.umberto.palo.apache.jms"})
public class JMSMainApplication extends AsyncConfigurerSupport {
	public static void main(String[] args) {
        SpringApplication.run(JMSMainApplication.class, args);
//        while (true){
//        	System.out.println("in ascolto.......");
//        }
    }
}
