package it.umberto.palo.datamongodb.service;

import org.springframework.beans.factory.annotation.Autowired;

import it.umberto.palo.DTO.CustomerDTO;
import it.umberto.palo.datamongodb.model.Customer;
import it.umberto.palo.datamongodb.repository.CustomerMongoRepository;

public class CustomerMongoServiceImpl implements ICustomerMongoService{

	@Autowired
	CustomerMongoRepository customerRepo;
	
	@Override
	public CustomerDTO addCustomer(Customer customer) {
		Customer c = customerRepo.save(customer);
		return new CustomerDTO(c.getName(), c.getAge());
	}

}
