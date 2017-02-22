package br.com.bluesoft.entidade;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comprador {

	@Id
	private Long id;
	
	private String nome;
	
	private BigDecimal credito;
	
	Comprador(){}
	
	public Comprador(Long id, String nome, BigDecimal credito) {
		this.id = id;
		this.nome = nome;
		this.credito = credito;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getCredito() {
		return credito;
	}

	public void setCredito(BigDecimal credito) {
		this.credito = credito;
	}
}
