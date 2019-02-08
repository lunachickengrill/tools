package eu.vrtime.vrm.domain.model;

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
import javax.persistence.metamodel.IdentifiableType;

import org.apache.commons.lang3.Validate;
import org.hibernate.engine.internal.Cascade;

import eu.vrtime.vrm.domain.shared.AbstractBaseEntity;
import eu.vrtime.vrm.domain.shared.Identity;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;
import eu.vrtime.vrm.domain.shared.SwitchId;

@Entity
public class Softswitch extends AbstractBaseEntity {

	@Column(name = "switch_id", nullable = false, updatable = false, unique = true)
	private String switchId;

	@Column(name = "name", nullable = false, updatable = true, unique = false)
	private String name;

	@Enumerated(EnumType.STRING)
	private SoftswitchStatus status;

	@Column(name = "description", nullable = true, updatable = true, unique = false)
	private String description;

	@Column(name = "NIC", nullable = false, updatable = true, unique = false)
	private String nic;

	@OneToMany(mappedBy = "softswitch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SessionManager> sessionManagers = new HashSet<>();

	public Softswitch(final SwitchId switchId, final String nic, final String name, final SoftswitchStatus status,
			final boolean isLenEnabled) {
		Validate.notNull(switchId, "switchId is null");
		Validate.notNull(nic, "nic is null");
		Validate.notNull(name, "name is null");
		Validate.notNull(status, "status is null");
		Validate.notNull(isLenEnabled, "isLenEnabled is null");

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

	public String getNic() {
		return this.nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
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

	@Override
	public String toString() {
		return "Softswitch [oid=" + toStringOid() + ", switchId=" + switchId + ", name=" + name + ", status=" + status
				+ ", description=" + description + "]";
	}

	Softswitch() {

	}

}