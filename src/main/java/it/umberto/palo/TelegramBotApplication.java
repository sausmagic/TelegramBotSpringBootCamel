package it.umberto.palo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This is a standard spring-boot main class.
 */
/**
 * Umberto: 
 * @see https://www.concretepage.com/spring-boot/spring-boot-getting-started-using-maven-and-gradle-with-eclipse
 * 
 * Per permettere a Spring boot di eseguire le configurazioni automatiche per eseguire il contesto in un jar
 * */
@Configuration
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackageClasses = {TelegramBotApplication.class})
//Umberto: è un array di String dove si specificano i package dei @Component e relativi Bean che si ignettano con @Autowired
@ComponentScan(basePackages= {"it.umberto.palo.bot.camel.route","it.umberto.palo.bot"})

/**
 * portato il component Scan nel ROOT package come da manuale SpringBoot
 * 
 *  vedi manuale versione Spring boot 1.5.8-RELEASE
 *  capitolo 14 (Structuring your code)
 *  
 *  Questo permette di avere allo scan la visibilità di tutti i @component, @Service, @Autowired, @Bean......
 *  
 *  
 *  NOTA BENE: 
 *  ho definito i package per il semplice fatto che esistono due applicazioni separate
 *  
 * @author u.palo
 *
 */
public class TelegramBotApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TelegramBotApplication.class, args);
    }

}
