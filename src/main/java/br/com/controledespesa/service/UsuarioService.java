package br.com.controledespesa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controledespesa.converter.DozerConverter;
import br.com.controledespesa.data.model.Usuario;
import br.com.controledespesa.data.vo.UsuarioVO;
import br.com.controledespesa.enums.TipoLoginEnum;
import br.com.controledespesa.repository.UsuarioDao;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	public List<UsuarioVO> findAll() {
		return DozerConverter.parseListObjects(usuarioDao.findAll(), UsuarioVO.class);
	}

	public UsuarioVO getById(Long id) {
		return DozerConverter.parseObject(usuarioDao.getById(id), UsuarioVO.class);
	}

	public UsuarioVO getUsuarioByEmailAndSenha(String email, String senha) {
		return DozerConverter.parseObject(usuarioDao.getUsuarioByEmailAndSenha(email, senha, TipoLoginEnum.SISTEMA), UsuarioVO.class);
	}

	public UsuarioVO getUsuarioByEmailAndSenhaAndTipoLogin(String email, String senha, TipoLoginEnum tipoLoginEnum) {
		return DozerConverter.parseObject(usuarioDao.getUsuarioByEmailAndSenha(email, senha, tipoLoginEnum), UsuarioVO.class);
	}

	public UsuarioVO getUsuarioByEmail(String email) {
		return DozerConverter.parseObject(usuarioDao.getUsuarioByEmail(email, TipoLoginEnum.SISTEMA), UsuarioVO.class);
	}

	public UsuarioVO getUsuarioByEmailAndTipoLoginEnum(String email, TipoLoginEnum tipoLoginEnum) {
		return DozerConverter.parseObject(usuarioDao.getUsuarioByEmail(email, tipoLoginEnum), UsuarioVO.class);
	}

	public UsuarioVO save(UsuarioVO usuarioVO) {
		Usuario entity = DozerConverter.parseObject(usuarioVO, Usuario.class);
		entity.setTipoLogin(TipoLoginEnum.SISTEMA.getChave());
		usuarioDao.save(entity);

		return DozerConverter.parseObject(entity, UsuarioVO.class);
	}
}
