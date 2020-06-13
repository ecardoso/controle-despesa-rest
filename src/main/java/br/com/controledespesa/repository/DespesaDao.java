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
public class DespesaDao extends GenericDaoImpl<Despesa, Long> {

	private static final String DATA_PAGAMENTO = "dataPagamento";

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Despesa> findByMes(Long idUsuario, Date dataInicial, Date dataFinal) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Despesa.class);
		criteria.add(Restrictions.eq("usuario.id", idUsuario));
		criteria.add(Restrictions.between(DATA_PAGAMENTO, dataInicial, dataFinal));
		criteria.addOrder(Order.asc("categoria"));
		criteria.addOrder(Order.asc(DATA_PAGAMENTO));

		return criteria.list();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Despesa> findByMesFormaPagamento(Long idUsuario, Long idFormaPagamento, Date dataInicial, Date dataFinal) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Despesa.class);
		criteria.add(Restrictions.eq("usuario.id", idUsuario));
		criteria.add(Restrictions.between(DATA_PAGAMENTO, dataInicial, dataFinal));
		criteria.add(Restrictions.eq("formaPagamento.id", idFormaPagamento));
		criteria.addOrder(Order.asc("categoria"));
		criteria.addOrder(Order.asc(DATA_PAGAMENTO));

		return criteria.list();
	}

}
