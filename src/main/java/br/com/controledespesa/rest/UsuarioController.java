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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -3654939268019465862L;

	@Autowired
	private transient UsuarioDao usuarioDao;

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista usuário"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping(value = "/findAllUsuario")
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@GetMapping(value = "/getUsuario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um usuário"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id usuário", defaultValue = "1", required = true, dataTypeClass = Long.class) })
	public Usuario getUsuario(@RequestParam(value = "id", defaultValue = "1") Long id) {
		return usuarioDao.getById(id);
	}

	@GetMapping(value = "/getUsuarioByEmailAndSenha")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um usuário"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "email", value = "e-mail do usuário", required = true, dataTypeClass = String.class),
			@ApiImplicitParam(name = "senha", value = "senha do usuário", required = true, dataTypeClass = String.class) })
	public Usuario getUsuarioByEmailAndSenha(@RequestParam(value = "email") String email, @RequestParam(value = "senha") String senha) {
		return usuarioDao.getUsuarioByEmailAndSenha(email, senha, TipoLoginEnum.SISTEMA);
	}

	@GetMapping(value = "/getUsuarioByEmail")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um usuário"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "email", value = "e-mail do usuário", required = true, dataTypeClass = String.class) })
	public Usuario getUsuarioByEmail(@RequestParam(value = "email") String email) {
		return usuarioDao.getUsuarioByEmail(email, TipoLoginEnum.SISTEMA);
	}

	@PostMapping(value = "/saveUsuario", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "salvar usuário"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "usuario", value = "usuario", required = true, dataTypeClass = Usuario.class) })
	public Usuario save(@RequestBody Usuario usuario) {
		usuario.setTipoLogin(TipoLoginEnum.SISTEMA.getChave());
		return usuarioDao.save(usuario);
	}

}
