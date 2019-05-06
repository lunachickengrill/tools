package eu.vrtime.bootwicketwebapptwo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import eu.vrtime.bootwicketwebapptwo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

	public Optional<Product> findOne(Specification<Product> spec);

	public List<Product> findAll(Specification<Product> spec);

	public long count(Specification<Product> spec);

	public long count();

	public void delete(Product entity);

	public <S extends Product> S saveAndFlush(S entity);

}
