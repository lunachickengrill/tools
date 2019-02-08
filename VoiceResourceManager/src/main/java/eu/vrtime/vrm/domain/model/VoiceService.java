
package eu.vrtime.vrm.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import eu.vrtime.vrm.domain.shared.AbstractBaseEntity;

@Entity
@Table(name = "T_VOICESERVICE")
public class VoiceService extends AbstractBaseEntity {

	private static final long serialVersionUID = 6895257404688974719L;

	@Column(name = "directory_number", nullable = false, updatable = true, unique = true)
	private String directoryNumber;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resourceId")
	public Resource resource;

	public VoiceService(final String directoryNumber) {
		Validate.notNull(directoryNumber, "directoryNumber is null");

		this.directoryNumber = directoryNumber;
	}

	public String getDirectoryNumber() {
		return directoryNumber;
	}

	public void setDirectoryNumber(String directoryNumber) {
		this.directoryNumber = directoryNumber;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof VoiceService))
			return false;
		return oid != null && oid.equals(((VoiceService) o).oid);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "VoiceService [directoryNumber=" + directoryNumber + ", resource=" + resource + "]";
	}

	VoiceService() {

	}

}