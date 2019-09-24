package br.com.controledespesa.repository;

import br.com.controledespesa.entity.Usuario;
import br.com.controledespesa.enums.TipoLoginEnum;

public interface UsuarioDao extends GenericDao<Usuario, Long> {

	Usuario getUsuarioByEmailAndSenha(String email, String senha, TipoLoginEnum tipoLoginEnum);

	Usuario getUsuarioByEmail(String email, TipoLoginEnum tipoLoginEnum);

}
