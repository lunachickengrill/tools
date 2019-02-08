<<<<<<< HEAD
package eu.vrtime.vrm.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

@Entity
@Table(name = "T_SESSIONMANAGER")
public class SessionManager extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6648727384134852057L;

	@Column(name = "sm_id", nullable = false, updatable = true, unique = true)
	private String smId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_SOFTSWITCH")
	private Softswitch softswitch;

	@OneToMany(mappedBy = "sessionManager", fetch=FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Resource> resources = new HashSet<>();

	public SessionManager(final String smId, final Softswitch softswitch) {
		Validate.notNull(smId, "smId is null");
		Validate.notNull(softswitch, "softswitch is null");
		
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
	
	public Date getCreateDate() {
		return createDate;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setResources(Set<Resource> resources) {
		for (Resource r : resources) {
			r.setSessionManager(this);
			this.resources.add(r);
		}
		
	}

	public void addResource(Resource resource) {
		resource.setSessionManager(this);
		resources.add(resource);
	}
	

	public void removeResource(Resource resource) {
		resources.remove(resource);
		resource.setSessionManager(null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof SessionManager))
			return false;
		return oid != null && oid.equals(((SessionManager) o).oid);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "SessionManager [oid=" + toStringOid() + ", smId=" + smId + ", createDate=" + createDate
				+ ", lastModified=" + lastModified + "]";
	}

	public SessionManager() {

	}

}
=======
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

	@Override
	public String toString() {
		return "SessionManager [oid=" + toStringOid() + ", smId=" + smId + ", createDate=" + createDate
				+ ", lastModified=" + lastModified + "]";
	}

	public SessionManager() {

	}

}
>>>>>>> 8dc4866f8ccba5a3c2d16602cbcc6c1b79c5597a
