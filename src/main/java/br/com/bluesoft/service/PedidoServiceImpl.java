package br.com.bluesoft.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.bluesoft.entidade.Imposto;
import br.com.bluesoft.entidade.Item;
import br.com.bluesoft.entidade.Pedido;
import br.com.bluesoft.repositorio.CompradorRepositorio;
import br.com.bluesoft.repositorio.PedidoRepositorio;

@Service("pedidoService")
public class PedidoServiceImpl implements PedidoService{
	
	private static final String AWS_URL = "https://0b2i66jenf.execute-api.us-east-1.amazonaws.com/dev/impostos/";

	@Autowired
	private PedidoRepositorio repositorio;
	
	@Autowired
	private CompradorRepositorio compradorRepositorio;
	
	@Override
	public Pedido calcular(Pedido pedido) {
		if(pedido == null)
			throw new IllegalArgumentException();
		
		RestTemplate restTemplate = new RestTemplate();
		Map<Long, Imposto> impostos = new HashMap<>();
		
		if(pedido.getItens() != null || !pedido.getItens().isEmpty()){
			pedido.getItens().removeIf(i -> i.getQuantidade() == null || i.getQuantidade() < 1);
			for(Item item : pedido.getItens()){
				if(!impostos.containsKey(item.getProduto().getGtin())){
					String url = AWS_URL + item.getProduto().getGtin();
					impostos.put(item.getProduto().getGtin(), restTemplate.getForObject(url, Imposto.class));
				}
				
				item.setImpostos(impostos.get(item.getProduto().getGtin()));
				item.setTotal(getTotal(item));
			}
			
			BigDecimal saldoComprador = pedido.getComprador().getCredito().subtract(Pedido.calcularValorTotal(pedido));
			pedido.getComprador().setCredito(saldoComprador);
		}
		
		return pedido;
	}

	@Override
	public Pedido incluir(Pedido pedido) {
		compradorRepositorio.save(pedido.getComprador());
		return repositorio.save(pedido);
	}
	
	@Override
	public Iterable<Pedido> buscarTodos() {
		return repositorio.findAll();
	}

	private BigDecimal getTotal(Item item){
		BigDecimal retorno = BigDecimal.ZERO;
		retorno = retorno.add(item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())));
		retorno = retorno.add(item.getCofins());
		retorno = retorno.add(item.getIcms());
		retorno = retorno.add(item.getPis());
		return retorno;
	}


}
