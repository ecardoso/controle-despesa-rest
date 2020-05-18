package br.com.controledespesa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.FormaPagamento;
import br.com.controledespesa.repository.FormaPagamentoDao;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoDao formaPagamentoDao;

	@GetMapping(value = "/findAllFormaPagamento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de forma de pagamento"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	public List<FormaPagamento> findAll() {
		return formaPagamentoDao.findAll();
	}

	@GetMapping(value = "/getFormaPagamento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma forma de pagamento"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id da forma de pagamento", defaultValue = "1", required = true, dataTypeClass = Long.class) })
	public FormaPagamento getFormaPagamento(@RequestParam(value = "id", defaultValue = "1") Long id) {
		return formaPagamentoDao.getById(id);
	}

	@PostMapping(value = "/saveFormaPagamento", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "salvar forma de pagamento"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "formaPagamento", value = "forma de pagamento", required = true, dataTypeClass = FormaPagamento.class) })
	public FormaPagamento save(@RequestBody FormaPagamento formaPagamento) {
		if (formaPagamento.getId() != null) {
			formaPagamentoDao.update(formaPagamento);
			return formaPagamento;
		}

		return formaPagamentoDao.save(formaPagamento);
	}

	@PostMapping(value = "/deleteFormaPagamento", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deletar forma de pagamento"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "formaPagamento", value = "forma de pagamento", required = true, dataTypeClass = FormaPagamento.class) })
	public void delete(@RequestBody FormaPagamento formaPagamento) {
		formaPagamentoDao.delete(formaPagamento);
	}

}
