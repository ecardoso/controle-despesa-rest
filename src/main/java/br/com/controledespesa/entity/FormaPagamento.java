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

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "FORMA_PAGAMENTO")
public class FormaPagamento extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 3745908541474026118L;

	@Id
	@Column(name = "id")
	@JsonAlias(value = "id")
	@SequenceGenerator(name = "FORMA_PAGAMENTO_ID", sequenceName = "FORMA_PAGAMENTO_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMA_PAGAMENTO_ID")
	@ApiModelProperty(value = "id forma de pagamento")
	private Long key;

	@ApiModelProperty(value = "descrição da forma de pagamento")
	private String descricao;

}
