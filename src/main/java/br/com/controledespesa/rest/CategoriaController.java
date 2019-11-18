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

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaDao categoriaDao;

	@GetMapping(value = "/findAllCategoria")
	public List<Categoria> findAll() {
		return categoriaDao.findAll();
	}

	@GetMapping(value = "/getCategoria")
	public Categoria getCategoria(@RequestParam(value = "id", defaultValue = "1") Long id) {
		return categoriaDao.getById(id);
	}

	@PostMapping(value = "/saveCategoria", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Categoria save(@RequestBody Categoria categoria) {
		return categoriaDao.save(categoria);
	}

}
