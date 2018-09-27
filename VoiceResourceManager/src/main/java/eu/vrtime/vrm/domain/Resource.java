package eu.vrtime.vrm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import eu.vrtime.vrm.domain.shared.AbstractBaseEntity;
import eu.vrtime.vrm.domain.shared.ResourceStatus;

@Entity
public class Resource extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6150543763294953271L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sessionmanager", nullable = false, updatable = true, unique = false)
	private SessionManager sessionManager;

	@Column(name = "identifier", nullable = false, updatable = true, unique = true)
	private String identifier;

	@Enumerated(EnumType.STRING)
	private ResourceStatus status;

	public Resource(SessionManager sessionManager, String identifier, ResourceStatus status) {
		this.sessionManager = sessionManager;
		this.identifier = identifier;
		this.status = status;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public ResourceStatus getStatus() {
		return status;
	}

	public void setStatus(ResourceStatus status) {
		this.status = status;
	}

	public String toStringOid() {
		return oid.toString();
	}

	@Override
	public String toString() {
		return "Resource [oid=" + toStringOid() + ", identifier=" + identifier + ", sessionManager="
				+ sessionManager.toStringOid() + ", status=" + status + ", oid=" + oid + ", createDate=" + createDate
				+ ", lastModified=" + lastModified + "]";
	}

	Resource() {

	}

}
