package it.umberto.palo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableWebSecurity
@ComponentScan("it.umberto.palo")
public class SecurityJavaConfig extends GlobalAuthenticationConfigurerAdapter {
@Autowired
IAccountRepository accountRepository;

/**
 * Inizializza il contesto di Spring Security
 */
@Override
public void init(AuthenticationManagerBuilder auth) throws Exception {
  auth.userDetailsService(userDetailsService());
}

/**
 * Umberto: classe interna che implementa un service per recuperare gli utenti dal database mongodb e importarli nel contesto di spring security
 * @return
 */
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
