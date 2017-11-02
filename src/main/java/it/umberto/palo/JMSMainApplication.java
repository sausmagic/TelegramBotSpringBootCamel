package it.umberto.palo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
//@EnableScheduling
//@EnableAsync
//@EnableAutoConfiguration
//@ComponentScan(basePackages= {"it.umberto.palo.apache.jms.route","it.umberto.palo.apache.jms"})
/**
 * portato il component Scan nel ROOT package come da manuale SpringBoot
 * 
 *  vedi manuale versione Spring boot 1.5.8-RELEASE
 *  capitolo 14 (Structuring your code)
 *  
 *  Questo permette di avere allo scan la visibilità di tutti i @component, @Service, @Autowired, @Bean......
 *  
 *  NOTA BENE: 
 *  ho definito i package per il semplice fatto che esistono due applicazioni separate
 *  
 * @author u.palo
 *
 */


public class JMSMainApplication extends AsyncConfigurerSupport {
	public static void main(String[] args) {
        SpringApplication.run(JMSMainApplication.class, args);
        while (true){
//        	System.out.println("in ascolto.......");
        }
    }
}
