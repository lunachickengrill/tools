package com.addressmanagement.infrastructure.shared;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * The base repository for this domain provides the basic operations.
 * 
 * @author tschwaiger
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface DomainBaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

	public <S extends T> List<S> findAll();

	public T findOne(ID id);

	public T save(T entity);

	public <S extends T> List<S> save(Iterable<S> entities);

	public void delete(T entity);

}
