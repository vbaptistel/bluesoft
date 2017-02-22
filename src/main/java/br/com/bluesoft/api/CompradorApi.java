package br.com.bluesoft.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.entidade.Comprador;
import br.com.bluesoft.repositorio.CompradorRepositorio;

@RestController
@RequestMapping("/api/compradores")
public class CompradorApi {

	@Autowired
	private CompradorRepositorio compradorRepositorio;
	
	@RequestMapping(method = GET)
	public Iterable<Comprador> listar(){
		return compradorRepositorio.findAll();
	}
}
