package br.com.controledespesa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.FormaPagamento;
import br.com.controledespesa.repository.FormaPagamentoDao;

@RestController
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoDao formaPagamentoDao;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/formaPagamentoLista")
	public List<FormaPagamento> lista() {
		return (List<FormaPagamento>) formaPagamentoDao.findAll();
	}

	@RequestMapping(value = "/getFormaPagamento")
	public FormaPagamento getFormaPagamento(@RequestParam(value = "id", defaultValue = "1") Long id) {
		FormaPagamento formaPagamento = formaPagamentoDao.getById(id);
		return formaPagamento;
	}

	@RequestMapping(value = "/formaPagamentoSalvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public FormaPagamento formaPagamentoSalvar(@RequestBody FormaPagamento formaPagamento) {
		formaPagamentoDao.save(formaPagamento);
		return formaPagamento;
	}

}
