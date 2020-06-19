package br.com.controledespesa.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity(name = "FORMA_PAGAMENTO")
public class FormaPagamento implements Serializable {

	private static final long serialVersionUID = 3745908541474026118L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "FORMA_PAGAMENTO_ID", sequenceName = "FORMA_PAGAMENTO_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMA_PAGAMENTO_ID")
	private Long key;
	private String descricao;

}
