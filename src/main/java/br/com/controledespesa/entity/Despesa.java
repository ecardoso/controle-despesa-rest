package br.com.controledespesa.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "DESPESA")
public class Despesa {

	@Id
	@SequenceGenerator(name = "DESPESA_ID", sequenceName = "DESPESA_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DESPESA_ID")
	@ApiModelProperty(value = "id despesa")
	private Long id;

	@ManyToOne(targetEntity = Categoria.class)
	@ApiModelProperty(value = "categoria")
	private Categoria categoria;

	@ManyToOne(targetEntity = Usuario.class)
	@ApiModelProperty(value = "usuario")
	private Usuario usuario;

	@ManyToOne(targetEntity = FormaPagamento.class)
	@ApiModelProperty(value = "forma dr pagamento")
	private FormaPagamento formaPagamento;

	@ApiModelProperty(value = "data da compra")
	private Date dataCompra;

	@ApiModelProperty(value = "data de pagamento")
	private Date dataPagamento;

	@ApiModelProperty(value = "descrição da despesa")
	private String descricao;

	@ApiModelProperty(value = "valor da despesa")
	private BigDecimal valor;

	@ApiModelProperty(value = "despesa fixa do mês")
	private boolean despesaFixa;

	@ApiModelProperty(value = "despesa foi paga")
	private boolean pago;

	@ApiModelProperty(value = "quantidade de parcelas")
	private int quantidadeParcelas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isDespesaFixa() {
		return despesaFixa;
	}

	public void setDespesaFixa(boolean despesaFixa) {
		this.despesaFixa = despesaFixa;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
