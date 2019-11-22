package br.com.controledespesa.autenticacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class GoogleAuthenticator {

	private static final Logger logger = LoggerFactory.getLogger(GoogleAuthenticator.class);

	@Autowired
	private UsuarioDao usuarioDao;

	@GetMapping(value = "/loginGoogle")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna usuário que será logado"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "nome", value = "nome do usuário", required = true, dataTypeClass = String.class),
			@ApiImplicitParam(name = "email", value = "e-mail do usuário", required = true, dataTypeClass = String.class) })
	public Usuario getLogin(@RequestParam(value = "nome") String nome, @RequestParam(value = "email") String email) {
		logger.info(String.format("email: %s, nome: %s", email, nome));

		Usuario usuario = usuarioDao.getUsuarioByEmail(email, TipoLoginEnum.GOOGLE);
		if (usuario == null) {
			usuario = new Usuario(nome, email);
			usuario.setTipoLogin(TipoLoginEnum.GOOGLE.getChave());

			usuarioDao.save(usuario);
		}

		return usuario;
	}

}
