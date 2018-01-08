package org.bookpub;

import org.bookpub.BookpubApplication;
import org.bookpub.entity.Book;
import org.bookpub.repository.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;

import javax.sql.DataSource;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
// This ability to select a random http port is very handy when running tests on
// a Jenkins or any other CI server where, if multiple jobs are running in
// parallel, you could get a port collision
@SpringBootTest(classes = {BookpubApplication.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookpubApplicationTests {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private BookRepository repository;
	@Autowired
	private DataSource ds;
	@Value("${local.server.port}")
	private int port;

	private MockMvc mockMvc;
	private RestTemplate restTemplate = new RestTemplate();
	private static boolean loadDataFixtures = true;

	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Before
	public void loadDataFixtures() throws IOException {
		if (loadDataFixtures) {
			ResourceDatabasePopulator populator = new ResourceDatabasePopulator(context.getResources("classpath:/test-data.sql"));
			DatabasePopulatorUtils.execute(populator, ds);
			loadDataFixtures = false;
		}
	}

	@Test
	public void contextLoads() {
		Assert.assertEquals(3, repository.count());
	}

	@Test
	public void webAppBookIsbnApi() {
		// Using client side rest template
		Book book = restTemplate.getForObject("http://localhost:" + port + "/books/978-1-78528-415-1", Book.class);
		Assert.assertNotNull(book);
		Assert.assertEquals("Packt", book.getPublisher().getName());
	}

	@Test
	public void webAppPublisherApi() throws Exception {
		// Using server side mockmvc
		mockMvc.perform(get("/publishers/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.parseMediaType("application/hal+json;charset=UTF-8")))
				.andExpect(content().string(containsString("Packt"))).andExpect(jsonPath("$.name", is("Packt")));
	}

}
