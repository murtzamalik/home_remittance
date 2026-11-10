package org.ais.hr.transactions.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_MT102_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_MT102_DETAIL")
@NamedQuery(name="TblMt102Detail.findAll", query="SELECT t FROM TblMt102Detail t")
public class TblMt102Detail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MT102_DETAIL_ID")
	private long mt102DetailId;

	private BigDecimal amount;

	@Column(name="BENEFICIARY_ACCOUNT_NO")
	private String beneficiaryAccountNo;

	@Column(name="BENEFICIARY_ACCOUNT_TITLE")
	private String beneficiaryAccountTitle;

	@Column(name="BENEFICIARY_BANK_NAME")
	private String beneficiaryBankName;

	@Column(name="BENEFICIARY_BRANCH_ADD1")
	private String beneficiaryBranchAdd1;

	@Column(name="BENEFICIARY_BRANCH_ADD2")
	private String beneficiaryBranchAdd2;

	@Column(name="BENEFICIARY_BRANCH_CODE")
	private String beneficiaryBranchCode;

	@Column(name="BENEFICIARY_CNIC")
	private String beneficiaryCnic;

	@Column(name="BENEFICIARY_NAME")
	private String beneficiaryName;

	@Column(name="BENEFICIARY_OTHER_CONTACT")
	private String beneficiaryOtherContact;

	@Column(name="BIC_CODE")
	private String bicCode;

	@Column(name="BENEFICIARY_BANK_ACCOUNT")
	private String beneficaryBankAccount;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="REMITTER_ADDRESS")
	private String remitterAddress;

	@Column(name="REMITTER_COUNTRY")
	private String remitterCountry;

	@Column(name="REMITTER_NAME")
	private String remitterName;

	@Column(name="REMITTER_NUMBER")
	private String remitterNumber;

	@Column(name="TRANSACTION_REFERENCE")
	private String transactionReference;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblMt102Head
	@ManyToOne
	@JoinColumn(name="MT102_HEAD_ID")
	private TblMt102Head tblMt102Head;

	public TblMt102Detail() {
	}

	public String getBicCode() {
		return bicCode;
	}

	public void setBicCode(String bicCode) {
		this.bicCode = bicCode;
	}

	public String getBeneficaryBankAccount() {
		return beneficaryBankAccount;
	}

	public void setBeneficaryBankAccount(String beneficaryBankAccount) {
		this.beneficaryBankAccount = beneficaryBankAccount;
	}

	public long getMt102DetailId() {
		return this.mt102DetailId;
	}

	public void setMt102DetailId(long mt102DetailId) {
		this.mt102DetailId = mt102DetailId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBeneficiaryAccountNo() {
		return this.beneficiaryAccountNo;
	}

	public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
		this.beneficiaryAccountNo = beneficiaryAccountNo;
	}

	public String getBeneficiaryAccountTitle() {
		return this.beneficiaryAccountTitle;
	}

	public void setBeneficiaryAccountTitle(String beneficiaryAccountTitle) {
		this.beneficiaryAccountTitle = beneficiaryAccountTitle;
	}

	public String getBeneficiaryBankName() {
		return this.beneficiaryBankName;
	}

	public void setBeneficiaryBankName(String beneficiaryBankName) {
		this.beneficiaryBankName = beneficiaryBankName;
	}

	public String getBeneficiaryBranchAdd1() {
		return this.beneficiaryBranchAdd1;
	}

	public void setBeneficiaryBranchAdd1(String beneficiaryBranchAdd1) {
		this.beneficiaryBranchAdd1 = beneficiaryBranchAdd1;
	}

	public String getBeneficiaryBranchAdd2() {
		return this.beneficiaryBranchAdd2;
	}

	public void setBeneficiaryBranchAdd2(String beneficiaryBranchAdd2) {
		this.beneficiaryBranchAdd2 = beneficiaryBranchAdd2;
	}

	public String getBeneficiaryBranchCode() {
		return this.beneficiaryBranchCode;
	}

	public void setBeneficiaryBranchCode(String beneficiaryBranchCode) {
		this.beneficiaryBranchCode = beneficiaryBranchCode;
	}

	public String getBeneficiaryCnic() {
		return this.beneficiaryCnic;
	}

	public void setBeneficiaryCnic(String beneficiaryCnic) {
		this.beneficiaryCnic = beneficiaryCnic;
	}

	public String getBeneficiaryName() {
		return this.beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBeneficiaryOtherContact() {
		return this.beneficiaryOtherContact;
	}

	public void setBeneficiaryOtherContact(String beneficiaryOtherContact) {
		this.beneficiaryOtherContact = beneficiaryOtherContact;
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

	public String getRemitterAddress() {
		return this.remitterAddress;
	}

	public void setRemitterAddress(String remitterAddress) {
		this.remitterAddress = remitterAddress;
	}

	public String getRemitterCountry() {
		return this.remitterCountry;
	}

	public void setRemitterCountry(String remitterCountry) {
		this.remitterCountry = remitterCountry;
	}

	public String getRemitterName() {
		return this.remitterName;
	}

	public void setRemitterName(String remitterName) {
		this.remitterName = remitterName;
	}

	public String getRemitterNumber() {
		return this.remitterNumber;
	}

	public void setRemitterNumber(String remitterNumber) {
		this.remitterNumber = remitterNumber;
	}

	public String getTransactionReference() {
		return this.transactionReference;
	}

	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblMt102Head getTblMt102Head() {
		return this.tblMt102Head;
	}

	public void setTblMt102Head(TblMt102Head tblMt102Head) {
		this.tblMt102Head = tblMt102Head;
	}

}