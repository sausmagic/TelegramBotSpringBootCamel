package it.umberto.palo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import it.umberto.palo.datamongodb.model.Account;
import it.umberto.palo.datamongodb.repository.IAccountRepository;

@SpringBootApplication(scanBasePackageClasses = {Application.class})
@Configuration
//Umberto: avvio lazy per l'injection spring, filtro per escusione su package
//@ComponentScan(lazyInit=true,excludeFilters=@ComponentScan.Filter(type=FilterType.REGEX, pattern= {"it.umberto.palo.apache","it.umberto.palo.bot"}))
@ComponentScan(excludeFilters=@ComponentScan.Filter(type=FilterType.REGEX, pattern= {"it.umberto.palo.apache","it.umberto.palo.bot"}))

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
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

  @Autowired
  IAccountRepository accountRepository;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }

  @Bean
  UserDetailsService userDetailsService() {
    return new UserDetailsService() {

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account != null) {
        return new User(account.getUsername(), account.getPassword(), true, true, true, true,
                AuthorityUtils.createAuthorityList("USER"));
        } else {
          throw new UsernameNotFoundException("could not find the user '"
                  + username + "'");
        }
      }
      
    };
  }
}

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().fullyAuthenticated().and().
    httpBasic().and().
    csrf().disable();
  }
  
}

