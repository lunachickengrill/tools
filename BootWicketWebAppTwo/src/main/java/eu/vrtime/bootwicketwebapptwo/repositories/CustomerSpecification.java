package eu.vrtime.bootwicketwebapptwo.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import eu.vrtime.bootwicketwebapptwo.model.Customer;

public class CustomerSpecification implements Specification<Customer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8683956488746333808L;
	private Customer filter;

	public CustomerSpecification(final Customer filter) {
		super();
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		Predicate predicate = cb.conjunction();

		if (filter.getCustomerId() != null) {
			predicate.getExpressions().add(cb.equal(root.get("customerId"), filter.getCustomerId()));
		}

		if (filter.getFirstName() != null) {
			predicate.getExpressions()
					.add(cb.like(cb.lower(root.get("firstName")), "%" + filter.getFirstName().toLowerCase() + "%"));
		}

		if (filter.getLastName() != null) {
			predicate.getExpressions()
					.add(cb.like(cb.lower(root.get("lastName")), "%" + filter.getLastName().toLowerCase() + "%"));
		}

		if (filter.getEmailAddress() != null) {
			predicate.getExpressions()
					.add(cb.like(cb.lower(root.get("emailAddress")), "%" + filter.getEmailAddress().toLowerCase() + "%"));
		}

		if (filter.getCustomerId() == null & filter.getFirstName() == null && filter.getLastName() == null && filter.getEmailAddress() == null) {
			predicate.getExpressions().add(cb.equal(root.get("oid"), -1));

		}
		return predicate;
	}

}
