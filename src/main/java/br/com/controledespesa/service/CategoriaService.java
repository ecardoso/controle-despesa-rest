package br.com.controledespesa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controledespesa.converter.DozerConverter;
import br.com.controledespesa.data.model.Categoria;
import br.com.controledespesa.data.vo.CategoriaVO;
import br.com.controledespesa.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<CategoriaVO> findAll() {
		return DozerConverter.parseListObjects(categoriaRepository.findAll(), CategoriaVO.class);
	}

	public CategoriaVO getById(Long id) {
		return DozerConverter.parseObject(categoriaRepository.findById(id), CategoriaVO.class);
	}

	public CategoriaVO save(CategoriaVO categoriaVO) {
		Categoria entity = DozerConverter.parseObject(categoriaVO, Categoria.class);
		categoriaRepository.save(entity);

		return DozerConverter.parseObject(entity, CategoriaVO.class);
	}

	public void delete(CategoriaVO categoriaVO) {
		Categoria entity = DozerConverter.parseObject(categoriaVO, Categoria.class);
		categoriaRepository.delete(entity);
	}
}
