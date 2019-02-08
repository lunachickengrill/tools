package eu.vrtime.vrm.domain.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import eu.vrtime.vrm.domain.shared.AbstractBaseEntity;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;

@Entity
@Table(name = "T_RESOURCELOG")
public class ResourceLog extends AbstractBaseEntity {

	private static final long serialVersionUID = -4735534488791568579L;

	@Embedded
	@Column(name = "resource_id", unique = false, nullable = false)
	private ResourceIdentifier len;

	@Column(name = "directory_number", unique = false, nullable = false)
	private String dn;

	public ResourceLog(final ResourceIdentifier len, final String dn) {
		Validate.notNull(len, "len is null");
		Validate.notNull(dn, "dn is null");

		this.len = len;
		this.dn = dn;
	}

	public ResourceIdentifier getLen() {
		return len;
	}

	public String getDn() {
		return dn;
	}

	@Override
	public String toString() {
		return "ResourceLog [len=" + len + ", dn=" + dn + "]";
	}

	ResourceLog() {
	}

}