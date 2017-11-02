package it.umberto.palo.datamongodb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.umberto.palo.DTO.CustomerDTO;
import it.umberto.palo.datamongodb.model.Customer;
import it.umberto.palo.datamongodb.repository.ICustomerMongoRepository;

public class CustomerMongoServiceImpl implements ICustomerMongoService{
	
	final static Logger logger = LoggerFactory.getLogger(CustomerMongoServiceImpl.class);
	
	@Autowired
	ICustomerMongoRepository customerRepo;
	
	@Override
	public CustomerDTO addCustomer(Customer customer) {
		logger.info("Start: Inserito a DB: "+customer);
		Customer c = customerRepo.save(customer);
		logger.info("End: Inserito a DB: "+customer);
		return new CustomerDTO(c.getName(), c.getAge());
	}

}
