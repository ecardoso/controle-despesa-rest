package br.com.controledespesa.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class GenericDao<T, PK> {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public <S extends T> S save(S entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Transactional
	public <S extends T> void delete(S entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	@Transactional
	public <S extends T> S update(S entity) {
		entityManager.merge(entity);
		return entity;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<? extends T> findAll() {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(getTypeClass());
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public T getById(PK pk) {
		return (T) entityManager.find(getTypeClass(), pk);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public <S extends T> S getByProperty(String propertyName, String propertyNameValues) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(getTypeClass());
		criteria.add(Restrictions.eq(propertyName, propertyNameValues));

		return (S) criteria.uniqueResult();
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz;
	}

}
