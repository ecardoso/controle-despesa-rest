package br.com.controledespesa.autenticacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.data.vo.UsuarioVO;
import br.com.controledespesa.enums.TipoLoginEnum;
import br.com.controledespesa.service.UsuarioService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class GoogleAuthenticator {
	

	private static final Logger logger = LoggerFactory.getLogger(GoogleAuthenticator.class);

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(value = "/loginGoogle")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna usuário que será logado"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "nome", value = "nome do usuário", required = true, dataTypeClass = String.class),
							@ApiImplicitParam(name = "email", value = "e-mail do usuário", required = true, dataTypeClass = String.class) })
	public UsuarioVO getLogin(@RequestParam(value = "nome") String nome, @RequestParam(value = "email") String email) {
		logger.info("email: {} nome: {}", email, nome);

		UsuarioVO usuarioVO = usuarioService.getUsuarioByEmailAndTipoLoginEnum(email, TipoLoginEnum.GOOGLE);
		if (usuarioVO == null) {
			usuarioVO = new UsuarioVO(nome, email);
			usuarioVO.setTipoLogin(TipoLoginEnum.GOOGLE.getChave());

			usuarioService.save(usuarioVO);
		}

		return usuarioVO;
	}

}
