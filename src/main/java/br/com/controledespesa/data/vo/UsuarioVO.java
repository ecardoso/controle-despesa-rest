package br.com.controledespesa.data.vo;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.controledespesa.enums.TipoLoginEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({ "id", "tipoLogin", "nome", "email" })
public class UsuarioVO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = -229315017076086856L;

	@JsonProperty("id")
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

	public UsuarioVO() {
		this.tipoLogin = TipoLoginEnum.SISTEMA.getChave();
	}

	public UsuarioVO(String nome, String email) {
		this();
		this.nome = nome;
		this.email = email;
	}

}
