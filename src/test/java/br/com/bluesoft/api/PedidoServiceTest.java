package br.com.bluesoft.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.bluesoft.DesafioApplication;
import br.com.bluesoft.entidade.Fornecedor;
import br.com.bluesoft.entidade.Item;
import br.com.bluesoft.entidade.Pedido;
import br.com.bluesoft.entidade.Produto;
import br.com.bluesoft.service.PedidoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DesafioApplication.class)
@WebIntegrationTest
public class PedidoServiceTest {
	
	@Autowired
	private PedidoService pedidoService;
	

	@Test(expected = IllegalArgumentException.class)
	public void deveRetornarExcecaoSePedidoNulo() throws Exception {
		pedidoService.calcular(null);
	}
	
	@Test
	public void deveCalcularOsImpostos() throws Exception {
		Pedido pedido = mockPedido();
		pedido.getItens().iterator().next().setQuantidade(1);
		pedido = pedidoService.calcular(pedido);
		assertNotNull(pedido.getItens().iterator().next().getIcms());
		assertNotNull(pedido.getItens().iterator().next().getPis());
		assertNotNull(pedido.getItens().iterator().next().getCofins());
	}
	
	private Pedido mockPedido(){
		Pedido pedido = new Pedido();
		pedido.setId(1L);
		pedido.setItens(getItens());
		return pedido;
	}

	private List<Item> getItens() {
		Fornecedor refri = new Fornecedor("27889293000166", "Refrigerante Saudável");
		Item item1 = new Item(1L, new Produto(7894900010015l, "REFRIGERANTE COCA-COLA", new BigDecimal(3.10), refri));
		return Arrays.asList(item1);
	}

}
