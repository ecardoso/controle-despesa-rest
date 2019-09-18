package br.com.controledespesa.repository;

//@Repository
public class DespesaDaoImpl {//extends GenericDao<Despesa, Long> implements DespesaDao {

	/*@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Despesa> findByMes(Date dataInicial, Date dataFinal) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Despesa.class);
		criteria.add(Restrictions.between("dataCompra", dataInicial, dataFinal));
		criteria.addOrder(Order.asc("categoria"));
		criteria.addOrder(Order.asc("dataCompra"));
	
		List<Despesa> despesas = criteria.list();
		return despesas;
	}*/

}
