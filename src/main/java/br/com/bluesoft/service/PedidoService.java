package br.com.bluesoft.service;

import br.com.bluesoft.entidade.Pedido;

public interface PedidoService {
	
	Pedido calcular(Pedido pedido);
	
	Pedido incluir(Pedido pedido);

	Iterable<Pedido> buscarTodos();
	
}
