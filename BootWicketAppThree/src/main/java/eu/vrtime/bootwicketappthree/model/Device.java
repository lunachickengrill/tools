package eu.vrtime.bootwicketappthree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Device extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4504339706323582203L;

	@Column(nullable = false)
	private String mac;

	@Column(nullable = false)
	private String serial;

	@Column(nullable = false)
	private String type;

	@OneToOne(mappedBy = "device")
	private Service service;

	public Device() {

	}

	public Device(final String mac, final String serial, final String type) {
		this.mac = mac;
		this.serial = serial;
		this.type = type;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Device [mac=" + mac + ", serial=" + serial + ", type=" + type + "]";
	}

}
