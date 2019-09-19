package br.com.controledespesa.rest;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.TipoLoginEnum;
import br.com.controledespesa.entity.Usuario;
import br.com.controledespesa.repository.UsuarioDaoImpl;

@RestController
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = -3654939268019465862L;

	@Autowired
	private UsuarioDaoImpl usuarioDao;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/usuarioLista")
	public List<Usuario> lista() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@RequestMapping(value = "/getUsuario")
	public Usuario getUsuario(@RequestParam(value = "id", defaultValue = "1") Long id) {
		Usuario usuario = usuarioDao.getById(id);
		return usuario;
	}

	@RequestMapping(value = "/getUsuarioByEmailAndSenha")
	public Usuario getUsuarioByEmailAndSenha(@RequestParam(value = "email") String email, @RequestParam(value = "senha") String senha) {
		Usuario usuario = usuarioDao.getUsuarioByEmailAndSenha(email, senha, TipoLoginEnum.SISTEMA);
		return usuario;
	}

	@RequestMapping(value = "/getUsuarioByEmail")
	public Usuario getUsuarioByEmail(@RequestParam(value = "email") String email) {
		Usuario usuario = usuarioDao.getUsuarioByEmail(email, TipoLoginEnum.SISTEMA);
		return usuario;
	}

	@RequestMapping(value = "/usuarioSalvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Usuario salvar(@RequestBody Usuario usuario) {
		usuarioDao.save(usuario);
		return usuario;
	}

}
