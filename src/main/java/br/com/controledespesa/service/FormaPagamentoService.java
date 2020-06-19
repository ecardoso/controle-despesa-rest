package br.com.controledespesa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controledespesa.converter.DozerConverter;
import br.com.controledespesa.data.model.FormaPagamento;
import br.com.controledespesa.data.vo.FormaPagamentoVO;
import br.com.controledespesa.repository.FormaPagamentoDao;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoDao formaPagamentoDao;

	public List<FormaPagamentoVO> findAll() {
		return DozerConverter.parseListObjects(formaPagamentoDao.findAll(), FormaPagamentoVO.class);
	}

	public FormaPagamentoVO getById(Long id) {
		return DozerConverter.parseObject(formaPagamentoDao.getById(id), FormaPagamentoVO.class);
	}

	public FormaPagamentoVO save(FormaPagamentoVO formaPagamentoVO) {
		FormaPagamento entity = DozerConverter.parseObject(formaPagamentoVO, FormaPagamento.class);
		formaPagamentoDao.save(entity);

		return DozerConverter.parseObject(entity, FormaPagamentoVO.class);
	}

	public FormaPagamentoVO update(FormaPagamentoVO formaPagamentoVO) {
		FormaPagamento entity = DozerConverter.parseObject(formaPagamentoVO, FormaPagamento.class);
		formaPagamentoDao.update(entity);

		return DozerConverter.parseObject(entity, FormaPagamentoVO.class);
	}

	public void delete(FormaPagamentoVO formaPagamentoVO) {
		FormaPagamento entity = DozerConverter.parseObject(formaPagamentoVO, FormaPagamento.class);
		formaPagamentoDao.delete(entity);
	}

}
