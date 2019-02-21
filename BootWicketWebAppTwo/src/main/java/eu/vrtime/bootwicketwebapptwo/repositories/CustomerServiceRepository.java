package eu.vrtime.bootwicketwebapptwo.repositories;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.bootwicketwebapptwo.model.CustomerService;

@Repository
public interface CustomerServiceRepository extends JpaRepository<CustomerService, Long> {
	
	public Optional<CustomerService> findByServiceId(String serviceId);

	public Optional<CustomerService> findByOid(Long oid);

	public void deleteByOid(Long oid);

	public boolean existsByOid(Long oid);

	public long count();

	public void delete(CustomerService entity);

	public <S extends CustomerService> Optional<S> findOne(Example<S> example);

	public <S extends CustomerService> Page<S> findAll(Example<S> example, Pageable pageable);

	public <S extends CustomerService> long count(Example<S> example);

	public <S extends CustomerService> boolean exists(Example<S> example);

	public <S extends CustomerService> S saveAndFlush(S entity);

	public Page<CustomerService> findAll(Pageable pageable);

}
