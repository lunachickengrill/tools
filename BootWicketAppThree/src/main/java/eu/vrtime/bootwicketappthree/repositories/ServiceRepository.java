package eu.vrtime.bootwicketappthree.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import eu.vrtime.bootwicketappthree.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long>, JpaSpecificationExecutor<Service> {

	public Optional<Service> findByOid(Long oid);

	public Optional<Service> findByServiceId(String serviceId);

	public void delete(Service entity);

	public <S extends Service> S saveAndFlush(S entity);

	public Optional<Set<Service>> findByServiceNameContaining(String serviceName);

	public List<Service> findAll(Specification<Service> spec);

}
