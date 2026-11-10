package org.ais.hr.transactions.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_TRANS_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_TRANS_DETAIL")
@NamedQuery(name="TblTransDetail.findAll", query="SELECT t FROM TblTransDetail t")
public class TblTransDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TRANS_DETAIL_TRANSDETAILID_GENERATOR", sequenceName="TBL_TRANS_DETAIL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TRANS_DETAIL_TRANSDETAILID_GENERATOR")
	@Column(name="TRANS_DETAIL_ID")
	private long transDetailId;

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

	@JsonIgnore
	@Temporal(TemporalType.DATE)
	private Date createdate;
	@JsonIgnore
	private BigDecimal createuser;
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;
	@JsonIgnore
	private BigDecimal lastupdateuser;

	@Column(name="REMITTER_ADDRESS")
	private String remitterAddress;

	@Column(name="REMITTER_NAME")
	private String remitterName;

	@Column(name="REMITTER_NUMBER")
	private String remitterNumber;

	private String status;

	@Column(name="STATUS_DESCR")
	private String statusDescr;

	@Column(name="T24_TRANSACTION_DESCR")
	private String t24TransactionDescr;

	@Column(name="T24_TRANSACTION_REFERENCE")
	private String t24TransactionReference;

	@Column(name="TRANSACTION_NUMBER")
	private String transactionNumber;

	@Column(name="TRANSACTION_REFERENCE")
	private String transactionReference;

	@JsonIgnore
	private BigDecimal updateindex;

	@Column(name="TRANS_TYPE")
	private String transType;

	@JsonIgnore
	@Column(name="TRANS_BT")
	private String transBt;



	@Column(name="REMITTER_COUNTRY")
	private String remitterCountry;


	@JsonIgnore
	@Column(name="TRANS_AMD")
	private String transAmd;

	//bi-directional many-to-one association to TblTransHead
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="TRANS_HEAD_ID")
	private TblTransHead tblTransHead;



	public TblTransDetail() {
	}

	public long getTransDetailId() {
		return this.transDetailId;
	}

	public void setTransDetailId(long transDetailId) {
		this.transDetailId = transDetailId;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDescr() {
		return this.statusDescr;
	}

	public void setStatusDescr(String statusDescr) {
		this.statusDescr = statusDescr;
	}

	public String getT24TransactionDescr() {
		return this.t24TransactionDescr;
	}

	public void setT24TransactionDescr(String t24TransactionDescr) {
		this.t24TransactionDescr = t24TransactionDescr;
	}

	public String getT24TransactionReference() {
		return this.t24TransactionReference;
	}

	public void setT24TransactionReference(String t24TransactionReference) {
		this.t24TransactionReference = t24TransactionReference;
	}

	public String getTransactionNumber() {
		return this.transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
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

	public TblTransHead getTblTransHead() {
		return this.tblTransHead;
	}

	public void setTblTransHead(TblTransHead tblTransHead) {
		this.tblTransHead = tblTransHead;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransBt() {
		return transBt;
	}

	public void setTransBt(String transBt) {
		this.transBt = transBt;
	}

	public String getTransAmd() {
		return transAmd;
	}

	public void setTransAmd(String transAmd) {
		this.transAmd = transAmd;
	}

	public String getRemitterCountry() {
		return remitterCountry;
	}

	public void setRemitterCountry(String remitterCountry) {
		this.remitterCountry = remitterCountry;
	}
}