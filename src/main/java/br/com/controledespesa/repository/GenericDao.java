package br.com.controledespesa.repository;

import java.util.List;

public interface GenericDao<T, K> {

	public <S extends T> S save(S entity);

	public <S extends T> void delete(S entity);

	public <S extends T> S update(S entity);

	public List<T> findAll();

	public T getById(K pk);

	public <S extends T> S getByProperty(String propertyName, String propertyNameValues);

}
