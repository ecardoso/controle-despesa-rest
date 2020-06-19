package br.com.controledespesa.data.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DespesaVO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 8493198584033763957L;

	@JsonProperty("id")
	@ApiModelProperty(value = "id despesa")
	private Long key;

	@ApiModelProperty(value = "categoria")
	private CategoriaVO categoria;

	@ApiModelProperty(value = "usuario")
	private UsuarioVO usuario;

	@ApiModelProperty(value = "forma de pagamento")
	private FormaPagamentoVO formaPagamento;

	@ApiModelProperty(value = "data da compra")
	private LocalDateTime dataCompra;

	@ApiModelProperty(value = "data de pagamento")
	private LocalDateTime dataPagamento;

	@ApiModelProperty(value = "descrição da despesa")
	private String descricao;

	@ApiModelProperty(value = "valor da despesa")
	private BigDecimal valor;

	@ApiModelProperty(value = "despesa fixa do mês")
	private boolean despesaFixa;

	@ApiModelProperty(value = "despesa foi paga")
	private boolean pago;

	@ApiModelProperty(value = "quantidade de parcelas")
	private int quantidadeParcelas;

}
