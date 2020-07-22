package br.com.controledespesa.data.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import lombok.Data;

@Data
@Entity(name = "DESPESA")
public class Despesa implements Serializable {

	private static final long serialVersionUID = 8493198584033763957L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "DESPESA_ID", sequenceName = "DESPESA_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DESPESA_ID")
	private Long key;

	@ManyToOne(targetEntity = Categoria.class)
	private Categoria categoria;

	@ManyToOne(targetEntity = Usuario.class)
	private Usuario usuario;

	@ManyToOne(targetEntity = FormaPagamento.class)
	private FormaPagamento formaPagamento;

	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dataCompra;

	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dataPagamento;

	private String descricao;
	private BigDecimal valor;
	private boolean despesaFixa;
	private boolean pago;
	private int quantidadeParcelas;

}
