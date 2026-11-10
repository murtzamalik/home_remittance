package org.ais.hr.fileupload.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_SYSTEM database table.
 * 
 */
@Entity
@Table(name="TBL_SYSTEM")
@NamedQuery(name="TblSystem.findAll", query="SELECT t FROM TblSystem t")
public class TblSystem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_SYSTEM_SYSTEMID_GENERATOR", sequenceName="TBL_SYSTEM_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_SYSTEM_SYSTEMID_GENERATOR")
	@Column(name="SYSTEM_ID")
	private long systemId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private String status;

	@Column(name="SYSTEM_CODE")
	private String systemCode;

	@Column(name="SYSTEM_DESCR")
	private String systemDescr;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblApplication
	@OneToMany(mappedBy="tblSystem")
	private List<TblApplication> tblApplications;

	public TblSystem() {
	}

	public long getSystemId() {
		return this.systemId;
	}

	public void setSystemId(long systemId) {
		this.systemId = systemId;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSystemCode() {
		return this.systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getSystemDescr() {
		return this.systemDescr;
	}

	public void setSystemDescr(String systemDescr) {
		this.systemDescr = systemDescr;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblApplication> getTblApplications() {
		return this.tblApplications;
	}

	public void setTblApplications(List<TblApplication> tblApplications) {
		this.tblApplications = tblApplications;
	}

	public TblApplication addTblApplication(TblApplication tblApplication) {
		getTblApplications().add(tblApplication);
		tblApplication.setTblSystem(this);

		return tblApplication;
	}

	public TblApplication removeTblApplication(TblApplication tblApplication) {
		getTblApplications().remove(tblApplication);
		tblApplication.setTblSystem(null);

		return tblApplication;
	}

}