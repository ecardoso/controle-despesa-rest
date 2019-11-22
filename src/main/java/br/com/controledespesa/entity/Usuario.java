package br.com.controledespesa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.com.controledespesa.enums.TipoLoginEnum;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "USUARIO")
public class Usuario {

	@Id
	@SequenceGenerator(name = "USUARIO_ID", sequenceName = "USUARIO_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_ID")
	@ApiModelProperty(value = "id usuário")
	private Long id;

	@ApiModelProperty(value = "nome do usuário")
	private String nome;

	@ApiModelProperty(value = "e-mail do usuário")
	private String email;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getTipoLogin() {
		return tipoLogin;
	}

	public void setTipoLogin(Integer tipoLogin) {
		this.tipoLogin = tipoLogin;
	}

}
