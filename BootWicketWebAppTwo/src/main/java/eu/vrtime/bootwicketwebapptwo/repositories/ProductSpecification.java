package eu.vrtime.bootwicketwebapptwo.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import eu.vrtime.bootwicketwebapptwo.model.Product;

/**
 * An implementation of {@link #Specification} for searching Product objects
 * @author babis
 *
 */


public class ProductSpecification implements Specification<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4093005502010676123L;
	private Product filter;

	public ProductSpecification(final Product filter) {
		super();
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		Predicate predicate = cb.conjunction();

		if (filter.getProductId() != null) {
			predicate.getExpressions().add(cb.equal(root.get("productId"), filter.getProductId()));
		}

		if (filter.getProductStatus() != null) {
			predicate.getExpressions().add(cb.equal(root.get("productStatus"), filter.getProductStatus()));
		}

		if (filter.getDescription() != null) {
			predicate.getExpressions()
					.add(cb.like(cb.lower(root.get("description")), "%" + filter.getDescription().toLowerCase() + "%"));
		}

		return predicate;
	}

}
