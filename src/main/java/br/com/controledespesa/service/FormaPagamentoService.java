package br.com.controledespesa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.controledespesa.converter.DozerConverter;
import br.com.controledespesa.data.model.FormaPagamento;
import br.com.controledespesa.data.vo.FormaPagamentoVO;
import br.com.controledespesa.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	public List<FormaPagamentoVO> findAll() {
		return DozerConverter.parseListObjects(formaPagamentoRepository.findAll(Sort.by(Sort.Direction.ASC, "descricao")), FormaPagamentoVO.class);
	}

	public FormaPagamentoVO getById(Long id) {
		return DozerConverter.parseObject(formaPagamentoRepository.findById(id), FormaPagamentoVO.class);
	}

	public FormaPagamentoVO save(FormaPagamentoVO formaPagamentoVO) {
		FormaPagamento entity = DozerConverter.parseObject(formaPagamentoVO, FormaPagamento.class);
		formaPagamentoRepository.save(entity);

		return DozerConverter.parseObject(entity, FormaPagamentoVO.class);
	}

	public void delete(FormaPagamentoVO formaPagamentoVO) {
		FormaPagamento entity = DozerConverter.parseObject(formaPagamentoVO, FormaPagamento.class);
		formaPagamentoRepository.delete(entity);
	}

}
