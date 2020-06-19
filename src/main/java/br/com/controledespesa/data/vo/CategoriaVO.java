package br.com.controledespesa.data.vo;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CategoriaVO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 553406608346142661L;

	@JsonProperty("id")
	@ApiModelProperty(value = "id categoria")
	private Long key;

	@ApiModelProperty(value = "descrição da categoria")
	private String descricao;

}