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

import br.com.controledespesa.data.vo.FormaPagamentoVO;
import br.com.controledespesa.service.FormaPagamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Forma Pagamento Endpoint", tags = { "FormaPagamentoEndpoint" })
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@GetMapping(value = "/findAllFormaPagamento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de forma de pagamento"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	public List<FormaPagamentoVO> findAll() {
		List<FormaPagamentoVO> formasPagamentoVO = formaPagamentoService.findAll();
		formasPagamentoVO.stream().forEach(form -> addLinkByFormaPagamento(form));

		return formasPagamentoVO;
	}

	@GetMapping(value = "/getFormaPagamento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma forma de pagamento"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id da forma de pagamento", defaultValue = "1", required = true, dataTypeClass = Long.class) })
	public FormaPagamentoVO getFormaPagamento(@RequestParam(value = "id", defaultValue = "1") Long id) {
		FormaPagamentoVO formaPagamentoVO = formaPagamentoService.getById(id);
		addLinkByFormaPagamento(formaPagamentoVO);

		return formaPagamentoVO;
	}

	@PostMapping(value = "/saveFormaPagamento", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "salvar forma de pagamento"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "formaPagamento", value = "forma de pagamento", required = true, dataTypeClass = FormaPagamentoVO.class) })
	public FormaPagamentoVO save(@RequestBody FormaPagamentoVO formaPagamentoVO) {

		if (formaPagamentoVO.getKey() != null) {
			formaPagamentoService.update(formaPagamentoVO);
			return formaPagamentoVO;
		}

		formaPagamentoService.save(formaPagamentoVO);
		addLinkByFormaPagamento(formaPagamentoVO);

		return formaPagamentoVO;
	}

	@DeleteMapping(value = "/deleteFormaPagamento", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deletar forma de pagamento"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "formaPagamento", value = "forma de pagamento", required = true, dataTypeClass = FormaPagamentoVO.class) })
	public ResponseEntity<FormaPagamentoVO> delete(@RequestBody FormaPagamentoVO formaPagamentoVO) {
		formaPagamentoService.delete(formaPagamentoVO);

		return ResponseEntity.ok().build();
	}

	private void addLinkByFormaPagamento(FormaPagamentoVO formaPagamentoVO) {
		if (formaPagamentoVO == null) {
			return;
		}

		formaPagamentoVO.add(linkTo(methodOn(FormaPagamentoController.class).getFormaPagamento(formaPagamentoVO.getKey())).withRel("get-FormaPagamento"));
	}

}
