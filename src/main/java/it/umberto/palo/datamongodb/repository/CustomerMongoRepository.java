package it.umberto.palo.datamongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import it.umberto.palo.datamongodb.model.Customer;

@Repository
public interface CustomerMongoRepository extends MongoRepository<Customer, String> {
	List<Customer> findByName(String name);
}
