package br.com.controledespesa.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.controledespesa.entity.Despesa;

@Repository
public class DespesaDaoImpl extends GenericDao<Despesa, Long> implements DespesaDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Despesa> findByMes(String idUsuario, Date dataInicial, Date dataFinal) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Despesa.class);
		criteria.add(Restrictions.eq("usuario.id", Long.parseLong(idUsuario)));
		criteria.add(Restrictions.between("dataCompra", dataInicial, dataFinal));
		criteria.addOrder(Order.asc("categoria"));
		criteria.addOrder(Order.asc("dataCompra"));

		List<Despesa> despesas = criteria.list();
		return despesas;
	}

}
