package br.com.controledespesa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.Categoria;
import br.com.controledespesa.repository.CategoriaDao;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaDao categoriaDao;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/categoriaLista")
	public List<Categoria> lista() {
		return (List<Categoria>) categoriaDao.findAll();
	}

	@RequestMapping(value = "/getCategoria")
	public Categoria getCategoria(@RequestParam(value = "id", defaultValue = "1") Long id) {
		Categoria categoria = categoriaDao.getById(id);
		return categoria;
	}

	@RequestMapping(value = "/categoriaSalvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Categoria categoriaSalvar(@RequestBody Categoria categoria) {
		categoriaDao.save(categoria);
		return categoria;
	}

}
