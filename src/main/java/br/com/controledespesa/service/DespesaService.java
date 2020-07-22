package br.com.controledespesa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controledespesa.converter.DozerConverter;
import br.com.controledespesa.data.model.Despesa;
import br.com.controledespesa.data.vo.DespesaVO;
import br.com.controledespesa.repository.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;

	public List<DespesaVO> findAll() {
		return DozerConverter.parseListObjects(despesaRepository.findAll(), DespesaVO.class);
	}

	public DespesaVO getById(Long id) {
		return DozerConverter.parseObject(despesaRepository.findById(id), DespesaVO.class);
	}

	public List<DespesaVO> findByMes(Long idUsuario, LocalDateTime dataInicial, LocalDateTime dataFinal) {
		return DozerConverter.parseListObjects(despesaRepository.findByMes(idUsuario, dataInicial, dataFinal), DespesaVO.class);
	}

	public List<DespesaVO> findByMesFormaPagamento(Long idUsuario, Long idFormaPagamento, LocalDateTime dataInicial, LocalDateTime dataFinal) {
		return DozerConverter.parseListObjects(despesaRepository.findByMesFormaPagamento(idUsuario, idFormaPagamento, dataInicial, dataFinal), DespesaVO.class);
	}

	public DespesaVO save(DespesaVO despesaVO) {
		Despesa entity = DozerConverter.parseObject(despesaVO, Despesa.class);
		despesaRepository.save(entity);

		return DozerConverter.parseObject(entity, DespesaVO.class);
	}

	public void delete(DespesaVO despesaVO) {
		Despesa entity = DozerConverter.parseObject(despesaVO, Despesa.class);
		despesaRepository.delete(entity);
	}
}
