package eu.vrtime.bootwicketwebapptwo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.bootwicketwebapptwo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Optional<Customer> findByOid(Long oid);

	public void deleteByOid(Long oid);

	public boolean existsByOid(Long oid);

	public long count();

	public void delete(Customer entity);

	public <S extends Customer> Optional<S> findOne(Example<S> example);

	public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable);

	public <S extends Customer> long count(Example<S> example);

	public <S extends Customer> boolean exists(Example<S> example);

	public <S extends Customer> S saveAndFlush(S entity);

	public Page<Customer> findAll(Pageable pageable);
	
	public Optional<List<Customer>> findByCustomerId(Long customerId);
	
	

}
