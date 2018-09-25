package com.genericinventory.infrastructure.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.genericinventory.domain.objecttype.ObjectTypeAttribute;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface AttributeRepository extends BaseRepository<ObjectTypeAttribute, Long> {

	@Override
	public void delete(Long id);

	@Override
	public ObjectTypeAttribute save(ObjectTypeAttribute type);

	@Override
	public ObjectTypeAttribute findOne(Long id);

	@Override
	public List<ObjectTypeAttribute> findAll();

	public <S extends ObjectTypeAttribute> List<S> save(Iterable<S> entities);
	
	public ObjectTypeAttribute findByNameEqualsIgnoreCase(String name);

}
