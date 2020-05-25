package br.com.controledespesa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.Categoria;
import br.com.controledespesa.repository.CategoriaDao;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaDao categoriaDao;

	@GetMapping(value = "/findAllCategoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de categoria"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	public List<Categoria> findAll() {
		return categoriaDao.findAll();
	}

	@GetMapping(value = "/getCategoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma categoria"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id da categoria", defaultValue = "1", required = true, dataTypeClass = Long.class) })
	public Categoria getCategoria(@RequestParam(value = "id", defaultValue = "1") Long id) {
		return categoriaDao.getById(id);
	}

	@PostMapping(value = "/saveCategoria", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "salvar categoria"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "categoria", value = "categoria", required = true, dataTypeClass = Categoria.class) })
	public Categoria save(@RequestBody Categoria categoria) {
		return categoriaDao.save(categoria);
	}

}
