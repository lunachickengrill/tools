package com.genericinventory.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.genericinventory.domain.dataobject.DataObject;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface DataObjectRepository extends BaseRepository<DataObject, Long> {

	@Override
	public void delete(Long id);

	@Override
	public DataObject save(DataObject type);

	@Override
	public DataObject findOne(Long id);

	@Override
	public List<DataObject> findAll();

	public DataObject findByNameEqualsIgnoreCase(String name);

	@Query(value = "SELECT OBJ.* FROM object OBJ WHERE OBJ.OBJECT_TYPE_ID =:objectTypeId AND (OBJ.object_id = (SELECT object_id FROM object_param_primary where value =:val) OR OBJ.object_id = (SELECT object_id from object_param where value =:val))", nativeQuery = true)
	public List<DataObject> findObjectByValue(@Param("objectTypeId") Long objectTypeId, @Param("val") String value);

	@Query(value = "SELECT OBJ.* FROM object OBJ WHERE obj.object_type_id =:objectTypeId AND OBJ.object_id = (SELECT object_id FROM object_param_primary WHERE attribute_id =:attributeId and value =:val) OR OBJ.object_id = (SELECT object_id FROM object_param WHERE attribute_id =:attributeId and value =:val)", nativeQuery = true)
	public List<DataObject> findObjectByAttributeAndValue(@Param("objectTypeId") Long objectTypeId,
			@Param("attributeId") Long attributeId, @Param("val") String value);

	@Modifying
	@Transactional
	@Query("delete from DataObject o where o.id=?1")
	public void deleteCascade(Long id);

}
