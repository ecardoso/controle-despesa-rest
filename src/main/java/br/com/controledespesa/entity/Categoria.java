package br.com.controledespesa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "CATEGORIA")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 553406608346142661L;

	@Id
	@SequenceGenerator(name = "CATEGORIA_ID", sequenceName = "CATEGORIA_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIA_ID")
	@ApiModelProperty(value = "id categoria")
	private Long id;

	@ApiModelProperty(value = "descrição da categoria")
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