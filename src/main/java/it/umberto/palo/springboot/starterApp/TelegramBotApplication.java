package it.umberto.palo.springboot.starterApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is a standard spring-boot main class.
 */
@SpringBootApplication
@ComponentScan(basePackages= {"it.umberto.palo.camel.route","it.umberto.palo.bot"})
public class TelegramBotApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TelegramBotApplication.class, args);
    }

}
