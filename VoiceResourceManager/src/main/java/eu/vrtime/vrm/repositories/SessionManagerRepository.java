package eu.vrtime.vrm.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.SessionManager;
import eu.vrtime.vrm.domain.Softswitch;

@Repository
@Transactional
public interface SessionManagerRepository extends JpaRepository<SessionManager, Long>{
	
	public Optional<SessionManager> findBySmId(Integer smId);
	
	public Optional<SessionManager> findBySoftswitch(Softswitch softswitch);

}
