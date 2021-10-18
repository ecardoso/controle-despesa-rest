package br.com.controledespesa.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.data.vo.CategoriaVO;
import br.com.controledespesa.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Categoria Endpoint", tags = { "CategoriaEndpoint" })
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping(value = "/findAllCategoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de categoria"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	public List<CategoriaVO> findAll() {
		List<CategoriaVO> categoriaVO = categoriaService.findAll();
		categoriaVO.stream().forEach(categ -> addLinkByGetCategoria(categ));

		return categoriaVO;
	}
	
	
	@GetMapping(value = "/findTeste")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de teste"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	public String findTeste() {
		return "Sucesso - OK";
	}

	@GetMapping(value = "/getCategoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma categoria"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id da categoria", defaultValue = "1", required = true, dataTypeClass = Long.class) })
	public CategoriaVO getCategoria(@RequestParam(value = "id", defaultValue = "1") Long id) {
		CategoriaVO categoriaVO = categoriaService.getById(id);
		addLinkByGetCategoria(categoriaVO);

		return categoriaVO;
	}

	@PostMapping(value = "/saveCategoria", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "salvar categoria"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "categoria", value = "categoria", required = true, dataTypeClass = CategoriaVO.class) })
	public CategoriaVO save(@RequestBody CategoriaVO categoriaVO) {
		categoriaService.save(categoriaVO);
		addLinkByGetCategoria(categoriaVO);

		return categoriaVO;
	}

	private void addLinkByGetCategoria(CategoriaVO categoriaVO) {
		if (categoriaVO == null) {
			return;
		}

		categoriaVO.add(linkTo(methodOn(CategoriaController.class).getCategoria(categoriaVO.getKey())).withRel("get-Categoria"));
	}

}
