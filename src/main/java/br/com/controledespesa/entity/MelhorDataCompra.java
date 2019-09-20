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

	@ManyToOne(targetEntity = FormaPagamento.class)
	private FormaPagamento formaPagamento;

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

}
