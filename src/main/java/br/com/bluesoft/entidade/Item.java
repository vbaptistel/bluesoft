package br.com.bluesoft.entidade;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	private BigDecimal pis;
	
	private BigDecimal cofins;
	
	private BigDecimal icms;
	
	private Integer quantidade;
	
	private BigDecimal total;
	
	Item(){};
	
	public Item(Long id, Produto produto){
		this.id = id;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getPis() {
		return pis;
	}

	public void setPis(BigDecimal pis) {
		this.pis = pis;
	}

	public BigDecimal getCofins() {
		return cofins;
	}

	public void setCofins(BigDecimal cofins) {
		this.cofins = cofins;
	}

	public BigDecimal getIcms() {
		return icms;
	}

	public void setIcms(BigDecimal icms) {
		this.icms = icms;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public void setImpostos(Imposto imposto){
		if(imposto == null)
			return;
		
		BigDecimal qtde = new BigDecimal(this.quantidade).divide(new BigDecimal(100));
		
		this.icms = imposto.getIcms().multiply(qtde);
		this.pis = imposto.getPis().multiply(qtde);
		this.cofins = imposto.getCofins().multiply(qtde);
	}
	
}
