package it.umberto.palo.test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.umberto.palo.DTO.CustomerDTO;

public class CustomerControllerTest extends BaseIntegrationTest{

//	public static class MockSecurityContext implements SecurityContext {
//
//        private static final long serialVersionUID = -1386535243513362694L;
//
//        private Authentication authentication;
//
//        public MockSecurityContext(Authentication authentication) {
//            this.authentication = authentication;
//        }
//
//        @Override
//        public Authentication getAuthentication() {
//            return this.authentication;
//        }
//
//        @Override
//        public void setAuthentication(Authentication authentication) {
//            this.authentication = authentication;
//        }
//    }

	@Test
	@WithMockUser(username = "sausmagic", password = "sausmagic", roles = "USER")
	public void test() throws UnsupportedEncodingException, Exception {
//		 UsernamePasswordAuthenticationToken principal = 
//	                this.getPrincipal("test1");
//		 
//		 MockHttpSession session = new MockHttpSession();
//	        session.setAttribute(
//	                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, 
//	                new MockSecurityContext(principal));
		 
		CustomerDTO customerDTO = new CustomerDTO("test", 30);
		String jsonContent = mapper.writeValueAsString(customerDTO);
		String response = mockMvc.perform(MockMvcRequestBuilders.post("/Customer/addCustomer")
//				.header(HttpHeaders.AUTHORIZATION,"Basic "+Base64Utils.encodeToString("sausmagic:sausmagic".getBytes()))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn()
				.getResponse()
				.getContentAsString();
		CustomerDTO receivedResponse = mapper.readValue(response, CustomerDTO.class);
		CustomerDTO expected = new CustomerDTO(receivedResponse.getName(), receivedResponse.getAge());
		Assert.assertThat(receivedResponse, SamePropertyValuesAs.samePropertyValuesAs(expected));
	}
	
	 
	

}
