package eu.vrtime.vrm.infra.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.vrm.domain.Resource;
import eu.vrtime.vrm.domain.VoiceService;

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
