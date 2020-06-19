package br.com.controledespesa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controledespesa.converter.DozerConverter;
import br.com.controledespesa.data.model.Despesa;
import br.com.controledespesa.data.vo.DespesaVO;
import br.com.controledespesa.repository.DespesaDao;

@Service
public class DespesaService {

	@Autowired
	private DespesaDao despesaDao;

	public List<DespesaVO> findAll() {
		return DozerConverter.parseListObjects(despesaDao.findAll(), DespesaVO.class);
	}

	public DespesaVO getById(Long id) {
		return DozerConverter.parseObject(despesaDao.getById(id), DespesaVO.class);
	}

	public List<DespesaVO> findByMes(Long idUsuario, LocalDateTime dataInicial, LocalDateTime dataFinal) {
		return DozerConverter.parseListObjects(despesaDao.findByMes(idUsuario, dataInicial, dataFinal), DespesaVO.class);
	}

	public List<DespesaVO> findByMesFormaPagamento(Long idUsuario, Long idFormaPagamento, LocalDateTime dataInicial, LocalDateTime dataFinal) {
		return DozerConverter.parseListObjects(despesaDao.findByMesFormaPagamento(idUsuario, idFormaPagamento, dataInicial, dataFinal), DespesaVO.class);
	}

	public DespesaVO save(DespesaVO despesaVO) {
		Despesa entity = DozerConverter.parseObject(despesaVO, Despesa.class);
		despesaDao.save(entity);

		return DozerConverter.parseObject(entity, DespesaVO.class);
	}

	public DespesaVO update(DespesaVO despesaVO) {
		Despesa entity = DozerConverter.parseObject(despesaVO, Despesa.class);
		despesaDao.update(entity);

		return DozerConverter.parseObject(entity, DespesaVO.class);
	}

	public void delete(DespesaVO despesaVO) {
		Despesa entity = DozerConverter.parseObject(despesaVO, Despesa.class);
		despesaDao.delete(entity);
	}
}
