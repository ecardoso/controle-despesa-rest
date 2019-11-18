package br.com.controledespesa.rest;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.Usuario;
import br.com.controledespesa.enums.TipoLoginEnum;
import br.com.controledespesa.repository.UsuarioDao;

@RestController
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -3654939268019465862L;

	@Autowired
	private transient UsuarioDao usuarioDao;

	@GetMapping(value = "/findAllUsuario")
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@GetMapping(value = "/getUsuario")
	public Usuario getUsuario(@RequestParam(value = "id", defaultValue = "1") Long id) {
		return usuarioDao.getById(id);
	}

	@GetMapping(value = "/getUsuarioByEmailAndSenha")
	public Usuario getUsuarioByEmailAndSenha(@RequestParam(value = "email") String email, @RequestParam(value = "senha") String senha) {
		return usuarioDao.getUsuarioByEmailAndSenha(email, senha, TipoLoginEnum.SISTEMA);
	}

	@GetMapping(value = "/getUsuarioByEmail")
	public Usuario getUsuarioByEmail(@RequestParam(value = "email") String email) {
		return usuarioDao.getUsuarioByEmail(email, TipoLoginEnum.SISTEMA);
	}

	@PostMapping(value = "/saveUsuario", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Usuario save(@RequestBody Usuario usuario) {
		usuario.setTipoLogin(TipoLoginEnum.SISTEMA.getChave());
		return usuarioDao.save(usuario);
	}

}
