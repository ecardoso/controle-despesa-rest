package br.com.controledespesa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "MELHOR_DATA_COMPRA")
public class MelhorDataCompra {

	@Id
	@SequenceGenerator(name = "MELHOR_DATA_COMPRA_ID", sequenceName = "MELHOR_DATA_COMPRA_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MELHOR_DATA_COMPRA_ID")
	@ApiModelProperty(value = "id melhor data da compra")
	private Long id;

	@ApiModelProperty(value = "dia da melhor compra")
	private int dataMelhorCompra;

	@ApiModelProperty(value = "dia do pagamento")
	private int dataPagamento;
	
	@ApiModelProperty(value = "mes e referencia")
	private Date mesReferencia;	

	@ManyToOne(targetEntity = FormaPagamento.class)
	@ApiModelProperty(value = "forma de pagamento")
	private FormaPagamento formaPagamento;

	@ManyToOne(targetEntity = Usuario.class)
	@ApiModelProperty(value = "usuário")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDataMelhorCompra() {
		return dataMelhorCompra;
	}

	public void setDataMelhorCompra(int dataMelhorCompra) {
		this.dataMelhorCompra = dataMelhorCompra;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(int dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(Date mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

}
