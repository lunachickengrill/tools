package eu.vrtime.vrm.domain.model;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import eu.vrtime.vrm.domain.shared.SoftswitchStatus;
import eu.vrtime.vrm.domain.shared.SwitchId;

@Entity
@Table(name = "T_SOFTSWITCH")
public class Softswitch extends AbstractBaseEntity {

	// @Column(name = "switch_id", nullable = false, updatable = true, unique =
	// true)
	// private String switchId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7823116495312122813L;

	@Column(name = "len_enabled", nullable = false, updatable = true, unique = false)
	private Boolean isLenEnabled;

	@Embedded
	@Column(name = "switch_id", nullable = false, updatable = true, unique = true)
	private SwitchId switchId;

	@Column(name = "nic", nullable = false, updatable = true, unique = false)
	private String nic;

	@Column(name = "name", nullable = false, updatable = true, unique = false)
	private String name;

	@Enumerated(EnumType.STRING)
	private SoftswitchStatus status;

	@Column(name = "description", nullable = true, updatable = true, unique = false)
	private String description;
	
	@Column(name="NIC", nullable=false, updatable=true, unique=false)
	private String nic;
	

	@OneToMany(mappedBy = "softswitch", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SessionManager> sessionManagers = new HashSet<>();

<<<<<<< HEAD
	public Softswitch(String switchId, String name, SoftswitchStatus status, String nic) {
=======
	public Softswitch(final SwitchId switchId, final String nic, final String name, final SoftswitchStatus status,
			final Boolean isLenEnabled) {
		Validate.notNull(switchId, "switchId is null");
		Validate.notNull(nic, "nic is null");
		Validate.notNull(name, "name is null");
		Validate.notNull(status, "status is null");
		Validate.notNull(isLenEnabled, "isLenEnabled is null");

>>>>>>> branch 'master' of https://github.com/lunachickengrill/JavaStuff.git
		this.switchId = switchId;
		this.nic = nic;
		this.name = name;
		this.status = status;
<<<<<<< HEAD
		this.nic =nic;
=======
		this.isLenEnabled = isLenEnabled;
>>>>>>> branch 'master' of https://github.com/lunachickengrill/JavaStuff.git
	}

	public Boolean getIsLenEnabled() {
		return isLenEnabled;
	}

	public void setIsLenEnabled(Boolean isLenEnabled) {
		this.isLenEnabled = isLenEnabled;
	}

	public SwitchId getSwitchId() {
		return switchId;
	}

	public void setSwitchId(SwitchId switchId) {
		this.switchId = switchId;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
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
		sessionManagers.add(sessionManager);
		sessionManager.setSoftswitch(this);
	}

	public void removeSessionManager(SessionManager sessionManager) {
		sessionManagers.remove(sessionManager);
		sessionManager.setSoftswitch(null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Softswitch))
			return false;
		return oid != null && oid.equals(((Softswitch) o).oid);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "Softswitch [isLenEnabled=" + isLenEnabled + ", switchId=" + switchId + ", nic=" + nic + ", name=" + name
				+ ", status=" + status + ", description=" + description + ", sessionManagers=" + sessionManagers + "]";
	}

	public Softswitch() {

	}

}