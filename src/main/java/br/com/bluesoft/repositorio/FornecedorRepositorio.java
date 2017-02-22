package br.com.bluesoft.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.bluesoft.entidade.Fornecedor;

public interface FornecedorRepositorio extends CrudRepository<Fornecedor, String> {

}
