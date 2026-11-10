package org.ais.hr.user_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_MODULE database table.
 * 
 */
@Entity
@Table(name="TBL_MODULE")
@NamedQuery(name="TblModule.findAll", query="SELECT t FROM TblModule t")
public class TblModule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_MODULE_MODULEID_GENERATOR", sequenceName="TBL_MODULE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_MODULE_MODULEID_GENERATOR")
	@Column(name="MODULE_ID")
	private long moduleId;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;

	@Column(name="CHECKER_ID")
	private BigDecimal checkerId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Column(name="MODULE_DESCR")
	private String moduleDescr;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblMenu
	@OneToMany(mappedBy="tblModule")
	@JsonIgnore
	private List<TblMenu> tblMenus;

	public TblModule() {
	}

	public long getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckerComments() {
		return this.checkerComments;
	}

	public void setCheckerComments(String checkerComments) {
		this.checkerComments = checkerComments;
	}

	public BigDecimal getCheckerId() {
		return this.checkerId;
	}

	public void setCheckerId(BigDecimal checkerId) {
		this.checkerId = checkerId;
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

	public String getMcStatus() {
		return this.mcStatus;
	}

	public void setMcStatus(String mcStatus) {
		this.mcStatus = mcStatus;
	}

	public String getModuleDescr() {
		return this.moduleDescr;
	}

	public void setModuleDescr(String moduleDescr) {
		this.moduleDescr = moduleDescr;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblMenu> getTblMenus() {
		return this.tblMenus;
	}

	public void setTblMenus(List<TblMenu> tblMenus) {
		this.tblMenus = tblMenus;
	}

	public TblMenu addTblMenus(TblMenu tblMenus) {
		getTblMenus().add(tblMenus);
		tblMenus.setTblModule(this);

		return tblMenus;
	}

	public TblMenu removeTblMenus(TblMenu tblMenus) {
		getTblMenus().remove(tblMenus);
		tblMenus.setTblModule(null);

		return tblMenus;
	}

}