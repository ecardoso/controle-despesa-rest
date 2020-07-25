package br.com.controledespesa.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "CATEGORIA")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 553406608346142661L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "CATEGORIA_ID", sequenceName = "CATEGORIA_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIA_ID")
	private Long key;

	@OrderBy
	private String descricao;

}