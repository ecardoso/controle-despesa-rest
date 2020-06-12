package br.com.controledespesa.entity;

import java.io.Serializable;
import java.util.Date;

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
@Entity(name = "MELHOR_DATA_COMPRA")
public class MelhorDataCompra extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = -6787263938307811073L;

	@Id
	@Column(name = "id")
	@JsonProperty("id")
	@SequenceGenerator(name = "MELHOR_DATA_COMPRA_ID", sequenceName = "MELHOR_DATA_COMPRA_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MELHOR_DATA_COMPRA_ID")
	@ApiModelProperty(value = "id melhor data da compra")
	private Long key;

	@ApiModelProperty(value = "dia da melhor compra")
	private int dataMelhorCompra;

	@ApiModelProperty(value = "dia do pagamento")
	private int dataPagamento;

	@ApiModelProperty(value = "mes e referencia")
	private Date mesReferencia;

	@ManyToOne(targetEntity = FormaPagamento.class)
	@ApiModelProperty(value = "forma de pagamento")
	private FormaPagamento formaPagamento;

	@ManyToOne(targetEntity = Usuario.class)
	@ApiModelProperty(value = "usuário")
	private Usuario usuario;

}
