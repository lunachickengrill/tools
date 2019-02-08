<<<<<<< HEAD
package eu.vrtime.vrm.repositories;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;

@Repository
public interface SessionManagerRepository extends JpaRepository<SessionManager, Long>{
	
	public Optional<SessionManager> findBySmId(String smId);
	
	public Set<SessionManager> findBySoftswitch(Softswitch softswitch);
	
	public Optional<SessionManager> findByOid(Long oid);

}
=======
package eu.vrtime.vrm.repositories;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;

@Repository
public interface SessionManagerRepository extends JpaRepository<SessionManager, Long>{
	
	public Optional<SessionManager> findBySmId(String smId);
	
	public Set<SessionManager> findBySoftswitch(Softswitch softswitch);

}
>>>>>>> 8dc4866f8ccba5a3c2d16602cbcc6c1b79c5597a
