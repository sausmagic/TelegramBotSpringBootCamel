package com.example.demo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
//Umberto: è un array di String dove si specificano i package dei @Component e relativi Bean che si ignettano con @Autowired
@ComponentScan(basePackages= {"it.umberto.palo.camel.route","it.umberto.palo.bot"})
public class TelegramBotApplication {
	
	
}


