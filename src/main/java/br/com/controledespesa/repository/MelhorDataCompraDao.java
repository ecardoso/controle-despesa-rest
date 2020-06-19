package br.com.controledespesa.repository;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.controledespesa.data.model.MelhorDataCompra;
import br.com.controledespesa.data.vo.FormaPagamentoVO;
import br.com.controledespesa.data.vo.UsuarioVO;
import br.com.controledespesa.helper.DataHelper;

@Repository
public class MelhorDataCompraDao extends GenericDaoImpl<MelhorDataCompra, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("deprecation")
	public MelhorDataCompra getMelhorDataCompra(UsuarioVO usuarioVO, FormaPagamentoVO formaPagamentoVO, LocalDateTime data) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(MelhorDataCompra.class);
		criteria.add(Restrictions.eq("usuario.id", usuarioVO.getKey()));
		criteria.add(Restrictions.eq("formaPagamento.id", formaPagamentoVO.getKey()));
		criteria.add(Restrictions.between("mesReferencia", DataHelper.getPrimeiroDiaDoMes(data), DataHelper.getUltimoDiaDoMes(data)));

		return (MelhorDataCompra) criteria.uniqueResult();
	}

}
