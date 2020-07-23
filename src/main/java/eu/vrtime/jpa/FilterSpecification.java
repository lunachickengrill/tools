package eu.vrtime.jpa;

import org.springframework.data.jpa.domain.Specification;

/**
 * Interface for Specification classes. Must implement method to build predicate. Can be used by AbstractEntityDataProvider implementations.
 * @author tschwaiger
 *
 * @param <T>
 */
public interface FilterSpecification<T> {
	
	Specification<T> createFilterSpecification();

}
