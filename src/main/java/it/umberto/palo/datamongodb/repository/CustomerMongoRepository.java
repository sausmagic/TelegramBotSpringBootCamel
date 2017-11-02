package it.umberto.palo.datamongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import it.umberto.palo.datamongodb.model.Customer;

/**
 * Viene creato un servizio che espone dei servizi che interagiscono direttamente con il MongoDB
 * @author u.palo
 *
 */
@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerMongoRepository extends MongoRepository<Customer, String> {
	List<Customer> findByName(String name);
}
