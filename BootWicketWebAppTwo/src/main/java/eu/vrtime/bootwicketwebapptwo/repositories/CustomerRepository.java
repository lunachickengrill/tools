package eu.vrtime.bootwicketwebapptwo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import eu.vrtime.bootwicketwebapptwo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

	public Optional<Customer> findByOid(Long oid);

	public void deleteByOid(Long oid);

	public boolean existsByOid(Long oid);

	public long count();

	public void delete(Customer entity);

	public <S extends Customer> S saveAndFlush(S entity);

	public <S extends Customer> List<S> saveAll(Iterable<S> entities);

	public List<Customer> findByCustomerId(Long customerId);

	public <S extends Customer> Optional<S> findOne(Example<S> example);

	public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable);

	public <S extends Customer> long count(Example<S> example);

	public <S extends Customer> boolean exists(Example<S> example);

	public Optional<Customer> findOne(Specification<Customer> spec);

	public List<Customer> findAll(Specification<Customer> spec);

	public Page<Customer> findAll(Specification<Customer> spec, Pageable pageable);

	public List<Customer> findAll(Specification<Customer> spec, Sort sort);

	public long count(Specification<Customer> spec);

}
