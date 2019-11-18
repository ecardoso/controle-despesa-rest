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

import br.com.controledespesa.entity.FormaPagamento;
import br.com.controledespesa.repository.FormaPagamentoDao;

@RestController
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoDao formaPagamentoDao;

	@GetMapping(value = "/findAllFormaPagamento")
	public List<FormaPagamento> findAll() {
		return formaPagamentoDao.findAll();
	}

	@GetMapping(value = "/getFormaPagamento")
	public FormaPagamento getFormaPagamento(@RequestParam(value = "id", defaultValue = "1") Long id) {
		return formaPagamentoDao.getById(id);
	}

	@PostMapping(value = "/saveFormaPagamento", consumes = MediaType.APPLICATION_JSON_VALUE)
	public FormaPagamento save(@RequestBody FormaPagamento formaPagamento) {
		if (formaPagamento.getId() != null) {
			formaPagamentoDao.update(formaPagamento);
			return formaPagamento;
		}

		return formaPagamentoDao.save(formaPagamento);
	}

	@DeleteMapping(value = "/deleteFormaPagamento", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@RequestBody FormaPagamento formaPagamento) {
		formaPagamentoDao.delete(formaPagamento);
	}

}
