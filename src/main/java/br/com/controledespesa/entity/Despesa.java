package br.com.controledespesa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "DESPESA")
public class Despesa extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 8493198584033763957L;

	@Id
	@Column(name = "id")
	@JsonProperty("id")
	@SequenceGenerator(name = "DESPESA_ID", sequenceName = "DESPESA_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DESPESA_ID")
	@ApiModelProperty(value = "id despesa")
	private Long key;

	@ManyToOne(targetEntity = Categoria.class)
	@ApiModelProperty(value = "categoria")
	private Categoria categoria;

	@ManyToOne(targetEntity = Usuario.class)
	@ApiModelProperty(value = "usuario")
	private Usuario usuario;

	@ManyToOne(targetEntity = FormaPagamento.class)
	@ApiModelProperty(value = "forma de pagamento")
	private FormaPagamento formaPagamento;

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
