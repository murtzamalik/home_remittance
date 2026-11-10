package org.ais.hr.transactions.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_USERLEVEL database table.
 * 
 */
@Entity
@Table(name="LKP_USERLEVEL")
@NamedQuery(name="LkpUserlevel.findAll", query="SELECT l FROM LkpUserlevel l")
public class LkpUserlevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_USERLEVEL_USERLEVELID_GENERATOR", sequenceName="LKP_USERLEVEL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_USERLEVEL_USERLEVELID_GENERATOR")
	@Column(name="USERLEVEL_ID")
	private long userlevelId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="\"MESSAGE\"")
	private String message;

	private BigDecimal updateindex;

	@Column(name="USERLEVEL_CODE")
	private String userlevelCode;

	@Column(name="USERLEVEL_DESCR")
	private String userlevelDescr;

	public LkpUserlevel() {
	}

	public long getUserlevelId() {
		return this.userlevelId;
	}

	public void setUserlevelId(long userlevelId) {
		this.userlevelId = userlevelId;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public BigDecimal getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(BigDecimal createuser) {
		this.createuser = createuser;
	}

	public Date getLastupdatedate() {
		return this.lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public BigDecimal getLastupdateuser() {
		return this.lastupdateuser;
	}

	public void setLastupdateuser(BigDecimal lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getUserlevelCode() {
		return this.userlevelCode;
	}

	public void setUserlevelCode(String userlevelCode) {
		this.userlevelCode = userlevelCode;
	}

	public String getUserlevelDescr() {
		return this.userlevelDescr;
	}

	public void setUserlevelDescr(String userlevelDescr) {
		this.userlevelDescr = userlevelDescr;
	}

}