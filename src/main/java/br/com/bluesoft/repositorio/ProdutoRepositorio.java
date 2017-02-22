package br.com.bluesoft.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.bluesoft.entidade.Produto;

public interface ProdutoRepositorio extends CrudRepository<Produto, Long> {
	
	Iterable<Produto> findByFornecedorCnpj(String fornecedorCnpj);

}
