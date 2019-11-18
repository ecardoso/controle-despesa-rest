package br.com.controledespesa.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.Usuario;
import br.com.controledespesa.enums.TipoLoginEnum;
import br.com.controledespesa.repository.UsuarioDao;

@RestController
public class GoogleAuthenticator {

	@Autowired
	private UsuarioDao usuarioDao;

	@GetMapping(value = "/loginGoogle")
	public Usuario getLogin(@RequestParam(value = "nome") String nome, @RequestParam(value = "email") String email) {
		Usuario usuario = usuarioDao.getUsuarioByEmail(email, TipoLoginEnum.GOOGLE);
		if (usuario == null) {
			usuario = new Usuario(nome, email);
			usuario.setTipoLogin(TipoLoginEnum.GOOGLE.getChave());

			usuarioDao.save(usuario);
		}

		return usuario;
	}

}
