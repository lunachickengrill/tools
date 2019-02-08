<<<<<<< HEAD
package eu.vrtime.vrm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.VoiceService;

@Repository
public interface VoiceServiceRepository extends JpaRepository<VoiceService, Long> {

	public Optional<VoiceService> findByOid(Long oid);

//	public Optional<List<VoiceService>> findByServiceId(String serviceId);

	public Optional<VoiceService> findByDirectoryNumber(String directoryNumber);

//	public Optional<List<VoiceService>> findByCustomerId(String customerId);
	
//	public Optional<VoiceService> findByCustomerIdAndLineNo(String customerId, int lineNo);

	public Optional<VoiceService> findByResource(Resource resource);

	public void delete(VoiceService service);

	public <S extends Resource> S save(S entity);

	public void flush();

}
=======
package eu.vrtime.vrm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.VoiceService;

@Repository
public interface VoiceServiceRepository extends JpaRepository<VoiceService, Long> {

	public Optional<VoiceService> findByOid(Long oid);

	public Optional<VoiceService> findByServiceId(String serviceId);

	public Optional<VoiceService> findByDirectoryNumber(String directoryNumber);

	public Optional<VoiceService> findByCustomerId(String customerId);

	public void delete(VoiceService service);

	public <S extends Resource> S save(S entity);

	public void flush();

}
>>>>>>> 8dc4866f8ccba5a3c2d16602cbcc6c1b79c5597a
