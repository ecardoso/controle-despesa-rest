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
import br.com.controledespesa.helper.DataHelper;
import br.com.controledespesa.repository.MelhorDataCompraRepository;

@Service
public class MelhorDataCompraService {

	@Autowired
	private MelhorDataCompraRepository melhorDataCompraRepository;

	public List<MelhorDataCompraVO> findAll() {
		return DozerConverter.parseListObjects(melhorDataCompraRepository.findAll(), MelhorDataCompraVO.class);
	}

	public MelhorDataCompraVO getById(Long id) {
		return DozerConverter.parseObject(melhorDataCompraRepository.findById(id), MelhorDataCompraVO.class);
	}

	public MelhorDataCompraVO getMelhorDataCompra(UsuarioVO usuarioVO, FormaPagamentoVO formaPagamentoVO, LocalDateTime data) {
		return DozerConverter.parseObject(
								melhorDataCompraRepository.getMelhorDataCompra(usuarioVO.getKey(), formaPagamentoVO.getKey(), DataHelper.getPrimeiroDiaDoMes(data), DataHelper.getUltimoDiaDoMes(data)),
								MelhorDataCompraVO.class);
	}

	public MelhorDataCompraVO save(MelhorDataCompraVO melhorDataCompraVO) {
		MelhorDataCompra entity = DozerConverter.parseObject(melhorDataCompraVO, MelhorDataCompra.class);
		melhorDataCompraRepository.save(entity);

		return DozerConverter.parseObject(entity, MelhorDataCompraVO.class);
	}

	public void delete(MelhorDataCompraVO melhorDataCompraVO) {
		MelhorDataCompra entity = DozerConverter.parseObject(melhorDataCompraVO, MelhorDataCompra.class);
		melhorDataCompraRepository.delete(entity);
	}
}
