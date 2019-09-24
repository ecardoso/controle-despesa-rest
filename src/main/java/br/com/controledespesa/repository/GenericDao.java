package br.com.controledespesa.repository;

import java.util.List;

public interface GenericDao<T, PK> {

	public <S extends T> S save(S entity);

	public <S extends T> void delete(S entity);

	public <S extends T> S update(S entity);

	public List<? extends T> findAll();

	public T getById(PK pk);

	public <S extends T> S getByProperty(String propertyName, String propertyNameValues);

}
