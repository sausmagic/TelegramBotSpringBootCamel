package it.umberto.palo.datamongodb.configuration;

import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

/**
 * Questa classe configura il driver per la connessione al DB MongoDB se non si vuole usare la configurazione auto di spring-boot
 * per attivare questa configurazione va mappato (nel @ComponentScan) il package di appartenenza sulla classe di start (quella con il main) 
 * 
 * In alternativa si può usare il file di.
 * application.properties
 * 
 * oppure
 * 
 * application.yaml
 * 
 * per configurare le variabili per configurare l'avvio del db con parametri custom
 * 
 * 
 * Ancora meglio sulla classe di start di Spring Boot è possibile utilizzare l'annotazione di @Import 
 * che estende @Configuration e @ComponentScan
 * permettendo l'import automatico e l'injection delle configurazioni qui specificate. La nomenclatura da usare è:
 * 
 * @Import(AppConfig.class)
 * @author u.palo
 *
 */
@Configuration
//@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class })
@EnableAutoConfiguration
//@ComponentScan(basePackages= {"it.umberto.palo.datamongodb.repository"})
public class AppConfig {

	@Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException{
        return new SimpleMongoDbFactory(new MongoClient("localhost", 27017), "test");
    }
  
    @Bean
    public MongoOperations mongoOperations() throws UnknownHostException{
        return new MongoTemplate(mongoDbFactory());
    }
}
