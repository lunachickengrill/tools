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

import eu.vrtime.bootwicketappthree.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

	public Optional<Customer> findByOid(Long oid);

	public Optional<Customer> findByCustomerId(String customerId);

	public void delete(Customer entity);

	public <S extends Customer> S saveAndFlush(S entity);

	public Optional<Set<Customer>> findByFirstNameContaining(String firstName);

	public List<Customer> findByCustomerIdContaining(String customerId);

	public List<Customer> findAll(Specification<Customer> specification);

}
