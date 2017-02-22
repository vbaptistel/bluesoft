package br.com.bluesoft.entidade;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="pedido")
	private List<Item> itens;
	
	@ManyToOne
	@JoinColumn(name="comprador_id")
	private Comprador comprador;
	
	@ManyToOne
	@JoinColumn(name="fornecedor_id")
	private Fornecedor fornecedor;
	
	private Integer quantidadeTotal;
	
	private BigDecimal valorTotal;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	@PrePersist
	@PreUpdate
	private void onPersist(){
		if(this.itens == null || this.itens.size() < 1){
			this.quantidadeTotal = 0;
			this.valorTotal = BigDecimal.ZERO;
		} else { 
			this.quantidadeTotal = this.itens.stream().mapToInt(i -> i.getQuantidade()).sum();
			this.valorTotal = calcularValorTotal(this);
		}
	}
	
	public static BigDecimal calcularValorTotal(Pedido pedido){
		if(pedido.itens == null || pedido.itens.size() < 1)
			return BigDecimal.ZERO;
		else 
			return pedido.itens.stream().map(Item::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
