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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent")
	private Softswitch parent;

	@Column(name = "identifier", nullable = false, updatable = true, unique = true)
	private String identifier;

	@Column(name = "session_manager", nullable = false, updatable = true)
	private long sessionManager;

	@Enumerated(EnumType.STRING)
	private ResourceStatus status;

	public Resource(final Softswitch parent, String identifier, long sessionManager, ResourceStatus status) {
		this.parent = parent;
		this.identifier = identifier;
		this.sessionManager = sessionManager;
		this.status = status;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Softswitch getParent() {
		return parent;
	}

	public void setParent(Softswitch parent) {
		this.parent = parent;
	}

	public long getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(long sessionManager) {
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
		return "Resource [oid=" + toStringOid() + ", parent=" + parent.toStringOid() + ", identifier=" + identifier
				+ ", sessionManager=" + sessionManager + ", status=" + status + ", oid=" + oid + ", createDate="
				+ createDate + ", lastModified=" + lastModified + "]";
	}

	Resource() {

	}

}
