package org.ais.hr.fi.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_APPLICATION database table.
 * 
 */
@Entity
@Table(name="TBL_APPLICATION")
@NamedQuery(name="TblApplication.findAll", query="SELECT t FROM TblApplication t")
public class TblApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_APPLICATION_APPLICATIONID_GENERATOR", sequenceName="TBL_APPLICATION_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_APPLICATION_APPLICATIONID_GENERATOR")
	@Column(name="APPLICATION_ID")
	private long applicationId;

	@Column(name="APPLICATION_CODE")
	private String applicationCode;

	@Column(name="APPLICATION_NAME")
	private String applicationName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private String status;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblSystem
	@ManyToOne
	@JoinColumn(name="SYSTEM_ID")
	private TblSystem tblSystem;

	//bi-directional many-to-one association to TblMenu
	@OneToMany(mappedBy="tblApplication")
	private List<TblMenu> tblMenus;

	public TblApplication() {
	}

	public long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationCode() {
		return this.applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getApplicationName() {
		return this.applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
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

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblSystem getTblSystem() {
		return this.tblSystem;
	}

	public void setTblSystem(TblSystem tblSystem) {
		this.tblSystem = tblSystem;
	}

	public List<TblMenu> getTblMenus() {
		return this.tblMenus;
	}

	public void setTblMenus(List<TblMenu> tblMenus) {
		this.tblMenus = tblMenus;
	}

	public TblMenu addTblMenus(TblMenu tblMenus) {
		getTblMenus().add(tblMenus);
		tblMenus.setTblApplication(this);

		return tblMenus;
	}

	public TblMenu removeTblMenus(TblMenu tblMenus) {
		getTblMenus().remove(tblMenus);
		tblMenus.setTblApplication(null);

		return tblMenus;
	}

}