package eu.vrtime.bootwicketappthree.model;

import javax.persistence.Column;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class DeviceSpecification implements Specification<Device> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5914596054802158631L;

	private String mac;
	private String serial;
	private String type;

	public DeviceSpecification() {
		super();
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		Predicate predicate = criteriaBuilder.conjunction();

		if (mac != null) {
			predicate.getExpressions()
					.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("mac")), "%" + mac.toLowerCase() + "%"));
		}

		if (serial != null) {

			predicate.getExpressions()
					.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("serial")), "%" + serial.toLowerCase() + "%"));
		}

		if (type != null) {

			predicate.getExpressions()
					.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("type")), type.toLowerCase()));

		}

		return predicate;
	}

}
