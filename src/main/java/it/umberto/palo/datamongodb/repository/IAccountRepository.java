package it.umberto.palo.datamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.umberto.palo.datamongodb.model.Account;

public interface IAccountRepository extends MongoRepository<Account, String> {
	  
	  public Account findByUsername(String username); 

}
