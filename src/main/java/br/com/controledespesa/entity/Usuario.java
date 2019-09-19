package br.com.controledespesa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.controledespesa.TipoLoginEnum;

@Entity(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;
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
