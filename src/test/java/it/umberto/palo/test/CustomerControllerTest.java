package it.umberto.palo.test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.umberto.palo.DTO.CustomerDTO;

public class CustomerControllerTest extends BaseIntegrationTest{

	@Test
	public void test() throws UnsupportedEncodingException, Exception {
		CustomerDTO customerDTO = new CustomerDTO("test", 30);
		String jsonContent = mapper.writeValueAsString(customerDTO);
		String response = mockMvc.perform(MockMvcRequestBuilders.post("/Customer/add")
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
