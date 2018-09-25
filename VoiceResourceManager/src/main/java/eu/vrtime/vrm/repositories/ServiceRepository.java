package eu.vrtime.vrm.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.Resource;
import eu.vrtime.vrm.domain.Service;

@Repository
@Transactional
public interface ServiceRepository extends JpaRepository<Service, Long> {
	
	public Optional<Service> findByOid(Long oid);
	
	public Optional<Service> findByServiceId(String serviceId);
	
	public Optional<Service> findByDirectoryNumber(String directoryNumber);

	public void delete(Service service);

	public <S extends Resource> S save(S entity);
	
	public void flush();

}
