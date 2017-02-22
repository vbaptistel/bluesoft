package br.com.bluesoft.entidade;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto {

	@Id
	private Long gtin;

	private String descricao;

	private BigDecimal preco;

	@ManyToOne
	@NotNull
	@JsonIgnore
	private Fornecedor fornecedor;
	
	Produto(){};

	public Produto(Long gtin, String descricao, BigDecimal preco, Fornecedor fornecedor) {
		this.gtin = gtin;
		this.descricao = descricao;
		this.preco = preco;
		this.fornecedor = fornecedor;
	}

	public Long getGtin() {
		return gtin;
	}

	public void setGtin(Long gtin) {
		this.gtin = gtin;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
