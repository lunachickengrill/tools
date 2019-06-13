package eu.vrtime.bootwicketappthree.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import eu.vrtime.bootwicketappthree.model.Device;
import eu.vrtime.bootwicketappthree.model.DeviceSpecification;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>, JpaSpecificationExecutor<Device> {

	public Optional<Device> findByOid(Long oid);

	public long count();

	public void delete(Device entity);

	public <S extends Device> S saveAndFlush(S entity);

	public Optional<Device> findBySerial(String serial);

	public List<Device> findAll(Specification<Device> spec);
	
	@Query("SELECT DISTINCT(d.type) from Device d")
	public List<String> listDeviceTypes();

}
