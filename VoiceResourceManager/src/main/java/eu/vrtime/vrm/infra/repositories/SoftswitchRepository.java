package eu.vrtime.vrm.infra.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.Softswitch;

@Repository
public interface SoftswitchRepository extends JpaRepository<Softswitch, Long> {
	
	public Optional<Softswitch> findBySwitchId(String switchId);

	public Optional<Softswitch> findByOid(Long oid);

	public void delete(Softswitch softswitch);

	public <S extends Softswitch> S save(S entity);

	public List<Softswitch> findByNameIgnoreCaseContains(String name);

	public void flush();

	
}
