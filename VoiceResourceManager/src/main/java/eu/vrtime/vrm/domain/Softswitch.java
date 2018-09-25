package eu.vrtime.vrm.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import eu.vrtime.vrm.domain.shared.AbstractBaseEntity;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;

@Entity
public class Softswitch extends AbstractBaseEntity {

	@Column(name = "switch_id", nullable = false, updatable = false)
	private String switchId;

	@Column(name = "name", nullable = false, updatable = true)
	private String name;

	@Enumerated(EnumType.STRING)
	private SoftswitchStatus status;

	@Column(name = "description", nullable = true, updatable = true)
	private String description;

//	@OneToMany(mappedBy = "softswitch")
//	private List<Resource> resources = new ArrayList<>();

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

	Softswitch() {

	}

}
