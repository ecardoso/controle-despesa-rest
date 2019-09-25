package br.com.controledespesa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.controledespesa.entity.Usuario;
import br.com.controledespesa.enums.TipoLoginEnum;

//public interface UsuarioDaoImpl extends CrudRepository<Usuario, Long> {
@Repository
public class UsuarioDao extends GenericDaoImpl<Usuario, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("deprecation")
	public Usuario getUsuarioByEmailAndSenha(String email, String senha, TipoLoginEnum tipoLoginEnum) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("senha", senha));
		criteria.add(Restrictions.eq("tipoLogin", tipoLoginEnum.getChave()));

		Usuario usuario = (Usuario) criteria.uniqueResult();
		return usuario;
	}

	@SuppressWarnings("deprecation")
	public Usuario getUsuarioByEmail(String email, TipoLoginEnum tipoLoginEnum) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("tipoLogin", tipoLoginEnum.getChave()));

		Usuario usuario = (Usuario) criteria.uniqueResult();
		return usuario;
	}

}
