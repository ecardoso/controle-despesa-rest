package br.com.controledespesa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "MELHOR_DATA_COMPRA")
public class MelhorDataCompra {

	@Id
	@SequenceGenerator(name = "MELHOR_DATA_COMPRA_ID", sequenceName = "MELHOR_DATA_COMPRA_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MELHOR_DATA_COMPRA_ID")
	private Long id;
	private int dataMelhorCompra;
	private int dataPagamento;

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

}
