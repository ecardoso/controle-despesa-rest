package br.com.controledespesa.repository;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.controledespesa.entity.FormaPagamento;
import br.com.controledespesa.entity.MelhorDataCompra;
import br.com.controledespesa.entity.Usuario;
import br.com.controledespesa.helper.DataHelper;

@Repository
public class MelhorDataCompraDao extends GenericDaoImpl<MelhorDataCompra, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("deprecation")
	public MelhorDataCompra getMelhorDataCompra(Usuario usuario, FormaPagamento formaPagamento, Date data) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(MelhorDataCompra.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		criteria.add(Restrictions.eq("formaPagamento", formaPagamento));
		criteria.add(Restrictions.between("mesReferencia", DataHelper.getPrimeiroDiaDoMes(data), DataHelper.getUltimoDiaDoMes(data)));

		return (MelhorDataCompra) criteria.uniqueResult();
	}

}
