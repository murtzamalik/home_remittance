package org.ais.hr.user_management.model;

import java.io.Serializable;
import javax.persistence.*;
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


	//bi-directional many-to-one association to TblLocation
//	@OneToMany(mappedBy="tblOrganization")
//	private List<TblLocation> tblLocations;

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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getBusinessNature() {
		return businessNature;
	}

	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public BigDecimal getCreateuser() {
		return createuser;
	}

	public void setCreateuser(BigDecimal createuser) {
		this.createuser = createuser;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getLastupdatedate() {
		return lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public BigDecimal getLastupdateuser() {
		return lastupdateuser;
	}

	public void setLastupdateuser(BigDecimal lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public String getNtnNumber() {
		return ntnNumber;
	}

	public void setNtnNumber(String ntnNumber) {
		this.ntnNumber = ntnNumber;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationDefault() {
		return organizationDefault;
	}

	public void setOrganizationDefault(String organizationDefault) {
		this.organizationDefault = organizationDefault;
	}

	public String getOrganizationDescr() {
		return organizationDescr;
	}

	public void setOrganizationDescr(String organizationDescr) {
		this.organizationDescr = organizationDescr;
	}

	public String getOrganizationLevel() {
		return organizationLevel;
	}

	public void setOrganizationLevel(String organizationLevel) {
		this.organizationLevel = organizationLevel;
	}

	public String getOrganizationShort() {
		return organizationShort;
	}

	public void setOrganizationShort(String organizationShort) {
		this.organizationShort = organizationShort;
	}

	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	public String getRefNumber1() {
		return refNumber1;
	}

	public void setRefNumber1(String refNumber1) {
		this.refNumber1 = refNumber1;
	}

	public String getRefNumber2() {
		return refNumber2;
	}

	public void setRefNumber2(String refNumber2) {
		this.refNumber2 = refNumber2;
	}

	public String getRefNumber3() {
		return refNumber3;
	}

	public void setRefNumber3(String refNumber3) {
		this.refNumber3 = refNumber3;
	}

	public String getRefNumber4() {
		return refNumber4;
	}

	public void setRefNumber4(String refNumber4) {
		this.refNumber4 = refNumber4;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getSbpCode() {
		return sbpCode;
	}

	public void setSbpCode(String sbpCode) {
		this.sbpCode = sbpCode;
	}

	public String getStatementComm() {
		return statementComm;
	}

	public void setStatementComm(String statementComm) {
		this.statementComm = statementComm;
	}

	public String getUan() {
		return uan;
	}

	public void setUan(String uan) {
		this.uan = uan;
	}

	public BigDecimal getUpdateindex() {
		return updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}



	public TblOrganization getTblOrganization() {
		return tblOrganization;
	}

	public void setTblOrganization(TblOrganization tblOrganization) {
		this.tblOrganization = tblOrganization;
	}

	public List<TblOrganization> getTblOrganizations() {
		return tblOrganizations;
	}

	public void setTblOrganizations(List<TblOrganization> tblOrganizations) {
		this.tblOrganizations = tblOrganizations;
	}

	public List<TblUser> getTblUsers() {
		return tblUsers;
	}

	public void setTblUsers(List<TblUser> tblUsers) {
		this.tblUsers = tblUsers;
	}
}