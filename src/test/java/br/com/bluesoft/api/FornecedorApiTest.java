package br.com.bluesoft.api;

import static org.hamcrest.Matchers.hasSize;
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
public class FornecedorApiTest {
	
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
	public void deveRetornarForncedores() throws Exception {
		mvc.perform(get("/api/fornecedores"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$").isNotEmpty())
		.andExpect(jsonPath("$[0].cnpj").value("27889293000166"))
		.andExpect(jsonPath("$[0].razaoSocial").value("Refrigerante Saudável"));
	}
	
	
	@Test
	public void deveRetornarProdutosDoFornecedor() throws Exception {
		mvc.perform(get("/api/fornecedores/27889293000166/produtos"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$[0].gtin").value(7894900010015l))
		.andExpect(jsonPath("$[0].preco").value(3.10))
		.andExpect(jsonPath("$[0].descricao").value("REFRIGERANTE COCA-COLA"));
	}

}
