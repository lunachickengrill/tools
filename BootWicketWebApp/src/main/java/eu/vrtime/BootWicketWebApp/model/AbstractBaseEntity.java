package eu.vrtime.BootWicketWebApp.model;

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
public class AbstractBaseEntity {
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

	public String toStringOid() {
		return oid.toString();
	}

}
