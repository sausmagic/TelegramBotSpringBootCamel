package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import it.umberto.palo.datamongodb.configuration.AppConfig;
import it.umberto.palo.datamongodb.model.Customer;
import it.umberto.palo.datamongodb.repository.ICustomerMongoRepository;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@DataMongoTest
//@SpringBootTest(classes = {CustomerMongoRepository.class, AppConfig.class})
//@SpringBootTest(classes = {CustomerMongoRepository.class})
@SpringBootTest(classes = {AppConfig.class})
//@ComponentScan(basePackages={"it.umberto.palo.datamongodb.repository"})
//@Import(AppConfig.class)
//@ContextConfiguration(classes = {AppConfig.class})
//@IfProfileValue(name = ACTIVE_PROFILES_PROPERTY_NAME, value = "it-embedded")

public class TestMongoDB {

	@Autowired
	ICustomerMongoRepository customerRepo;
	
	@Test
	public void exampleTest() {
		/**
		 * Save Entities 
		 */
		System.out.println("----------------Save customers!");
		// save an Entity
		Customer peter = new Customer("Peter", 24);
		customerRepo.save(peter);
		
		// save a List Entity
		List<Customer> custs = Arrays.asList(new Customer("Mary", 27), new Customer("Lauren", 21), new Customer("Peter", 19));
		customerRepo.save(custs);
		
		/**
		 * Find Entities
		 */
		System.out.println("----------------Find customers has name is 'Peter'!");
		List<Customer> peters = customerRepo.findByName("Peter");
		// -> Show result
		peters.forEach(System.out::println);
		
		/**
		 * Update an Entity
		 */
		System.out.println("----------------Rename a customer which has name is 'Peter' to 'Jack'!");
		Customer jack = peters.get(0);
		jack.setName("Jack");
		customerRepo.save(jack);
		
		/**
		 * Delete an Entity
		 */
		System.out.println("----------------Delete the remain Peter customer!");
		customerRepo.delete(peters.get(1));
		
		/**
		 * Find All customer
		 */
		System.out.println("----------------Show All Customers!");
		List<Customer> customers = customerRepo.findAll();
		customers.forEach(System.out::println);
	}
	
}
