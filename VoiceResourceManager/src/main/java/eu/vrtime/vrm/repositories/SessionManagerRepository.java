package eu.vrtime.vrm.repositories;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.SessionManager;
import eu.vrtime.vrm.domain.Softswitch;

@Repository
public interface SessionManagerRepository extends JpaRepository<SessionManager, Long>{
	
	public Optional<SessionManager> findBySmId(String smId);
	
	public Set<SessionManager> findBySoftswitch(Softswitch softswitch);

}