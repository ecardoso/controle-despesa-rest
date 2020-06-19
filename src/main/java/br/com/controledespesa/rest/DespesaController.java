package br.com.controledespesa.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.data.vo.DespesaVO;
import br.com.controledespesa.data.vo.MelhorDataCompraVO;
import br.com.controledespesa.helper.Calculadora;
import br.com.controledespesa.helper.DataHelper;
import br.com.controledespesa.service.DespesaService;
import br.com.controledespesa.service.MelhorDataCompraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Despesa Endpoint", tags = { "DespesaEndpoint" })
public class DespesaController implements Serializable {

	private static final long serialVersionUID = 3927947824384666134L;

	@Autowired
	private transient DespesaService despesaService;

	@Autowired
	private transient MelhorDataCompraService melhorDataCompraService;

	@GetMapping(value = "/findAllDespesa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	public List<DespesaVO> findAll() {
		List<DespesaVO> despesaVO = despesaService.findAll();
		despesaVO.stream().forEach(desp -> addLinkByDespesa(desp));

		return despesaVO;
	}

	@GetMapping(value = "/findDespesaListaByMes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de despesa do mês"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "usuario", value = "id do usuário", required = true, dataTypeClass = String.class),
							@ApiImplicitParam(name = "dataInicial", value = "data inicial", required = true, dataTypeClass = String.class),
							@ApiImplicitParam(name = "dataFinal", value = "data final", required = true, dataTypeClass = String.class) })
	public List<DespesaVO> findDespesaListaByMes(@RequestParam(value = "usuario") String idUsuario, @RequestParam(value = "dataInicial") String pDataInicial,
							@RequestParam(value = "dataFinal") String pDataFinal) {

		LocalDateTime dataInicial = DataHelper.converterStringParaDate(pDataInicial);
		LocalDateTime dataFinal = DataHelper.converterStringParaDate(pDataFinal);
		dataFinal = DataHelper.setHora(dataFinal, 23, 59, 59);

		List<DespesaVO> despesaVO = despesaService.findByMes(Long.parseLong(idUsuario), dataInicial, dataFinal);
		despesaVO.stream().forEach(desp -> addLinkByDespesa(desp));

		return despesaVO;
	}

	@GetMapping(value = "/findDespesaListaByMesFormaPagamento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de despesa do mês"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "usuario", value = "id do usuário", required = true, dataTypeClass = String.class),
							@ApiImplicitParam(name = "data", value = "data", required = true, dataTypeClass = String.class),
							@ApiImplicitParam(name = "formaPagamento", value = "forma pagamento", required = true, dataTypeClass = String.class) })
	public List<DespesaVO> findDespesaListaByMesFormaPagamento(@RequestParam(value = "usuario") String idUsuario, @RequestParam(value = "data") String pData,
							@RequestParam(value = "formaPagamento") String formaPagamento) {

		LocalDateTime data = DataHelper.converterStringParaDate(pData);
		LocalDateTime dataInicial = DataHelper.getPrimeiroDiaDoMes(data);
		LocalDateTime dataFinal = DataHelper.getUltimoDiaDoMes(dataInicial);

		List<DespesaVO> despesaVO = despesaService.findByMesFormaPagamento(Long.parseLong(idUsuario), Long.parseLong(formaPagamento), dataInicial, dataFinal);
		despesaVO.stream().forEach(desp -> addLinkByDespesa(desp));

		return despesaVO;
	}

	@GetMapping(value = "/getDespesa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id do usuário", defaultValue = "1", required = true, dataTypeClass = Long.class) })
	public DespesaVO getDespesa(@RequestParam(value = "id", defaultValue = "1") Long id) {
		DespesaVO despesaVO = despesaService.getById(id);
		addLinkByDespesa(despesaVO);

		return despesaVO;
	}

	@PostMapping(value = "/saveDespesa", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "salvar a despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "despesa", value = "despesa", required = true, dataTypeClass = DespesaVO.class) })
	public DespesaVO salvar(@RequestBody DespesaVO despesaVO) {
		MelhorDataCompraVO melhorDataCompraVO = melhorDataCompraService.getMelhorDataCompra(despesaVO.getUsuario(), despesaVO.getFormaPagamento(), despesaVO.getDataCompra());

		if (despesaVO.getKey() != null) {
			if (melhorDataCompraVO != null) {
				despesaVO.setDataPagamento(despesaVO.getDataCompra());
			}

			return despesaService.update(despesaVO);
		}

		int quantidadeParcelas = despesaVO.getQuantidadeParcelas();
		for (int i = 1; i <= quantidadeParcelas; i++) {
			despesaVO.setDataPagamento(despesaVO.getDataCompra());

			DespesaVO novaDespesa = novaDespesa(despesaVO);

			if (quantidadeParcelas > 1) {
				novaDespesa.setDescricao(despesaVO.getDescricao().concat(String.format(" Parcela %s de %s", i, quantidadeParcelas)));
			}

			novaDespesa.setValor(Calculadora.dividir(despesaVO.getValor(), new BigDecimal(quantidadeParcelas)));

			if (melhorDataCompraVO != null) {
				boolean melhorData = melhorDataCompraVO.getDataMelhorCompra() <= despesaVO.getDataCompra().getDayOfMonth();
				long addMes = melhorData ? 1l : 0l;
				novaDespesa.setDataPagamento(despesaVO.getDataCompra().withDayOfMonth(melhorDataCompraVO.getDataPagamento()).plusMonths(i + addMes));
			}

			despesaService.save(novaDespesa);
		}

		addLinkByDespesa(despesaVO);
		return despesaVO;
	}

	@DeleteMapping(value = "/deleteDespesaById")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deletar a despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id do usuário", required = true, dataTypeClass = Long.class) })
	public ResponseEntity<DespesaVO> deleteDespesaById(@RequestParam(value = "id") Long id) {
		DespesaVO despesa = despesaService.getById(id);
		delete(despesa);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/deleteDespesa", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deletar a despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "despesa", value = "despesa", required = true, dataTypeClass = DespesaVO.class) })
	public ResponseEntity<DespesaVO> delete(@RequestBody DespesaVO despesaVO) {
		despesaService.delete(despesaVO);

		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/updateDespesaParaPagoByMes", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "atualizar as despasa para pago do mês referente"),
							@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"), @ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "despesa", value = "despesa", required = true, dataTypeClass = DespesaVO.class) })
	public ResponseEntity<DespesaVO> updateDespesaParaPagoByMes(@RequestBody DespesaVO despesaVO) {
		LocalDateTime data = despesaVO.getDataCompra();
		LocalDateTime dataInicial = DataHelper.getPrimeiroDiaDoMes(data);
		LocalDateTime dataFinal = DataHelper.getUltimoDiaDoMes(data);

		List<DespesaVO> despesas = despesaService.findByMes(despesaVO.getUsuario().getKey(), dataInicial, dataFinal);

		for (DespesaVO value : despesas) {

			if (!value.isPago()) {
				value.setPago(true);
				despesaService.update(value);
			}
		}

		return ResponseEntity.ok().build();
	}

	private DespesaVO novaDespesa(DespesaVO despesaVO) {
		DespesaVO novaDespesa = new DespesaVO();
		novaDespesa.setCategoria(despesaVO.getCategoria());
		novaDespesa.setUsuario(despesaVO.getUsuario());
		novaDespesa.setFormaPagamento(despesaVO.getFormaPagamento());
		novaDespesa.setDataCompra(despesaVO.getDataCompra());
		novaDespesa.setDataPagamento(despesaVO.getDataPagamento());
		novaDespesa.setDescricao(despesaVO.getDescricao());
		novaDespesa.setValor(despesaVO.getValor());
		novaDespesa.setDespesaFixa(despesaVO.isDespesaFixa());
		novaDespesa.setPago(despesaVO.isPago());
		novaDespesa.setQuantidadeParcelas(despesaVO.getQuantidadeParcelas());

		return novaDespesa;
	}

	private void addLinkByDespesa(DespesaVO despesaVO) {
		if (despesaVO == null) {
			return;
		}

		despesaVO.add(linkTo(methodOn(DespesaController.class).getDespesa(despesaVO.getKey())).withRel("get-Despesa"));
		despesaVO.add(linkTo(methodOn(CategoriaController.class).getCategoria(despesaVO.getCategoria().getKey())).withRel("get-Categoria"));
		despesaVO.add(linkTo(methodOn(UsuarioController.class).getUsuario(despesaVO.getUsuario().getKey())).withRel("get-Usuario"));
		despesaVO.add(linkTo(methodOn(FormaPagamentoController.class).getFormaPagamento(despesaVO.getFormaPagamento().getKey())).withRel("get-FormaPagamento"));
	}

}
