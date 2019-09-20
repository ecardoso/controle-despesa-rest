package br.com.controledespesa.repository;

import br.com.controledespesa.entity.Usuario;
import br.com.controledespesa.enums.TipoLoginEnum;

public interface UsuarioDao {

	Usuario getUsuarioByEmailAndSenha(String email, String senha, TipoLoginEnum tipoLoginEnum);

	Usuario getUsuarioByEmail(String email, TipoLoginEnum tipoLoginEnum);

}
