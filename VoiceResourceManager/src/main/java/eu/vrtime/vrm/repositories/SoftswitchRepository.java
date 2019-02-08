
package eu.vrtime.vrm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.shared.SwitchId;

@Repository
public interface SoftswitchRepository extends JpaRepository<Softswitch, Long> {

	public Optional<Softswitch> findBySwitchId(SwitchId switchId);

	public Optional<Softswitch> findByOid(Long oid);

	public void delete(Softswitch softswitch);

	public <S extends Softswitch> S save(S entity);

	public List<Softswitch> findByNameIgnoreCaseContains(String name);

	public void flush();

}
