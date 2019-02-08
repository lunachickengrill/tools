package eu.vrtime.BootWicketWebApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.BootWicketWebApp.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Optional<Customer> findByOid(Long oid);

	public boolean existsById(Long id);

	public long count();

	public void deleteByOid(Long oid);

	public void delete(Customer entity);

	public <S extends Customer> S saveAndFlush(S entity);

}
