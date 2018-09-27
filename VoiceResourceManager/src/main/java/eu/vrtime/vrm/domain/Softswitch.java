package eu.vrtime.vrm.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.engine.internal.Cascade;

import eu.vrtime.vrm.domain.shared.AbstractBaseEntity;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;

@Entity
public class Softswitch extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3940445073324274980L;

	@Column(name = "switch_id", nullable = false, updatable = false, unique = true)
	private String switchId;

	@Column(name = "name", nullable = false, updatable = true, unique = false)
	private String name;

	@Enumerated(EnumType.STRING)
	private SoftswitchStatus status;

	@Column(name = "description", nullable = true, updatable = true, unique = false)
	private String description;

	@OneToMany(mappedBy = "softswitch", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<SessionManager> sessionManagers = new HashSet<>();

	public Softswitch(String switchId, String name, SoftswitchStatus status) {
		this.switchId = switchId;
		this.name = name;
		this.status = status;
	}

	public String getSwitchId() {
		return switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SoftswitchStatus getStatus() {
		return status;
	}

	public void setStatus(SoftswitchStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public Date getLastModified() {
		return this.lastModified;
	}

	public Set<SessionManager> getSessionManagers() {
		return sessionManagers;
	}

	public void setSessionManagers(Set<SessionManager> sessionManagers) {
		this.sessionManagers = sessionManagers;
	}

	public void addSessionManager(SessionManager sessionManager) {
		sessionManager.setSoftswitch(this);
		this.sessionManagers.add(sessionManager);
	}

	public String toStringOid() {
		return oid.toString();
	}

	@Override
	public String toString() {
		return "Softswitch [oid=" + toStringOid() + ", switchId=" + switchId + ", name=" + name + ", status=" + status
				+ ", description=" + description + "]";
	}

	Softswitch() {

	}

}
