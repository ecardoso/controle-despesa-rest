package br.com.controledespesa.rest;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = -3654939268019465862L;

	/*@Autowired
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
	
	@RequestMapping(value = "/usuarioSalvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Usuario salvar(@RequestBody Usuario usuario) {
		usuarioDao.save(usuario);
		return usuario;
	}*/

}
