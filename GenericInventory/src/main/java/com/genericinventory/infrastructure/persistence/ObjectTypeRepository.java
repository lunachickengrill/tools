package com.genericinventory.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.genericinventory.domain.objecttype.ObjectClass;
import com.genericinventory.domain.objecttype.ObjectType;

// TODO @Transactional entfernen und auf Service legen

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface ObjectTypeRepository extends JpaRepository<ObjectType, Long> {

	@Override
	public <S extends ObjectType> List<S> save(Iterable<S> entities);

	@Override
	public <S extends ObjectType> S save(S entity);

	@Override
	public void delete(Long id);

	@Override
	public boolean exists(Long id);

	public ObjectType findOne(Long id);

	@Override
	public List<ObjectType> findAll();

	@Override
	public void flush();

	public List<ObjectType> findByObjectClass(ObjectClass objectClass);

	public ObjectType findByNameEqualsIgnoreCase(String name);

	@Query("select o from ObjectType o where lower(o.name) = lower(?1) and o.objectClass = ?2")
	public ObjectType findByNameAndObjectClassEqualsIgnoreCase(String name, ObjectClass objectClass);

	@Modifying
	@Transactional
	@Query("delete from ObjectType o where o.id=?1")
	public void deleteCascade(Long id);

}
