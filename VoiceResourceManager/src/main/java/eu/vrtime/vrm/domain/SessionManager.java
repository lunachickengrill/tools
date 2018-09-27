package eu.vrtime.vrm.domain;

import java.util.HashSet;
import java.util.Set;

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
	private Integer smId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "softswitch", nullable=false, updatable = true, unique = false)
	private Softswitch softswitch;

	@OneToMany(mappedBy = "sessionManager")
	private Set<Resource> resources = new HashSet<>();

	public SessionManager(Integer smId) {
		this.smId = smId;
	}

	public Integer getSmId() {
		return smId;
	}

	public void setSmId(Integer smId) {
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
