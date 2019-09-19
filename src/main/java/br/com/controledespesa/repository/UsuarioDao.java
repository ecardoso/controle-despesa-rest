package br.com.controledespesa.repository;

import br.com.controledespesa.TipoLoginEnum;
import br.com.controledespesa.entity.Usuario;

public interface UsuarioDao {

	Usuario getUsuarioByEmailAndSenha(String email, String senha, TipoLoginEnum tipoLoginEnum);

	Usuario getUsuarioByEmail(String email, TipoLoginEnum tipoLoginEnum);

}
