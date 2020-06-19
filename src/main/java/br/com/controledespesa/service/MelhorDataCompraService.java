package br.com.controledespesa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controledespesa.converter.DozerConverter;
import br.com.controledespesa.data.model.MelhorDataCompra;
import br.com.controledespesa.data.vo.FormaPagamentoVO;
import br.com.controledespesa.data.vo.MelhorDataCompraVO;
import br.com.controledespesa.data.vo.UsuarioVO;
import br.com.controledespesa.repository.MelhorDataCompraDao;

@Service
public class MelhorDataCompraService {

	@Autowired
	private MelhorDataCompraDao melhorDataCompraDao;

	public List<MelhorDataCompraVO> findAll() {
		return DozerConverter.parseListObjects(melhorDataCompraDao.findAll(), MelhorDataCompraVO.class);
	}

	public MelhorDataCompraVO getById(Long id) {
		return DozerConverter.parseObject(melhorDataCompraDao.getById(id), MelhorDataCompraVO.class);
	}

	public MelhorDataCompraVO getMelhorDataCompra(UsuarioVO usuarioVO, FormaPagamentoVO formaPagamentoVO, LocalDateTime data) {
		return DozerConverter.parseObject(melhorDataCompraDao.getMelhorDataCompra(usuarioVO, formaPagamentoVO, data), MelhorDataCompraVO.class);
	}

	public MelhorDataCompraVO save(MelhorDataCompraVO melhorDataCompraVO) {
		MelhorDataCompra entity = DozerConverter.parseObject(melhorDataCompraVO, MelhorDataCompra.class);
		melhorDataCompraDao.save(entity);

		return DozerConverter.parseObject(entity, MelhorDataCompraVO.class);
	}

	public MelhorDataCompraVO update(MelhorDataCompraVO melhorDataCompraVO) {
		MelhorDataCompra entity = DozerConverter.parseObject(melhorDataCompraVO, MelhorDataCompra.class);
		melhorDataCompraDao.update(entity);

		return DozerConverter.parseObject(entity, MelhorDataCompraVO.class);
	}

	public void delete(MelhorDataCompraVO melhorDataCompraVO) {
		MelhorDataCompra entity = DozerConverter.parseObject(melhorDataCompraVO, MelhorDataCompra.class);
		melhorDataCompraDao.delete(entity);
	}
}
