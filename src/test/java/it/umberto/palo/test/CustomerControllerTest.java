package it.umberto.palo.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import it.umberto.palo.DTO.CustomerDTO;
import it.umberto.palo.datamongodb.model.Customer;
import it.umberto.palo.datamongodb.repository.IAccountRepository;

//@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes= {CustomerControllerTest.class})
@AutoConfigureMockMvc
//@AutoConfigureDataMongo
//@ComponentScan(excludeFilters=@ComponentScan.Filter(type=FilterType.REGEX, pattern= {"it.umberto.palo"}))
@ComponentScan(basePackages = {"it.umberto.palo.datamongodb"})
public class CustomerControllerTest {

	@Autowired
    protected MockMvc mockMvc;
    protected ObjectMapper mapper;
    private static MongodExecutable mongodExecutable;
    
    @Autowired
    protected MongoTemplate mongoTemplate;
    
    @Autowired
    IAccountRepository accountRepository;
    
    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }
    @After
    public void after() {
        mongoTemplate.dropCollection(Customer.class);
    }
    
    /**
     * Here we are setting up an embedded mongodb instance to run with our
     * integration tests.
     * 
     * @throws UnknownHostException
     * @throws IOException
     */
    @BeforeClass
    public static void beforeClass() throws UnknownHostException, IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        IMongodConfig mongoConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
            .net(new Net(27017, false)).build();
        mongodExecutable = starter.prepare(mongoConfig);
        try {
            mongodExecutable.start();
        } catch (Exception e) {
            closeMongoExecutable();
        }
    }
    @AfterClass
    public static void afterClass() {
        closeMongoExecutable();
    }
    
    private static void closeMongoExecutable() {
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
    }
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
