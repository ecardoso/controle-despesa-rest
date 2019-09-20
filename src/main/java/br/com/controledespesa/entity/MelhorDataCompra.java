package br.com.controledespesa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "MELHOR_DATA_COMPRA")
public class MelhorDataCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dataMelhorCompra;
	private Date dataPagamento;

	@ManyToOne(targetEntity = FormaPagamento.class)
	private FormaPagamento formaPagamento;

	@ManyToOne(targetEntity = Usuario.class)
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataMelhorCompra() {
		return dataMelhorCompra;
	}

	public void setDataMelhorCompra(Date dataMelhorCompra) {
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

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
