package org.ais.hr.fileupload.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_LOCATION database table.
 * 
 */
@Entity
@Table(name="TBL_LOCATION")
@NamedQuery(name="TblLocation.findAll", query="SELECT t FROM TblLocation t")
public class TblLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_LOCATION_LOCATIONID_GENERATOR", sequenceName="TBL_LOCATION_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_LOCATION_LOCATIONID_GENERATOR")
	@Column(name="LOCATION_ID")
	private long locationId;

	@Column(name="ADMIN_NATURE")
	private String adminNature;

	@Column(name="ADV_AVALABLE")
	private String advAvalable;

	private String allow;

	@Column(name="ATM_AVALABLE")
	private String atmAvalable;

	@Column(name="ATM_ONLINE")
	private String atmOnline;

	@Column(name="BANK_OPR")
	private String bankOpr;

	@Column(name="BR_ONLINE")
	private String brOnline;

	@Column(name="BRRG_CODE")
	private String brrgCode;

	private String cable;

	@Column(name="CASH_REPL")
	private String cashRepl;

	@Column(name="CENTRAL_OFFICE")
	private String centralOffice;

	@Temporal(TemporalType.DATE)
	@Column(name="CLOSE_DATE")
	private Date closeDate;

	@Column(name="CPU_BRANCH")
	private String cpuBranch;

	@Column(name="CRB_CODE")
	private String crbCode;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CTOF_TIME")
	private String ctofTime;

	@Column(name="ECIB_LOCK")
	private String ecibLock;

	@Temporal(TemporalType.DATE)
	@Column(name="ECIB_LOGDATE")
	private Date ecibLogdate;

	@Temporal(TemporalType.DATE)
	@Column(name="EOD_COMPLDATE")
	private Date eodCompldate;

	@Temporal(TemporalType.DATE)
	@Column(name="EOD_COMPLLOGDATE")
	private Date eodCompllogdate;

	@Column(name="EOD_COMPLSTAT")
	private String eodComplstat;

	@Column(name="EOD_COMPLUSER_ID")
	private BigDecimal eodCompluserId;

	@Temporal(TemporalType.DATE)
	@Column(name="EOD_READYDATE")
	private Date eodReadydate;

	@Temporal(TemporalType.DATE)
	@Column(name="EOD_READYLOGDATE")
	private Date eodReadylogdate;

	@Column(name="EOD_READYSTAT")
	private String eodReadystat;

	@Column(name="EOD_READYUSER_ID")
	private BigDecimal eodReadyuserId;

	@Column(name="EVNG_BANK")
	private String evngBank;

	@Column(name="EXTD_TIME")
	private String extdTime;

	@Column(name="FCY_LICSN")
	private String fcyLicsn;

	@Temporal(TemporalType.DATE)
	@Column(name="HOL_READYDATE")
	private Date holReadydate;

	@Temporal(TemporalType.DATE)
	@Column(name="HOL_READYLOGDATE")
	private Date holReadylogdate;

	@Column(name="HOL_READYSTAT")
	private String holReadystat;

	@Column(name="HOL_READYUSER_ID")
	private BigDecimal holReadyuserId;

	private String identifieras;

	@Column(name="IP_ADDRESS")
	private String ipAddress;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="ISLM_BANK")
	private String islmBank;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="LOCAT_CODE")
	private String locatCode;

	@Column(name="LOCATION_CODE")
	private String locationCode;

	@Column(name="LOCATION_DEFAULT")
	private String locationDefault;

	@Column(name="LOCATION_DESCR")
	private String locationDescr;

	@Column(name="LOCATION_LEVEL")
	private String locationLevel;

	@Column(name="LOCATION_NATURE")
	private String locationNature;

	@Column(name="LOCATION_SHORT")
	private String locationShort;

	@Column(name="LOCATION_TYPE")
	private String locationType;

	@Column(name="MAIN_BRANCH")
	private String mainBranch;

	@Temporal(TemporalType.DATE)
	@Column(name="NEXT_WORKDATE")
	private Date nextWorkdate;

	@Column(name="NORM_TIME")
	private String normTime;

	@Temporal(TemporalType.DATE)
	@Column(name="OPEN_DATE")
	private Date openDate;

	@Column(name="PBN_BNBRCODE")
	private String pbnBnbrcode;

	@Column(name="SBP_CODE")
	private String sbpCode;

	@Column(name="SBP_DELNG")
	private String sbpDelng;

	@Temporal(TemporalType.DATE)
	@Column(name="SOD_READYDATE")
	private Date sodReadydate;

	@Temporal(TemporalType.DATE)
	@Column(name="SOD_READYLOGDATE")
	private Date sodReadylogdate;

	@Column(name="SOD_READYSTAT")
	private String sodReadystat;

	@Column(name="SOD_READYUSER_ID")
	private BigDecimal sodReadyuserId;

	@Column(name="SPRP_NOTE")
	private String sprpNote;

	private String swift;

	@Temporal(TemporalType.DATE)
	@Column(name="TRAN_DATE")
	private Date tranDate;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpCity
	@ManyToOne
	@JoinColumn(name="CITY_ID")
	private LkpCity lkpCity;

	//bi-directional many-to-one association to LkpCountry
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private LkpCountry lkpCountry;

	//bi-directional many-to-one association to TblFininstBank
	@ManyToOne
	@JoinColumn(name="FININST_BANK_ID")
	private TblFininstBank tblFininstBank;

	//bi-directional many-to-one association to TblGlAccount
	@ManyToOne
	@JoinColumn(name="GL_ACCOUNT_ID")
	private TblGlAccount tblGlAccount;

	//bi-directional many-to-one association to TblLocation
	@ManyToOne
	@JoinColumn(name="HEDOFF_LOCATION_ID")
	private TblLocation tblLocation1;

	//bi-directional many-to-one association to TblLocation
	@OneToMany(mappedBy="tblLocation1")
	private List<TblLocation> tblLocations1;

	//bi-directional many-to-one association to TblLocation
	@ManyToOne
	@JoinColumn(name="PARENT_LOCATION_ID")
	private TblLocation tblLocation2;

	//bi-directional many-to-one association to TblLocation
	@OneToMany(mappedBy="tblLocation2")
	private List<TblLocation> tblLocations2;

	//bi-directional many-to-one association to TblLocation
	@ManyToOne
	@JoinColumn(name="TREASURY_LOCATION_ID")
	private TblLocation tblLocation3;

	//bi-directional many-to-one association to TblLocation
	@OneToMany(mappedBy="tblLocation3")
	private List<TblLocation> tblLocations3;

	//bi-directional many-to-one association to TblLocation
	@ManyToOne
	@JoinColumn(name="STLMNT_LOCATION_ID")
	private TblLocation tblLocation4;

	//bi-directional many-to-one association to TblLocation
	@OneToMany(mappedBy="tblLocation4")
	private List<TblLocation> tblLocations4;

	//bi-directional many-to-one association to TblLocation
	@ManyToOne
	@JoinColumn(name="CPU_LOCATION_ID")
	private TblLocation tblLocation5;

	//bi-directional many-to-one association to TblLocation
	@OneToMany(mappedBy="tblLocation5")
	private List<TblLocation> tblLocations5;

	//bi-directional many-to-one association to TblOrganization
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID")
	private TblOrganization tblOrganization;

	//bi-directional many-to-one association to TblUser
	@OneToMany(mappedBy="tblLocation")
	private List<TblUser> tblUsers;

	public TblLocation() {
	}

	public long getLocationId() {
		return this.locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getAdminNature() {
		return this.adminNature;
	}

	public void setAdminNature(String adminNature) {
		this.adminNature = adminNature;
	}

	public String getAdvAvalable() {
		return this.advAvalable;
	}

	public void setAdvAvalable(String advAvalable) {
		this.advAvalable = advAvalable;
	}

	public String getAllow() {
		return this.allow;
	}

	public void setAllow(String allow) {
		this.allow = allow;
	}

	public String getAtmAvalable() {
		return this.atmAvalable;
	}

	public void setAtmAvalable(String atmAvalable) {
		this.atmAvalable = atmAvalable;
	}

	public String getAtmOnline() {
		return this.atmOnline;
	}

	public void setAtmOnline(String atmOnline) {
		this.atmOnline = atmOnline;
	}

	public String getBankOpr() {
		return this.bankOpr;
	}

	public void setBankOpr(String bankOpr) {
		this.bankOpr = bankOpr;
	}

	public String getBrOnline() {
		return this.brOnline;
	}

	public void setBrOnline(String brOnline) {
		this.brOnline = brOnline;
	}

	public String getBrrgCode() {
		return this.brrgCode;
	}

	public void setBrrgCode(String brrgCode) {
		this.brrgCode = brrgCode;
	}

	public String getCable() {
		return this.cable;
	}

	public void setCable(String cable) {
		this.cable = cable;
	}

	public String getCashRepl() {
		return this.cashRepl;
	}

	public void setCashRepl(String cashRepl) {
		this.cashRepl = cashRepl;
	}

	public String getCentralOffice() {
		return this.centralOffice;
	}

	public void setCentralOffice(String centralOffice) {
		this.centralOffice = centralOffice;
	}

	public Date getCloseDate() {
		return this.closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getCpuBranch() {
		return this.cpuBranch;
	}

	public void setCpuBranch(String cpuBranch) {
		this.cpuBranch = cpuBranch;
	}

	public String getCrbCode() {
		return this.crbCode;
	}

	public void setCrbCode(String crbCode) {
		this.crbCode = crbCode;
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

	public String getCtofTime() {
		return this.ctofTime;
	}

	public void setCtofTime(String ctofTime) {
		this.ctofTime = ctofTime;
	}

	public String getEcibLock() {
		return this.ecibLock;
	}

	public void setEcibLock(String ecibLock) {
		this.ecibLock = ecibLock;
	}

	public Date getEcibLogdate() {
		return this.ecibLogdate;
	}

	public void setEcibLogdate(Date ecibLogdate) {
		this.ecibLogdate = ecibLogdate;
	}

	public Date getEodCompldate() {
		return this.eodCompldate;
	}

	public void setEodCompldate(Date eodCompldate) {
		this.eodCompldate = eodCompldate;
	}

	public Date getEodCompllogdate() {
		return this.eodCompllogdate;
	}

	public void setEodCompllogdate(Date eodCompllogdate) {
		this.eodCompllogdate = eodCompllogdate;
	}

	public String getEodComplstat() {
		return this.eodComplstat;
	}

	public void setEodComplstat(String eodComplstat) {
		this.eodComplstat = eodComplstat;
	}

	public BigDecimal getEodCompluserId() {
		return this.eodCompluserId;
	}

	public void setEodCompluserId(BigDecimal eodCompluserId) {
		this.eodCompluserId = eodCompluserId;
	}

	public Date getEodReadydate() {
		return this.eodReadydate;
	}

	public void setEodReadydate(Date eodReadydate) {
		this.eodReadydate = eodReadydate;
	}

	public Date getEodReadylogdate() {
		return this.eodReadylogdate;
	}

	public void setEodReadylogdate(Date eodReadylogdate) {
		this.eodReadylogdate = eodReadylogdate;
	}

	public String getEodReadystat() {
		return this.eodReadystat;
	}

	public void setEodReadystat(String eodReadystat) {
		this.eodReadystat = eodReadystat;
	}

	public BigDecimal getEodReadyuserId() {
		return this.eodReadyuserId;
	}

	public void setEodReadyuserId(BigDecimal eodReadyuserId) {
		this.eodReadyuserId = eodReadyuserId;
	}

	public String getEvngBank() {
		return this.evngBank;
	}

	public void setEvngBank(String evngBank) {
		this.evngBank = evngBank;
	}

	public String getExtdTime() {
		return this.extdTime;
	}

	public void setExtdTime(String extdTime) {
		this.extdTime = extdTime;
	}

	public String getFcyLicsn() {
		return this.fcyLicsn;
	}

	public void setFcyLicsn(String fcyLicsn) {
		this.fcyLicsn = fcyLicsn;
	}

	public Date getHolReadydate() {
		return this.holReadydate;
	}

	public void setHolReadydate(Date holReadydate) {
		this.holReadydate = holReadydate;
	}

	public Date getHolReadylogdate() {
		return this.holReadylogdate;
	}

	public void setHolReadylogdate(Date holReadylogdate) {
		this.holReadylogdate = holReadylogdate;
	}

	public String getHolReadystat() {
		return this.holReadystat;
	}

	public void setHolReadystat(String holReadystat) {
		this.holReadystat = holReadystat;
	}

	public BigDecimal getHolReadyuserId() {
		return this.holReadyuserId;
	}

	public void setHolReadyuserId(BigDecimal holReadyuserId) {
		this.holReadyuserId = holReadyuserId;
	}

	public String getIdentifieras() {
		return this.identifieras;
	}

	public void setIdentifieras(String identifieras) {
		this.identifieras = identifieras;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIslmBank() {
		return this.islmBank;
	}

	public void setIslmBank(String islmBank) {
		this.islmBank = islmBank;
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

	public String getLocatCode() {
		return this.locatCode;
	}

	public void setLocatCode(String locatCode) {
		this.locatCode = locatCode;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationDefault() {
		return this.locationDefault;
	}

	public void setLocationDefault(String locationDefault) {
		this.locationDefault = locationDefault;
	}

	public String getLocationDescr() {
		return this.locationDescr;
	}

	public void setLocationDescr(String locationDescr) {
		this.locationDescr = locationDescr;
	}

	public String getLocationLevel() {
		return this.locationLevel;
	}

	public void setLocationLevel(String locationLevel) {
		this.locationLevel = locationLevel;
	}

	public String getLocationNature() {
		return this.locationNature;
	}

	public void setLocationNature(String locationNature) {
		this.locationNature = locationNature;
	}

	public String getLocationShort() {
		return this.locationShort;
	}

	public void setLocationShort(String locationShort) {
		this.locationShort = locationShort;
	}

	public String getLocationType() {
		return this.locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getMainBranch() {
		return this.mainBranch;
	}

	public void setMainBranch(String mainBranch) {
		this.mainBranch = mainBranch;
	}

	public Date getNextWorkdate() {
		return this.nextWorkdate;
	}

	public void setNextWorkdate(Date nextWorkdate) {
		this.nextWorkdate = nextWorkdate;
	}

	public String getNormTime() {
		return this.normTime;
	}

	public void setNormTime(String normTime) {
		this.normTime = normTime;
	}

	public Date getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getPbnBnbrcode() {
		return this.pbnBnbrcode;
	}

	public void setPbnBnbrcode(String pbnBnbrcode) {
		this.pbnBnbrcode = pbnBnbrcode;
	}

	public String getSbpCode() {
		return this.sbpCode;
	}

	public void setSbpCode(String sbpCode) {
		this.sbpCode = sbpCode;
	}

	public String getSbpDelng() {
		return this.sbpDelng;
	}

	public void setSbpDelng(String sbpDelng) {
		this.sbpDelng = sbpDelng;
	}

	public Date getSodReadydate() {
		return this.sodReadydate;
	}

	public void setSodReadydate(Date sodReadydate) {
		this.sodReadydate = sodReadydate;
	}

	public Date getSodReadylogdate() {
		return this.sodReadylogdate;
	}

	public void setSodReadylogdate(Date sodReadylogdate) {
		this.sodReadylogdate = sodReadylogdate;
	}

	public String getSodReadystat() {
		return this.sodReadystat;
	}

	public void setSodReadystat(String sodReadystat) {
		this.sodReadystat = sodReadystat;
	}

	public BigDecimal getSodReadyuserId() {
		return this.sodReadyuserId;
	}

	public void setSodReadyuserId(BigDecimal sodReadyuserId) {
		this.sodReadyuserId = sodReadyuserId;
	}

	public String getSprpNote() {
		return this.sprpNote;
	}

	public void setSprpNote(String sprpNote) {
		this.sprpNote = sprpNote;
	}

	public String getSwift() {
		return this.swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public LkpCity getLkpCity() {
		return this.lkpCity;
	}

	public void setLkpCity(LkpCity lkpCity) {
		this.lkpCity = lkpCity;
	}

	public LkpCountry getLkpCountry() {
		return this.lkpCountry;
	}

	public void setLkpCountry(LkpCountry lkpCountry) {
		this.lkpCountry = lkpCountry;
	}

	public TblFininstBank getTblFininstBank() {
		return this.tblFininstBank;
	}

	public void setTblFininstBank(TblFininstBank tblFininstBank) {
		this.tblFininstBank = tblFininstBank;
	}

	public TblGlAccount getTblGlAccount() {
		return this.tblGlAccount;
	}

	public void setTblGlAccount(TblGlAccount tblGlAccount) {
		this.tblGlAccount = tblGlAccount;
	}

	public TblLocation getTblLocation1() {
		return this.tblLocation1;
	}

	public void setTblLocation1(TblLocation tblLocation1) {
		this.tblLocation1 = tblLocation1;
	}

	public List<TblLocation> getTblLocations1() {
		return this.tblLocations1;
	}

	public void setTblLocations1(List<TblLocation> tblLocations1) {
		this.tblLocations1 = tblLocations1;
	}

	public TblLocation addTblLocations1(TblLocation tblLocations1) {
		getTblLocations1().add(tblLocations1);
		tblLocations1.setTblLocation1(this);

		return tblLocations1;
	}

	public TblLocation removeTblLocations1(TblLocation tblLocations1) {
		getTblLocations1().remove(tblLocations1);
		tblLocations1.setTblLocation1(null);

		return tblLocations1;
	}

	public TblLocation getTblLocation2() {
		return this.tblLocation2;
	}

	public void setTblLocation2(TblLocation tblLocation2) {
		this.tblLocation2 = tblLocation2;
	}

	public List<TblLocation> getTblLocations2() {
		return this.tblLocations2;
	}

	public void setTblLocations2(List<TblLocation> tblLocations2) {
		this.tblLocations2 = tblLocations2;
	}

	public TblLocation addTblLocations2(TblLocation tblLocations2) {
		getTblLocations2().add(tblLocations2);
		tblLocations2.setTblLocation2(this);

		return tblLocations2;
	}

	public TblLocation removeTblLocations2(TblLocation tblLocations2) {
		getTblLocations2().remove(tblLocations2);
		tblLocations2.setTblLocation2(null);

		return tblLocations2;
	}

	public TblLocation getTblLocation3() {
		return this.tblLocation3;
	}

	public void setTblLocation3(TblLocation tblLocation3) {
		this.tblLocation3 = tblLocation3;
	}

	public List<TblLocation> getTblLocations3() {
		return this.tblLocations3;
	}

	public void setTblLocations3(List<TblLocation> tblLocations3) {
		this.tblLocations3 = tblLocations3;
	}

	public TblLocation addTblLocations3(TblLocation tblLocations3) {
		getTblLocations3().add(tblLocations3);
		tblLocations3.setTblLocation3(this);

		return tblLocations3;
	}

	public TblLocation removeTblLocations3(TblLocation tblLocations3) {
		getTblLocations3().remove(tblLocations3);
		tblLocations3.setTblLocation3(null);

		return tblLocations3;
	}

	public TblLocation getTblLocation4() {
		return this.tblLocation4;
	}

	public void setTblLocation4(TblLocation tblLocation4) {
		this.tblLocation4 = tblLocation4;
	}

	public List<TblLocation> getTblLocations4() {
		return this.tblLocations4;
	}

	public void setTblLocations4(List<TblLocation> tblLocations4) {
		this.tblLocations4 = tblLocations4;
	}

	public TblLocation addTblLocations4(TblLocation tblLocations4) {
		getTblLocations4().add(tblLocations4);
		tblLocations4.setTblLocation4(this);

		return tblLocations4;
	}

	public TblLocation removeTblLocations4(TblLocation tblLocations4) {
		getTblLocations4().remove(tblLocations4);
		tblLocations4.setTblLocation4(null);

		return tblLocations4;
	}

	public TblLocation getTblLocation5() {
		return this.tblLocation5;
	}

	public void setTblLocation5(TblLocation tblLocation5) {
		this.tblLocation5 = tblLocation5;
	}

	public List<TblLocation> getTblLocations5() {
		return this.tblLocations5;
	}

	public void setTblLocations5(List<TblLocation> tblLocations5) {
		this.tblLocations5 = tblLocations5;
	}

	public TblLocation addTblLocations5(TblLocation tblLocations5) {
		getTblLocations5().add(tblLocations5);
		tblLocations5.setTblLocation5(this);

		return tblLocations5;
	}

	public TblLocation removeTblLocations5(TblLocation tblLocations5) {
		getTblLocations5().remove(tblLocations5);
		tblLocations5.setTblLocation5(null);

		return tblLocations5;
	}

	public TblOrganization getTblOrganization() {
		return this.tblOrganization;
	}

	public void setTblOrganization(TblOrganization tblOrganization) {
		this.tblOrganization = tblOrganization;
	}

	public List<TblUser> getTblUsers() {
		return this.tblUsers;
	}

	public void setTblUsers(List<TblUser> tblUsers) {
		this.tblUsers = tblUsers;
	}

	public TblUser addTblUser(TblUser tblUser) {
		getTblUsers().add(tblUser);
		tblUser.setTblLocation(this);

		return tblUser;
	}

	public TblUser removeTblUser(TblUser tblUser) {
		getTblUsers().remove(tblUser);
		tblUser.setTblLocation(null);

		return tblUser;
	}

}