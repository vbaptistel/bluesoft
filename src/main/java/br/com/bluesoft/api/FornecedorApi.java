package br.com.bluesoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.entidade.Fornecedor;
import br.com.bluesoft.entidade.Produto;
import br.com.bluesoft.repositorio.FornecedorRepositorio;
import br.com.bluesoft.repositorio.ProdutoRepositorio;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorApi {

	@Autowired
	private FornecedorRepositorio repositorio;

	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	@RequestMapping(method = GET)
	public Iterable<Fornecedor> listarForncedores() {
		return repositorio.findAll();
	}

	@RequestMapping(value = "/{cnpj}/produtos", method = GET)
	public Iterable<Produto> listarProdutosPorFornecedor(@PathVariable("cnpj") String cnpj) {
		return produtoRepositorio.findByFornecedorCnpj(cnpj);
	}
}
