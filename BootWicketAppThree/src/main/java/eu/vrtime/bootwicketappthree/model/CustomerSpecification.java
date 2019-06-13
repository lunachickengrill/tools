package eu.vrtime.bootwicketappthree.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification implements Specification<Customer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5647166752242572939L;

	private String customerId;
	private String firstName;
	private String LastName;

	public CustomerSpecification() {
		super();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	@Override
	public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		Predicate predicate = criteriaBuilder.conjunction();

		if (customerId != null) {
			predicate.getExpressions().add(criteriaBuilder.equal(root.get("customerId"), customerId));
		}

		if (firstName != null) {
			predicate.getExpressions().add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
					"%" + firstName.toLowerCase() + "%"));
		}

		if (LastName != null) {
			predicate.getExpressions().add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
					"%" + LastName.toLowerCase() + "%"));
		}

		return predicate;
	}

}
