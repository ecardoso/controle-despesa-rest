package br.com.controledespesa.rest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controledespesa.entity.Despesa;
import br.com.controledespesa.entity.MelhorDataCompra;
import br.com.controledespesa.helper.Calculadora;
import br.com.controledespesa.helper.DataHelper;
import br.com.controledespesa.repository.DespesaDao;
import br.com.controledespesa.repository.MelhorDataCompraDao;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
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
		return despesaDao.findAll();
	}

	@GetMapping(value = "/findDespesaListaByMes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de despesa do mês"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "usuario", value = "id do usuário", required = true, dataTypeClass = String.class),
			@ApiImplicitParam(name = "data", value = "data", required = true, dataTypeClass = String.class) })
	public List<Despesa> findDespesaListaByMes(@RequestParam(value = "usuario") String idUsuario, @RequestParam(value = "data") String dataParam) throws ParseException {
		Date data = DataHelper.converterStringParaDate(dataParam);
		Date dataInicial = DataHelper.getPrimeiroDiaDoMes(data);
		Date dataFinal = DataHelper.getUltimoDiaDoMes(data);

		return despesaDao.findByMes(idUsuario, dataInicial, dataFinal);
	}

	@GetMapping(value = "/getDespesa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id do usuário", defaultValue = "1", required = true, dataTypeClass = Long.class) })
	public Despesa getDespesa(@RequestParam(value = "id", defaultValue = "1") Long id) {
		return despesaDao.getById(id);
	}

	@SuppressWarnings({ "deprecation" })
	@PostMapping(value = "/saveDespesa", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "salvar a despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "despesa", value = "despesa", required = true, dataTypeClass = Despesa.class) })
	public Despesa salvar(@RequestBody Despesa despesa) {
		MelhorDataCompra melhorDataCompra = melhorDataCompraDaoImpl.getMelhorDataCompra(despesa.getUsuario(), despesa.getFormaPagamento());
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

		return despesa;
	}

	@DeleteMapping(value = "/deleteDespesaById")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deletar a despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id do usuário", required = true, dataTypeClass = Long.class) })
	public void deleteDespesaById(@RequestParam(value = "id") Long id) {
		Despesa despesa = despesaDao.getById(id);
		delete(despesa);
	}

	@DeleteMapping(value = "/deleteDespesa", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "deletar a despesa"), @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "despesa", value = "despesa", required = true, dataTypeClass = Despesa.class) })
	public void delete(@RequestBody Despesa despesa) {
		despesaDao.delete(despesa);
	}

	@PostMapping(value = "/updateDespesaParaPagoByMes", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "atualizar as despasa para pago do mês referente"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"), @ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@ApiImplicitParams({ @ApiImplicitParam(name = "despesa", value = "despesa", required = true, dataTypeClass = Despesa.class) })
	public void updateDespesaParaPagoByMes(@RequestBody Despesa despesa) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, 01, 1);

		Date data = calendar.getTime();
		Date dataInicial = DataHelper.getPrimeiroDiaDoMes(data);
		Date dataFinal = DataHelper.getUltimoDiaDoMes(new Date());

		List<Despesa> despesas = despesaDao.findByMes(despesa.getUsuario().getId().toString(), dataInicial, dataFinal);
		for (Despesa value : despesas) {
			if (!value.isPago()) {
				value.setPago(true);
				despesaDao.update(value);
			}
		}
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

}
