package br.com.controledespesa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.controledespesa.entity.FormaPagamento;
import br.com.controledespesa.entity.MelhorDataCompra;
import br.com.controledespesa.entity.Usuario;

@Repository
public class MelhorDataCompraDaoImpl extends GenericDao<MelhorDataCompra, Long> implements MelhorDataCompraDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("deprecation")
	@Override
	public MelhorDataCompra getMelhorDataCompra(Usuario usuario, FormaPagamento formaPagamento) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(MelhorDataCompra.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		criteria.add(Restrictions.eq("formaPagamento", formaPagamento));

		MelhorDataCompra melhorDataCompra = (MelhorDataCompra) criteria.uniqueResult();
		return melhorDataCompra;
	}

}
