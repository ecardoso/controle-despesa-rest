package br.com.controledespesa.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
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

import br.com.controledespesa.entity.Despesa;
import br.com.controledespesa.entity.MelhorDataCompra;
import br.com.controledespesa.helper.Calculadora;
import br.com.controledespesa.helper.DataHelper;
import br.com.controledespesa.repository.DespesaDao;
import br.com.controledespesa.repository.MelhorDataCompraDao;
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
	private transient DespesaDao despesaDao;

	@Autowired
	private transient MelhorDataCompraDao melhorDataCompraDaoImpl;

	@GetMapping(value = "/findAllDespesa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	public List<Despesa> findAll() {
		List<Despesa> despesas = despesaDao.findAll();
		despesas.stream().forEach(desp -> addLinkByDespesa(desp));

		return despesas;
	}

	@GetMapping(value = "/findDespesaListaByMes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de despesa do mês"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "usuario", value = "id do usuário", required = true, dataTypeClass = String.class),
							@ApiImplicitParam(name = "dataInicial", value = "data inicial", required = true, dataTypeClass = String.class),
							@ApiImplicitParam(name = "dataFinal", value = "data final", required = true, dataTypeClass = String.class) })
	public List<Despesa> findDespesaListaByMes(@RequestParam(value = "usuario") String idUsuario, @RequestParam(value = "dataInicial") String pDataInicial,
							@RequestParam(value = "dataFinal") String pDataFinal) throws ParseException {
		Date dataInicial = DataHelper.converterStringParaDate(pDataInicial);
		Date dataFinal = DataHelper.converterStringParaDate(pDataFinal);

		List<Despesa> despesas = despesaDao.findByMes(idUsuario, dataInicial, dataFinal);
		despesas.stream().forEach(desp -> addLinkByDespesa(desp));

		return despesas;
	}

	@GetMapping(value = "/getDespesa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id do usuário", defaultValue = "1", required = true, dataTypeClass = Long.class) })
	public Despesa getDespesa(@RequestParam(value = "id", defaultValue = "1") Long id) {
		Despesa despesa = despesaDao.getById(id);
		addLinkByDespesa(despesa);

		return despesa;
	}

	@SuppressWarnings({ "deprecation" })
	@PostMapping(value = "/saveDespesa", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "salvar a despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "despesa", value = "despesa", required = true, dataTypeClass = Despesa.class) })
	public Despesa salvar(@RequestBody Despesa despesa) {
		MelhorDataCompra melhorDataCompra = melhorDataCompraDaoImpl.getMelhorDataCompra(despesa.getUsuario(), despesa.getFormaPagamento(), despesa.getDataCompra());

		if (despesa.getId() != null) {

			if (melhorDataCompra != null) {
				despesa.setDataPagamento(despesa.getDataCompra());
			}

			return despesaDao.update(despesa);
		}

		int quantidadeParcelas = despesa.getQuantidadeParcelas();

		for (int i = 1; i <= quantidadeParcelas; i++) {
			despesa.setDataPagamento(despesa.getDataCompra());

			Despesa novaDespesa = novaDespesa(despesa);

			if (quantidadeParcelas > 1) {
				novaDespesa.setDescricao(despesa.getDescricao().concat(String.format(" Parcela %s de %s", i, quantidadeParcelas)));
			}

			novaDespesa.setValor(Calculadora.dividir(despesa.getValor(), new BigDecimal(quantidadeParcelas)));

			if (melhorDataCompra != null) {
				boolean melhorData = melhorDataCompra.getDataMelhorCompra() <= despesa.getDataCompra().getDate();
				int addMes = melhorData ? 1 : 0;

				novaDespesa.setDataPagamento(DataHelper.addMesByDia(melhorDataCompra.getDataPagamento(), despesa.getDataCompra().getMonth(), i + addMes));
			}

			despesaDao.save(novaDespesa);
		}

		addLinkByDespesa(despesa);
		return despesa;
	}

	@DeleteMapping(value = "/deleteDespesaById")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deletar a despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id do usuário", required = true, dataTypeClass = Long.class) })
	public ResponseEntity<Despesa> deleteDespesaById(@RequestParam(value = "id") Long id) {
		Despesa despesa = despesaDao.getById(id);
		delete(despesa);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/deleteDespesa", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deletar a despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
							@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "despesa", value = "despesa", required = true, dataTypeClass = Despesa.class) })
	public ResponseEntity<Despesa> delete(@RequestBody Despesa despesa) {
		despesaDao.delete(despesa);

		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/updateDespesaParaPagoByMes", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "atualizar as despasa para pago do mês referente"),
							@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"), @ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "despesa", value = "despesa", required = true, dataTypeClass = Despesa.class) })
	public ResponseEntity<Despesa> updateDespesaParaPagoByMes(@RequestBody Despesa despesa) {
		Date data = despesa.getDataCompra();
		Date dataInicial = DataHelper.getPrimeiroDiaDoMes(data);
		Date dataFinal = DataHelper.getUltimoDiaDoMes(data);

		List<Despesa> despesas = despesaDao.findByMes(despesa.getUsuario().getId().toString(), dataInicial, dataFinal);

		for (Despesa value : despesas) {

			if (!value.isPago()) {
				value.setPago(true);
				despesaDao.update(value);
			}
		}

		return ResponseEntity.ok().build();
	}

	private Despesa novaDespesa(Despesa despesa) {
		Despesa novaDespesa = new Despesa();
		novaDespesa.setCategoria(despesa.getCategoria());
		novaDespesa.setUsuario(despesa.getUsuario());
		novaDespesa.setFormaPagamento(despesa.getFormaPagamento());
		novaDespesa.setDataCompra(despesa.getDataCompra());
		novaDespesa.setDataPagamento(despesa.getDataPagamento());
		novaDespesa.setDescricao(despesa.getDescricao());
		novaDespesa.setValor(despesa.getValor());
		novaDespesa.setDespesaFixa(despesa.isDespesaFixa());
		novaDespesa.setPago(despesa.isPago());
		novaDespesa.setQuantidadeParcelas(despesa.getQuantidadeParcelas());

		return novaDespesa;
	}

	private void addLinkByDespesa(Despesa despesa) {
		if (despesa == null) {
			return;
		}

		despesa.add(linkTo(methodOn(DespesaController.class).getDespesa(despesa.getKey())).withRel("get-Despesa"));
		despesa.add(linkTo(methodOn(CategoriaController.class).getCategoria(despesa.getCategoria().getKey())).withRel("get-Categoria"));
		despesa.add(linkTo(methodOn(UsuarioController.class).getUsuario(despesa.getUsuario().getKey())).withRel("get-Usuario"));
		despesa.add(linkTo(methodOn(FormaPagamentoController.class).getFormaPagamento(despesa.getFormaPagamento().getKey())).withRel("get-FormaPagamento"));
	}

}
