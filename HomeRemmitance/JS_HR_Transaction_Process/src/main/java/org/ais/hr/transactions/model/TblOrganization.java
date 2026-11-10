package org.ais.hr.transactions.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_ORGANIZATION database table.
 * 
 */
@Entity
@Table(name="TBL_ORGANIZATION")
@NamedQuery(name="TblOrganization.findAll", query="SELECT t FROM TblOrganization t")
public class TblOrganization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_ORGANIZATION_ORGANIZATIONID_GENERATOR", sequenceName="TBL_ORGANIZATION_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ORGANIZATION_ORGANIZATIONID_GENERATOR")
	@Column(name="ORGANIZATION_ID")
	private long organizationId;

	private String address1;

	private String address2;

	private String address3;

	@Column(name="BUSINESS_NATURE")
	private String businessNature;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="NTN_NUMBER")
	private String ntnNumber;

	@Column(name="ORGANIZATION_CODE")
	private String organizationCode;

	@Column(name="ORGANIZATION_DEFAULT")
	private String organizationDefault;

	@Column(name="ORGANIZATION_DESCR")
	private String organizationDescr;

	@Column(name="ORGANIZATION_LEVEL")
	private String organizationLevel;

	@Column(name="ORGANIZATION_SHORT")
	private String organizationShort;

	@Column(name="ORGANIZATION_TYPE")
	private String organizationType;

	@Column(name="REF_NUMBER1")
	private String refNumber1;

	@Column(name="REF_NUMBER2")
	private String refNumber2;

	@Column(name="REF_NUMBER3")
	private String refNumber3;

	@Column(name="REF_NUMBER4")
	private String refNumber4;

	@Column(name="REG_ADDRESS")
	private String regAddress;

	@Column(name="REG_NUMBER")
	private String regNumber;

	@Column(name="SBP_CODE")
	private String sbpCode;

	@Column(name="STATEMENT_COMM")
	private String statementComm;

	private String uan;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblFininstBank
	@OneToMany(mappedBy="tblOrganization")
	private List<TblFininstBank> tblFininstBanks;

	//bi-directional many-to-one association to TblGlAccount
	@OneToMany(mappedBy="tblOrganization")
	private List<TblGlAccount> tblGlAccounts;

	//bi-directional many-to-one association to TblLocation
	@OneToMany(mappedBy="tblOrganization")
	private List<TblLocation> tblLocations;

	//bi-directional many-to-one association to TblOrganization
	@ManyToOne
	@JoinColumn(name="PARENT_ORGANIZATION_ID")
	private TblOrganization tblOrganization;

	//bi-directional many-to-one association to TblOrganization
	@OneToMany(mappedBy="tblOrganization")
	private List<TblOrganization> tblOrganizations;

	//bi-directional many-to-one association to TblUser
	@OneToMany(mappedBy="tblOrganization")
	private List<TblUser> tblUsers;

	public TblOrganization() {
	}

	public long getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getBusinessNature() {
		return this.businessNature;
	}

	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
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

	public String getNtnNumber() {
		return this.ntnNumber;
	}

	public void setNtnNumber(String ntnNumber) {
		this.ntnNumber = ntnNumber;
	}

	public String getOrganizationCode() {
		return this.organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationDefault() {
		return this.organizationDefault;
	}

	public void setOrganizationDefault(String organizationDefault) {
		this.organizationDefault = organizationDefault;
	}

	public String getOrganizationDescr() {
		return this.organizationDescr;
	}

	public void setOrganizationDescr(String organizationDescr) {
		this.organizationDescr = organizationDescr;
	}

	public String getOrganizationLevel() {
		return this.organizationLevel;
	}

	public void setOrganizationLevel(String organizationLevel) {
		this.organizationLevel = organizationLevel;
	}

	public String getOrganizationShort() {
		return this.organizationShort;
	}

	public void setOrganizationShort(String organizationShort) {
		this.organizationShort = organizationShort;
	}

	public String getOrganizationType() {
		return this.organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	public String getRefNumber1() {
		return this.refNumber1;
	}

	public void setRefNumber1(String refNumber1) {
		this.refNumber1 = refNumber1;
	}

	public String getRefNumber2() {
		return this.refNumber2;
	}

	public void setRefNumber2(String refNumber2) {
		this.refNumber2 = refNumber2;
	}

	public String getRefNumber3() {
		return this.refNumber3;
	}

	public void setRefNumber3(String refNumber3) {
		this.refNumber3 = refNumber3;
	}

	public String getRefNumber4() {
		return this.refNumber4;
	}

	public void setRefNumber4(String refNumber4) {
		this.refNumber4 = refNumber4;
	}

	public String getRegAddress() {
		return this.regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getRegNumber() {
		return this.regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getSbpCode() {
		return this.sbpCode;
	}

	public void setSbpCode(String sbpCode) {
		this.sbpCode = sbpCode;
	}

	public String getStatementComm() {
		return this.statementComm;
	}

	public void setStatementComm(String statementComm) {
		this.statementComm = statementComm;
	}

	public String getUan() {
		return this.uan;
	}

	public void setUan(String uan) {
		this.uan = uan;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblFininstBank> getTblFininstBanks() {
		return this.tblFininstBanks;
	}

	public void setTblFininstBanks(List<TblFininstBank> tblFininstBanks) {
		this.tblFininstBanks = tblFininstBanks;
	}

	public TblFininstBank addTblFininstBank(TblFininstBank tblFininstBank) {
		getTblFininstBanks().add(tblFininstBank);
		tblFininstBank.setTblOrganization(this);

		return tblFininstBank;
	}

	public TblFininstBank removeTblFininstBank(TblFininstBank tblFininstBank) {
		getTblFininstBanks().remove(tblFininstBank);
		tblFininstBank.setTblOrganization(null);

		return tblFininstBank;
	}

	public List<TblGlAccount> getTblGlAccounts() {
		return this.tblGlAccounts;
	}

	public void setTblGlAccounts(List<TblGlAccount> tblGlAccounts) {
		this.tblGlAccounts = tblGlAccounts;
	}

	public TblGlAccount addTblGlAccount(TblGlAccount tblGlAccount) {
		getTblGlAccounts().add(tblGlAccount);
		tblGlAccount.setTblOrganization(this);

		return tblGlAccount;
	}

	public TblGlAccount removeTblGlAccount(TblGlAccount tblGlAccount) {
		getTblGlAccounts().remove(tblGlAccount);
		tblGlAccount.setTblOrganization(null);

		return tblGlAccount;
	}

	public List<TblLocation> getTblLocations() {
		return this.tblLocations;
	}

	public void setTblLocations(List<TblLocation> tblLocations) {
		this.tblLocations = tblLocations;
	}

	public TblLocation addTblLocation(TblLocation tblLocation) {
		getTblLocations().add(tblLocation);
		tblLocation.setTblOrganization(this);

		return tblLocation;
	}

	public TblLocation removeTblLocation(TblLocation tblLocation) {
		getTblLocations().remove(tblLocation);
		tblLocation.setTblOrganization(null);

		return tblLocation;
	}

	public TblOrganization getTblOrganization() {
		return this.tblOrganization;
	}

	public void setTblOrganization(TblOrganization tblOrganization) {
		this.tblOrganization = tblOrganization;
	}

	public List<TblOrganization> getTblOrganizations() {
		return this.tblOrganizations;
	}

	public void setTblOrganizations(List<TblOrganization> tblOrganizations) {
		this.tblOrganizations = tblOrganizations;
	}

	public TblOrganization addTblOrganization(TblOrganization tblOrganization) {
		getTblOrganizations().add(tblOrganization);
		tblOrganization.setTblOrganization(this);

		return tblOrganization;
	}

	public TblOrganization removeTblOrganization(TblOrganization tblOrganization) {
		getTblOrganizations().remove(tblOrganization);
		tblOrganization.setTblOrganization(null);

		return tblOrganization;
	}

	public List<TblUser> getTblUsers() {
		return this.tblUsers;
	}

	public void setTblUsers(List<TblUser> tblUsers) {
		this.tblUsers = tblUsers;
	}

	public TblUser addTblUser(TblUser tblUser) {
		getTblUsers().add(tblUser);
		tblUser.setTblOrganization(this);

		return tblUser;
	}

	public TblUser removeTblUser(TblUser tblUser) {
		getTblUsers().remove(tblUser);
		tblUser.setTblOrganization(null);

		return tblUser;
	}

}