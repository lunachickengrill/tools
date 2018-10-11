package eu.vrtime.vrm.domain.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractBaseEntity implements Identity<AbstractBaseEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6764265482164843304L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OID", updatable = false, nullable = false)
	protected Long oid;

	@Column(name = "create_date", nullable = false, updatable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createDate;

	@Column(name = "last_modified")
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModified;

	public Long getOid() {
		return oid;
	}

	@Override
	public boolean sameIdentityAs(AbstractBaseEntity other) {
		return other != null && oid.equals(other.oid);
	}

	@Override
	public int hashCode() {
		return oid.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		final AbstractBaseEntity other = (AbstractBaseEntity) obj;

		return sameIdentityAs(other);
	}

}
