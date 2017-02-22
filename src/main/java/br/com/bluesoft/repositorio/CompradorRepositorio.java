package br.com.bluesoft.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.bluesoft.entidade.Comprador;

public interface CompradorRepositorio extends CrudRepository<Comprador, Long> {

}
