package br.com.controledespesa.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.com.controledespesa.enums.TipoLoginEnum;
import lombok.Data;

@Data
@Entity(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -229315017076086856L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "USUARIO_ID", sequenceName = "USUARIO_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_ID")
	private Long key;

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

}
