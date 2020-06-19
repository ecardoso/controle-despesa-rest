package br.com.controledespesa.data.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MelhorDataCompraVO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = -6787263938307811073L;

	@JsonProperty("id")
	@ApiModelProperty(value = "id melhor data da compra")
	private Long key;

	@ApiModelProperty(value = "dia da melhor compra")
	private int dataMelhorCompra;

	@ApiModelProperty(value = "dia do pagamento")
	private int dataPagamento;

	@ApiModelProperty(value = "mes e referencia")
	private LocalDateTime mesReferencia;

	@ApiModelProperty(value = "forma de pagamento")
	private FormaPagamentoVO formaPagamento;

	@ApiModelProperty(value = "usuário")
	private UsuarioVO usuario;

}
