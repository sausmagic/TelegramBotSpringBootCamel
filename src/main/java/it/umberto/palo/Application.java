package it.umberto.palo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import it.umberto.palo.datamongodb.model.Account;
import it.umberto.palo.datamongodb.repository.IAccountRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
	/**
	 * Umberto: creiamo un Comman Line Runner per eseguire e creare un account di test all'avvio per inserire le credenziali Account
	 * @param accountRepository
	 * @return
	 */
    @Bean
    CommandLineRunner init(final IAccountRepository accountRepository) {
      
      return new CommandLineRunner() {

        @Override
        public void run(String... arg0) throws Exception {
          accountRepository.save(new Account("rbaxter", "password"));
          
        }
        
      };

    }
    
}

