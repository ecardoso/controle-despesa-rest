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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.controledespesa.enums.TipoLoginEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({ "id", "tipoLogin", "nome", "email" })
@Entity(name = "USUARIO")
public class Usuario extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = -229315017076086856L;

	@Id
	@Column(name = "id")
	@JsonAlias(value = "id")
	@SequenceGenerator(name = "USUARIO_ID", sequenceName = "USUARIO_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_ID")
	@ApiModelProperty(value = "id usuário")
	private Long key;

	@ApiModelProperty(value = "nome do usuário")
	private String nome;

	@ApiModelProperty(value = "e-mail do usuário")
	private String email;

	@JsonIgnore
	@ApiModelProperty(value = "senha do usuário")
	private String senha;

	@ApiModelProperty(value = "tipo de login do usuário")
	private Integer tipoLogin;

	public Usuario() {
		this.tipoLogin = TipoLoginEnum.SISTEMA.getChave();
	}

	public Usuario(String nome, String email) {
		this();
		this.nome = nome;
		this.email = email;
	}

}
