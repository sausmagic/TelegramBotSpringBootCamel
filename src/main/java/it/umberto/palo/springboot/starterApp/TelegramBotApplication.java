package it.umberto.palo.springboot.starterApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is a standard spring-boot main class.
 */
/**
 * Umberto: 
 * @see https://www.concretepage.com/spring-boot/spring-boot-getting-started-using-maven-and-gradle-with-eclipse
 * 
 * Per permettere a Spring boot di eseguire le configurazioni automatiche per eseguire il contesto in un jar
 * */
@EnableAutoConfiguration
@SpringBootApplication
//Umberto: è un array di String dove si specificano i package dei @Component e relativi Bean che si ignettano con @Autowired
@ComponentScan(basePackages= {"it.umberto.palo.camel.route","it.umberto.palo.bot"})
public class TelegramBotApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TelegramBotApplication.class, args);
    }

}
