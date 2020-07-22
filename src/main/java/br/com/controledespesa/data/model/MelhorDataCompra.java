package br.com.controledespesa.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity(name = "MELHOR_DATA_COMPRA")
public class MelhorDataCompra implements Serializable {

	private static final long serialVersionUID = -6787263938307811073L;

	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "MELHOR_DATA_COMPRA_ID", sequenceName = "MELHOR_DATA_COMPRA_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MELHOR_DATA_COMPRA_ID")
	private Long key;

	private int dataMelhorCompra;
	private int dataPagamento;
	private LocalDateTime mesReferencia;

	@JoinColumn(name = "FORMA_PAGAMENTO_ID")
	@ManyToOne(targetEntity = FormaPagamento.class)
	private FormaPagamento formaPagamento;

	@JoinColumn(name = "USUARIO_ID")
	@ManyToOne(targetEntity = Usuario.class)
	private Usuario usuario;

}
