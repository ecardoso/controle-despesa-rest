package br.com.controledespesa.rest;

import java.io.Serializable;
import java.math.BigDecimal;
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
import br.com.controledespesa.entity.MelhorDataCompra;
import br.com.controledespesa.helper.Calculadora;
import br.com.controledespesa.helper.DataHelper;
import br.com.controledespesa.repository.DespesaDaoImpl;
import br.com.controledespesa.repository.MelhorDataCompraDaoImpl;

@RestController
public class DespesaService implements Serializable {

	private static final long serialVersionUID = 3927947824384666134L;

	@Autowired
	private DespesaDaoImpl despesaDao;

	@Autowired
	private MelhorDataCompraDaoImpl melhorDataCompraDaoImpl;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/despesaLista")
	public List<Despesa> lista() {
		return (List<Despesa>) despesaDao.findAll();
	}

	@RequestMapping(value = "/findDespesaListaByMes")
	public List<Despesa> listaByMes(@RequestParam(value = "usuario") String idUsuario, @RequestParam(value = "data") String dataParam) throws ParseException {
		Date data = DataHelper.converterStringParaDate(dataParam);
		Date dataInicial = DataHelper.getPrimeiroDiaDoMes(data);
		Date dataFinal = DataHelper.getUltimoDiaDoMes(data);

		return despesaDao.findByMes(idUsuario, dataInicial, dataFinal);
	}

	@RequestMapping(value = "/getDespesa")
	public Despesa getDespesa(@RequestParam(value = "id", defaultValue = "1") Long id) {
		Despesa despesa = despesaDao.getById(id);
		return despesa;
	}

	@SuppressWarnings({ "deprecation" })
	@RequestMapping(value = "/despesaSalvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Despesa salvar(@RequestBody Despesa despesa) {
		if (despesa.getId() != null) {
			despesaDao.update(despesa);
			return despesa;
		}

		int quantidadeParcelas = despesa.getQuantidadeParcelas();
		if (quantidadeParcelas <= 1) {
			despesaDao.save(despesa);
			return despesa;
		}

		Integer pagamento = null;
		MelhorDataCompra melhorDataCompra = melhorDataCompraDaoImpl.getMelhorDataCompra(despesa.getUsuario(), despesa.getFormaPagamento());
		if (melhorDataCompra != null) {
			pagamento = melhorDataCompra.getDataPagamento();
		}

		for (int i = 1; i <= quantidadeParcelas; i++) {
			despesa.setDataPagamento(despesa.getDataCompra());

			Despesa novaDespesa = novaDespesa(despesa);
			novaDespesa.setDescricao(despesa.getDescricao().concat(String.format(" Parcela %s de %s", i, quantidadeParcelas)));
			novaDespesa.setValor(Calculadora.dividir(despesa.getValor(), new BigDecimal(quantidadeParcelas)));

			if (pagamento != null) {
				novaDespesa.setDataPagamento(DataHelper.addMesByDia(pagamento, despesa.getDataCompra().getMonth(), i));
			}

			despesaDao.save(novaDespesa);
		}

		return despesa;
	}

	@RequestMapping(value = "/despesaDeletar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deletar(@RequestBody Despesa despesa) {
		despesaDao.delete(despesa);
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
