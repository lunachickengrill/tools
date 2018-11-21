package eu.vrtime.vrm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.shared.ResourceCountingResult;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.domain.shared.ResourceStatus;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

	public Optional<Resource> findTopByStatusAndSessionManagerOrderByOid(ResourceStatus status,
			SessionManager sessionManager);

	public Optional<Resource> findByOid(Long oid);

	public Optional<Resource> findByIdentifier(ResourceIdentifier identifier);

	public void delete(Resource resource);

	public <S extends Resource> S save(S entity);

	public List<Resource> findByStatus(ResourceStatus status);

	public List<Resource> findBySessionManager(SessionManager sessionManager);

	public Long countByStatus(ResourceStatus status);

	public void flush();

	@Query(value = "select new eu.vrtime.vrm.domain.shared.ResourceCountingResult(r.sessionManager as sessionManager, count(r) as cnt) from Resource r where r.status ='FREE' group by r.sessionManager order by count(*) desc")
	public List<ResourceCountingResult> queryResouces();

}
