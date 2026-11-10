package org.ais.hr.fileupload.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_REMIT_MODE database table.
 * 
 */
@Entity
@Table(name="TBL_REMIT_MODE")
@NamedQuery(name="TblRemitMode.findAll", query="SELECT t FROM TblRemitMode t")
public class TblRemitMode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_REMIT_MODE_REMITMODEID_GENERATOR", sequenceName="TBL_REMIT_MODE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_REMIT_MODE_REMITMODEID_GENERATOR")
	@Column(name="REMIT_MODE_ID")
	private long remitModeId;

	@Column(name="AMOUNT_CAPPING")
	private BigDecimal amountCapping;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="REMIT_CODE")
	private String remitCode;

	@Column(name="REMIT_DEFAULT")
	private String remitDefault;

	@Column(name="REMIT_DESCR")
	private String remitDescr;

	private BigDecimal updateindex;

	public TblRemitMode() {
	}

	public long getRemitModeId() {
		return this.remitModeId;
	}

	public void setRemitModeId(long remitModeId) {
		this.remitModeId = remitModeId;
	}

	public BigDecimal getAmountCapping() {
		return this.amountCapping;
	}

	public void setAmountCapping(BigDecimal amountCapping) {
		this.amountCapping = amountCapping;
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

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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

	public String getRemitCode() {
		return this.remitCode;
	}

	public void setRemitCode(String remitCode) {
		this.remitCode = remitCode;
	}

	public String getRemitDefault() {
		return this.remitDefault;
	}

	public void setRemitDefault(String remitDefault) {
		this.remitDefault = remitDefault;
	}

	public String getRemitDescr() {
		return this.remitDescr;
	}

	public void setRemitDescr(String remitDescr) {
		this.remitDescr = remitDescr;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

}