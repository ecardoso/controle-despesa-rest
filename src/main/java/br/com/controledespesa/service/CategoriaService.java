package br.com.controledespesa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controledespesa.converter.DozerConverter;
import br.com.controledespesa.data.model.Categoria;
import br.com.controledespesa.data.vo.CategoriaVO;
import br.com.controledespesa.repository.CategoriaDao;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDao categoriaDao;

	public List<CategoriaVO> findAll() {
		return DozerConverter.parseListObjects(categoriaDao.findAll(), CategoriaVO.class);
	}

	public CategoriaVO getById(Long id) {
		return DozerConverter.parseObject(categoriaDao.getById(id), CategoriaVO.class);
	}

	public CategoriaVO save(CategoriaVO categoriaVO) {
		Categoria entity = DozerConverter.parseObject(categoriaVO, Categoria.class);
		categoriaDao.save(entity);

		return DozerConverter.parseObject(entity, CategoriaVO.class);
	}

	public CategoriaVO update(CategoriaVO categoriaVO) {
		Categoria entity = DozerConverter.parseObject(categoriaVO, Categoria.class);
		categoriaDao.update(entity);

		return DozerConverter.parseObject(entity, CategoriaVO.class);
	}

	public void delete(CategoriaVO categoriaVO) {
		Categoria entity = DozerConverter.parseObject(categoriaVO, Categoria.class);
		categoriaDao.delete(entity);
	}
}
