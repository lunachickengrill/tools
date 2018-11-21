package eu.vrtime.vrm.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import eu.vrtime.vrm.domain.shared.AbstractBaseEntity;

@Entity
@Table(name = "T_TRACELOG")
public class TraceLog extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4735534488791568579L;

	@Column(name = "service_id", unique = false, nullable = false)
	private String serviceId;

	@Column(name = "customer_id", unique = false, nullable = false)
	private String customerId;

	@Column(name = "resource_id", unique = false, nullable = false)
	private Long resourceId;

	public TraceLog(final String serviceId, final String customerId, final Long resourceId) {
		this.serviceId = serviceId;
		this.customerId = customerId;
		this.resourceId = resourceId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	@Override
	public String toString() {
		return "TraceLog [serviceId=" + serviceId + ", customerId=" + customerId + ", resourceId=" + resourceId
				+ ", toStringOid()=" + toStringOid() + "]";
	}

	TraceLog() {
	}

}
