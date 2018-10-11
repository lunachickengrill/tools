package eu.vrtime.vrm.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import eu.vrtime.vrm.domain.shared.AbstractBaseEntity;

@Entity
public class SessionManager extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6648727384134852057L;

	@Column(name = "sm_id", nullable = false, updatable = true, unique = true)
	private String smId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_SOFTSWITCH", nullable = false, updatable = true, unique = false)
	private Softswitch softswitch;

	@OneToMany(mappedBy = "sessionManager",fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Resource> resources = new HashSet<>();

	public SessionManager(final String smId, final Softswitch softswitch) {
		this.smId = smId;
		this.softswitch = softswitch;
	}

	public String getSmId() {
		return smId;
	}

	public void setSmId(String smId) {
		this.smId = smId;
	}

	public Softswitch getSoftswitch() {
		return softswitch;
	}

	public void setSoftswitch(Softswitch softswitch) {
		this.softswitch = softswitch;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	
	public void addResource(Resource resource) {
		resource.setSessionManager(this);
		resources.add(resource);
	}

	public String toStringOid() {
		return this.oid.toString();
	}

	@Override
	public String toString() {
		return "SessionManager [oid=" + toStringOid() + ", smId=" + smId + ", createDate=" + createDate
				+ ", lastModified=" + lastModified + "]";
	}

	public SessionManager() {

	}

}
