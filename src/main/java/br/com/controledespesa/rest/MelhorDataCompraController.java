package br.com.controledespesa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.MelhorDataCompra;
import br.com.controledespesa.repository.MelhorDataCompraDao;

@RestController
public class MelhorDataCompraController {

	@Autowired
	private MelhorDataCompraDao melhorDataCompraDao;

	@GetMapping(value = "/findAllMelhorDataCompra")
	public List<MelhorDataCompra> findAll() {
		return melhorDataCompraDao.findAll();
	}

	@GetMapping(value = "/getMelhorDataCompra")
	public MelhorDataCompra getMelhorDataCompra(@RequestParam(value = "id", defaultValue = "1") Long id) {
		return melhorDataCompraDao.getById(id);
	}

	@PostMapping(value = "/saveMelhorDataCompra", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MelhorDataCompra save(@RequestBody MelhorDataCompra melhorDataCompra) {
		if (melhorDataCompra.getId() == null) {
			melhorDataCompraDao.save(melhorDataCompra);
		} else {
			melhorDataCompraDao.update(melhorDataCompra);
		}

		return melhorDataCompra;
	}

	@DeleteMapping(value = "/deleteMelhorDataCompra", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@RequestBody MelhorDataCompra melhorDataCompra) {
		melhorDataCompraDao.delete(melhorDataCompra);
	}

}
