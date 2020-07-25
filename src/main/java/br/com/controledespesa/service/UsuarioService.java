package br.com.controledespesa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.controledespesa.converter.DozerConverter;
import br.com.controledespesa.data.model.Usuario;
import br.com.controledespesa.data.vo.UsuarioVO;
import br.com.controledespesa.enums.TipoLoginEnum;
import br.com.controledespesa.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<UsuarioVO> findAll() {
		return DozerConverter.parseListObjects(usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "nome")), UsuarioVO.class);
	}

	public UsuarioVO getById(Long id) {
		return DozerConverter.parseObject(usuarioRepository.findById(id), UsuarioVO.class);
	}

	public UsuarioVO getUsuarioByEmailAndSenha(String email, String senha) {
		return DozerConverter.parseObject(usuarioRepository.getUsuarioByEmailAndSenha(email, senha, TipoLoginEnum.SISTEMA.getChave()), UsuarioVO.class);
	}

	public UsuarioVO getUsuarioByEmailAndSenhaAndTipoLogin(String email, String senha, TipoLoginEnum tipoLoginEnum) {
		return DozerConverter.parseObject(usuarioRepository.getUsuarioByEmailAndSenha(email, senha, tipoLoginEnum.getChave()), UsuarioVO.class);
	}

	public UsuarioVO getUsuarioByEmail(String email) {
		return DozerConverter.parseObject(usuarioRepository.getUsuarioByEmail(email, TipoLoginEnum.SISTEMA.getChave()), UsuarioVO.class);
	}

	public UsuarioVO getUsuarioByEmailAndTipoLoginEnum(String email, TipoLoginEnum tipoLoginEnum) {
		return DozerConverter.parseObject(usuarioRepository.getUsuarioByEmail(email, tipoLoginEnum.getChave()), UsuarioVO.class);
	}

	public UsuarioVO save(UsuarioVO usuarioVO) {
		Usuario entity = DozerConverter.parseObject(usuarioVO, Usuario.class);
		entity.setTipoLogin(TipoLoginEnum.SISTEMA.getChave());
		usuarioRepository.save(entity);

		return DozerConverter.parseObject(entity, UsuarioVO.class);
	}
}
