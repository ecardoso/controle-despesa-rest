package br.com.controledespesa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.controledespesa.TipoLoginEnum;
import br.com.controledespesa.entity.Usuario;

//public interface UsuarioDaoImpl extends CrudRepository<Usuario, Long> {
@Repository
public class UsuarioDaoImpl extends GenericDao<Usuario, Long> implements UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("deprecation")
	@Override
	public Usuario getUsuarioByEmailAndSenha(String email, String senha, TipoLoginEnum tipoLoginEnum) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("senha", senha));
		criteria.add(Restrictions.eq("tipoLogin", tipoLoginEnum.getChave()));

		Usuario usuario = (Usuario) criteria.uniqueResult();
		return usuario;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Usuario getUsuarioByEmail(String email, TipoLoginEnum tipoLoginEnum) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("tipoLogin", tipoLoginEnum.getChave()));

		Usuario usuario = (Usuario) criteria.uniqueResult();
		return usuario;
	}

}
