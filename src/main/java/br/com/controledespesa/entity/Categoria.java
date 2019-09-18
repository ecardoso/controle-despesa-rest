package br.com.controledespesa.entity;

import java.io.Serializable;

//@Entity(name = "CATEGORIA")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 553406608346142661L;

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;

	public Categoria() {

	}

	public Categoria(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}