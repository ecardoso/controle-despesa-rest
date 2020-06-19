package br.com.controledespesa.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.data.vo.UsuarioVO;
import br.com.controledespesa.enums.TipoLoginEnum;
import br.com.controledespesa.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Usuario Endpoint", tags = { "UsuarioEndpoint" })
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -3654939268019465862L;

	@Autowired
	private transient UsuarioService usuarioService;

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista usuário"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping(value = "/findAllUsuario")
	public List<UsuarioVO> findAll() {
		List<UsuarioVO> usuarioVO = usuarioService.findAll();
		usuarioVO.stream().forEach(usu -> addLinkByGetUsuario(usu));

		return usuarioVO;
	}

	@GetMapping(value = "/getUsuario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um usuário"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id usuário", defaultValue = "1", required = true, dataTypeClass = Long.class) })
	public UsuarioVO getUsuario(@RequestParam(value = "id", defaultValue = "1") Long id) {
		UsuarioVO usuarioVO = usuarioService.getById(id);
		addLinkByGetUsuario(usuarioVO);

		return usuarioVO;
	}

	@GetMapping(value = "/getUsuarioByEmailAndSenha")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um usuário"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "email", value = "e-mail do usuário", required = true, dataTypeClass = String.class),
							@ApiImplicitParam(name = "senha", value = "senha do usuário", required = true, dataTypeClass = String.class) })
	public UsuarioVO getUsuarioByEmailAndSenha(@RequestParam(value = "email") String email, @RequestParam(value = "senha") String senha) {
		UsuarioVO usuarioVO = usuarioService.getUsuarioByEmailAndSenhaAndTipoLogin(email, senha, TipoLoginEnum.SISTEMA);
		addLinkByGetUsuario(usuarioVO);

		return usuarioVO;
	}

	@GetMapping(value = "/getUsuarioByEmail")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um usuário"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "email", value = "e-mail do usuário", required = true, dataTypeClass = String.class) })
	public UsuarioVO getUsuarioByEmail(@RequestParam(value = "email") String email) {
		UsuarioVO usuarioVO = usuarioService.getUsuarioByEmailAndTipoLoginEnum(email, TipoLoginEnum.SISTEMA);
		addLinkByGetUsuarioAndEmail(usuarioVO);

		return usuarioVO;
	}

	@PostMapping(value = "/saveUsuario", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "salvar usuário"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "usuario", value = "usuario", required = true, dataTypeClass = UsuarioVO.class) })
	public UsuarioVO save(@RequestBody UsuarioVO usuarioVO) {
		usuarioVO.setTipoLogin(TipoLoginEnum.SISTEMA.getChave());
		usuarioService.save(usuarioVO);

		addLinkByGetUsuarioAndEmail(usuarioVO);
		return usuarioVO;
	}

	private void addLinkByGetUsuario(UsuarioVO usuarioVO) {
		if (usuarioVO == null) {
			return;
		}

		usuarioVO.add(linkTo(methodOn(UsuarioController.class).getUsuario(usuarioVO.getKey())).withSelfRel());
	}

	private void addLinkByGetUsuarioAndEmail(UsuarioVO usuarioVO) {
		if (usuarioVO == null) {
			return;
		}

		addLinkByGetUsuario(usuarioVO);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).getUsuarioByEmail(usuarioVO.getEmail())).withRel("get-Usuario"));
	}

}
