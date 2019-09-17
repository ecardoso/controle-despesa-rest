package br.com.controledespesa.rest;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.Despesa;
import br.com.controledespesa.helper.DataHelper;
import br.com.controledespesa.repository.DespesaDaoImpl;

@RestController
public class DespesaService implements Serializable {

	private static final long serialVersionUID = 3927947824384666134L;

	@Autowired
	private DespesaDaoImpl despesaDao;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/despesaLista")
	public List<Despesa> lista() {
		return (List<Despesa>) despesaDao.findAll();
	}

	@RequestMapping(value = "/findDespesaListaByMes")
	public List<Despesa> listaByMes(@RequestParam(value = "data") String dataParam) throws ParseException {
		Date data = DataHelper.converterStringParaDate(dataParam);
		Date dataInicial = DataHelper.getPrimeiroDiaDoMes(data);
		Date dataFinal = DataHelper.getUltimoDiaDoMes(data);

		return despesaDao.findByMes(dataInicial, dataFinal);
	}

	@RequestMapping(value = "/getDespesa")
	public Despesa getDespesa(@RequestParam(value = "id", defaultValue = "1") Long id) {
		Despesa despesa = despesaDao.getById(id);
		return despesa;
	}

	@RequestMapping(value = "/despesaSalvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Despesa salvar(@RequestBody Despesa despesa) {
		if (despesa.getId() == null) {
			despesaDao.save(despesa);
		} else {
			despesaDao.update(despesa);
		}

		return despesa;
	}

	@RequestMapping(value = "/despesaDeletar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deletar(@RequestBody Despesa despesa) {
		despesaDao.delete(despesa);
	}

}
