package br.com.bluesoft.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.bluesoft.entidade.Pedido;

public interface PedidoRepositorio extends CrudRepository<Pedido, Long> {
		
}
