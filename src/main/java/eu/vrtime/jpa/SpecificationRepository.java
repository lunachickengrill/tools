package eu.vrtime.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface SpecificationRepository<T,ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
	
	public Optional<T> findByOid(Long oid);

	@Override
	public void delete(T entity);

	@Override
	public <S extends T> S saveAndFlush(S entity);

	@Override
	public List<T> findAll(Specification<T> spec);

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable);
	
	

}
