package br.com.controledespesa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.MelhorDataCompra;
import br.com.controledespesa.repository.MelhorDataCompraDao;

@RestController
public class MelhorDataCompraController {

	@Autowired
	private MelhorDataCompraDao melhorDataCompraDao;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/melhorDataCompraLista")
	public List<MelhorDataCompra> lista() {
		return (List<MelhorDataCompra>) melhorDataCompraDao.findAll();
	}

	@RequestMapping(value = "/getMelhorDataCompra")
	public MelhorDataCompra getMelhorDataCompra(@RequestParam(value = "id", defaultValue = "1") Long id) {
		MelhorDataCompra melhorDataCompra = melhorDataCompraDao.getById(id);
		return melhorDataCompra;
	}

	@RequestMapping(value = "/melhorDataCompraSalvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public MelhorDataCompra melhorDataCompraSalvar(@RequestBody MelhorDataCompra melhorDataCompra) {
		if (melhorDataCompra.getId() == null) {
			melhorDataCompraDao.save(melhorDataCompra);
		} else {
			melhorDataCompraDao.update(melhorDataCompra);
		}

		return melhorDataCompra;
	}

	@RequestMapping(value = "/melhorDataCompraDeletar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deletar(@RequestBody MelhorDataCompra melhorDataCompra) {
		melhorDataCompraDao.delete(melhorDataCompra);
	}

}
