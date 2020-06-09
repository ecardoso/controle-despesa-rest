package br.com.controledespesa.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		List<FormaPagamento> formasPagamento = formaPagamentoDao.findAll();
		formasPagamento.stream().forEach(form -> addLinkByFormaPagamento(form));

		return formasPagamento;
	}

	@GetMapping(value = "/getFormaPagamento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma forma de pagamento"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id da forma de pagamento", defaultValue = "1", required = true, dataTypeClass = Long.class) })
	public FormaPagamento getFormaPagamento(@RequestParam(value = "id", defaultValue = "1") Long id) {
		FormaPagamento formaPagamento = formaPagamentoDao.getById(id);
		addLinkByFormaPagamento(formaPagamento);

		return formaPagamento;
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

		formaPagamentoDao.save(formaPagamento);
		addLinkByFormaPagamento(formaPagamento);

		return formaPagamento;
	}

	@DeleteMapping(value = "/deleteFormaPagamento", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deletar forma de pagamento"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "formaPagamento", value = "forma de pagamento", required = true, dataTypeClass = FormaPagamento.class) })
	public ResponseEntity<FormaPagamento> delete(@RequestBody FormaPagamento formaPagamento) {
		formaPagamentoDao.delete(formaPagamento);

		return ResponseEntity.ok().build();
	}

	private void addLinkByFormaPagamento(FormaPagamento formaPagamento) {
		if (formaPagamento == null) {
			return;
		}

		formaPagamento.add(linkTo(methodOn(FormaPagamentoController.class).getFormaPagamento(formaPagamento.getKey())).withRel("get-FormaPagamento"));
	}

}
