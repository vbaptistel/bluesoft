package br.com.bluesoft.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.bluesoft.DesafioApplication;
 


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DesafioApplication.class)
@WebIntegrationTest
public class CompradorApiTest {
	
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.build();
	}

	@Test
	public void deveRetornarCompradores() throws Exception {
		mvc.perform(get("/api/compradores"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$").isNotEmpty())
			.andExpect(jsonPath("$[0].nome").value("Rodrigo Bonfim"))
			.andExpect(jsonPath("$[0].credito").value(100.20));
	}
}
