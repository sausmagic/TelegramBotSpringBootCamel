package it.umberto.palo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.umberto.palo.DTO.CustomerDTO;
import it.umberto.palo.datamongodb.model.Customer;
import it.umberto.palo.datamongodb.service.ICustomerMongoService;





@RestController
@RequestMapping(path= "/Customer", consumes= {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

	@Autowired
	ICustomerMongoService customerService;
	
	 @ResponseBody
	    @ResponseStatus(value = HttpStatus.CREATED)
	    @RequestMapping(method = RequestMethod.POST, path = "/addCustomer", produces = {
	        MediaType.APPLICATION_JSON_VALUE
	    }, consumes = {
	        MediaType.APPLICATION_JSON_VALUE
	    })
	public CustomerDTO addCustomer(@Valid @RequestBody Customer customer) {
		 CustomerDTO customerDTO = new CustomerDTO(customer.getName(), customer.getAge());
		 try {
			 customerDTO = customerService.addCustomer(customer);
			 customerDTO.setInserted(true);
			 customerDTO.setStatus("OK-Saved to DB!");
		} catch (Exception e) {
			customerDTO.setInserted(false);
			customerDTO.setStatus("KO-Not Saved to DB!");
			customerDTO.setErrorCode("1");
			customerDTO.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return null;
		 
	 }
}
