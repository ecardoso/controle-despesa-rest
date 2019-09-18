package br.com.controledespesa.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.Categoria;

@RestController
public class CategoriaService {

	//@Autowired
	//private CategoriaDaoImpl categoriaDao;

	@RequestMapping(value = "/categoriaLista")
	public List<Categoria> lista() {
		Categoria categoria = new Categoria();
		categoria.setDescricao("teste 123");
		List<Categoria> categorias = new ArrayList<Categoria>();
		categorias.add(categoria);

		return categorias;
		//return (List<Categoria>) categoriaDao.findAll();
	}

	/*@RequestMapping(value = "/getCategoria")
	public Categoria getCategoria(@RequestParam(value = "id", defaultValue = "1") Long id) {
		Categoria categoria = categoriaDao.getById(id);
		return categoria;
	}
	
	@RequestMapping(value = "/categoriaSalvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Categoria categoriaSalvar(@RequestBody Categoria categoria) {
		categoriaDao.save(categoria);
		return categoria;
	}*/

}
