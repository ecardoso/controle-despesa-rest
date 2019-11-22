package br.com.controledespesa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "FORMA_PAGAMENTO")
public class FormaPagamento {

	@Id
	@SequenceGenerator(name = "FORMA_PAGAMENTO_ID", sequenceName = "FORMA_PAGAMENTO_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMA_PAGAMENTO_ID")
	@ApiModelProperty(value = "id forma de pagamento")
	private Long id;

	@ApiModelProperty(value = "descrição da forma de pagamento")
	private String descricao;

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
