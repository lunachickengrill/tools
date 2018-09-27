package eu.vrtime.vrm.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.Resource;
import eu.vrtime.vrm.domain.SessionManager;
import eu.vrtime.vrm.domain.Softswitch;
import eu.vrtime.vrm.domain.shared.ResourceStatus;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

	public Optional<Resource> findTopByStatusOrderByOid(ResourceStatus status);

	public Optional<Resource> findByOid(Long oid);

	public void delete(Resource resource);

	public <S extends Resource> S save(S entity);

	public List<Resource> findByStatus(ResourceStatus status);

	public List<Resource> findBySessionManager(long sessionManager);

	public List<Resource> findBySessionManager(SessionManager sessionManager);

	public long countByStatus(ResourceStatus status);
	
	public void flush();

}
