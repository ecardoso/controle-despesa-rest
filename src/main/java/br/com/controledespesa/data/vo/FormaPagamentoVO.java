package br.com.controledespesa.data.vo;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FormaPagamentoVO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 3745908541474026118L;

	@JsonProperty("id")
	@ApiModelProperty(value = "id forma de pagamento")
	private Long key;

	@ApiModelProperty(value = "descrição da forma de pagamento")
	private String descricao;

}
