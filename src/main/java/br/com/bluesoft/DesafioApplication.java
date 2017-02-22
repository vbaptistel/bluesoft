package br.com.bluesoft;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.bluesoft.entidade.Comprador;
import br.com.bluesoft.entidade.Fornecedor;
import br.com.bluesoft.entidade.Produto;
import br.com.bluesoft.repositorio.CompradorRepositorio;
import br.com.bluesoft.repositorio.FornecedorRepositorio;
import br.com.bluesoft.repositorio.ProdutoRepositorio;

@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(FornecedorRepositorio repositorioFornecedor, ProdutoRepositorio produtoRepositorio, CompradorRepositorio compradorRepositorio ) {
		return (args) -> {
			
			Fornecedor refri = new Fornecedor("27889293000166", "Refrigerante Saudável");
			Fornecedor cerveja = new Fornecedor("63285474000147", "Crazy beer");
			repositorioFornecedor.save(refri);
			repositorioFornecedor.save(cerveja);

			produtoRepositorio.save(new Produto(7894900010015l, "REFRIGERANTE COCA-COLA", new BigDecimal(3.10), refri));
			produtoRepositorio.save(new Produto(7894900011753l, "REFRIGERANTE COCA-COLA 1,5LT", new BigDecimal(5.70), refri));
			produtoRepositorio.save(new Produto(7894900700046l, "REFRIGERANTE COCA COLA LATA ZERO", new BigDecimal(3.50), refri));
			
			produtoRepositorio.save(new Produto(7898911693496l, "MISTURA CLÁSSICA VERTIGEM", new BigDecimal(10.43), cerveja));
			produtoRepositorio.save(new Produto(7891991010832l, "CERVEJA BUDWEISER", new BigDecimal(7.95), cerveja));
			produtoRepositorio.save(new Produto(7891149102150l, "CERVEJA SKOL", new BigDecimal(8.34), cerveja));
			produtoRepositorio.save(new Produto(7891149101900l, "CERVEJA STELLA ARTOIS", new BigDecimal(10.43), cerveja));
			produtoRepositorio.save(new Produto(7891149210503l, "CERVEJA CARACU", new BigDecimal(10.43), cerveja));
			
			compradorRepositorio.save(new Comprador(1L, "Rodrigo Bonfim", BigDecimal.valueOf(100.20)));
			compradorRepositorio.save(new Comprador(2L, "Adalberto Silva", BigDecimal.valueOf(1000.20)));
			
		};
	}
}
