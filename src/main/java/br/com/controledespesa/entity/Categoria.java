package br.com.controledespesa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = "CATEGORIA")
public class Categoria extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 553406608346142661L;

	@Id
	@Column(name = "id")
	@JsonAlias(value = "id")
	@SequenceGenerator(name = "CATEGORIA_ID", sequenceName = "CATEGORIA_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIA_ID")
	@ApiModelProperty(value = "id categoria")
	private Long key;

	@ApiModelProperty(value = "descrição da categoria")
	private String descricao;

}