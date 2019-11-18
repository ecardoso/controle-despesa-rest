package br.com.controledespesa.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

public class GenericDaoImpl<T, K> implements GenericDao<T, K> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public <S extends T> S save(S entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	@Transactional
	public <S extends T> void delete(S entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	@Override
	@Transactional
	public <S extends T> S update(S entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<T> findAll() {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(getTypeClass());
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getById(K pk) {
		return (T) entityManager.find(getTypeClass(), pk);
	}

	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	public <S extends T> S getByProperty(String propertyName, String propertyNameValues) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(getTypeClass());
		criteria.add(Restrictions.eq(propertyName, propertyNameValues));

		return (S) criteria.uniqueResult();
	}

	private Class<?> getTypeClass() {
		return (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
