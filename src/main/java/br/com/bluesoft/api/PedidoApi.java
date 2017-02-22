package br.com.bluesoft.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.entidade.Pedido;
import br.com.bluesoft.service.PedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoApi {
		
	@Autowired
	PedidoService service;
	
	@RequestMapping(path="/checkout", method = POST)
	public Pedido checkout(@RequestBody Pedido pedido){
		return service.calcular(pedido);
	}
	
	@RequestMapping(method = POST)
	public Pedido incluir(@RequestBody Pedido pedido){
		return service.incluir(pedido);
	}
	
	@RequestMapping(method = GET)
	public Iterable<Pedido> listarPedidos(){
		return service.buscarTodos();
	}
	
	
}
