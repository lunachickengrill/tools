package eu.vrtime.vrm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.model.ResourceLog;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;

@Repository
public interface ResourceLogRepository extends JpaRepository<ResourceLog, Long> {

	public long count();

	public <S extends ResourceLog> S saveAndFlush(S entity);

	public Optional<ResourceLog> findByOid(Long oid);

//	public Optional<List<ResourceLog>> findByServiceId(String serviceId);
//
//	public Optional<List<ResourceLog>> findByCustomerId(String customerId);

	public Optional<List<ResourceLog>> findByLen(ResourceIdentifier len);

	public Optional<List<ResourceLog>> findByDn(String dn);

}
