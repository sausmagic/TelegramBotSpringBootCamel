package it.umberto.palo.datamongodb.service;

import it.umberto.palo.DTO.CustomerDTO;
import it.umberto.palo.datamongodb.model.Customer;

public interface ICustomerMongoService {

	CustomerDTO addCustomer(Customer customer) throws Exception;
}
