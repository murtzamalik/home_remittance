package org.ais.hr.transactions.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_RESPONSE database table.
 * 
 */
@Entity
@Table(name="TBL_RESPONSE")
@NamedQuery(name="TblResponse.findAll", query="SELECT t FROM TblResponse t")
public class TblResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_RESPONSE_RESPONSEID_GENERATOR", sequenceName="TBL_RESPONSE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_RESPONSE_RESPONSEID_GENERATOR")
	@Column(name="RESPONSE_ID")
	private long responseId;

	@Column(name="ACCOUNTIDENTIFICATION1_102")
	private String accountidentification1102;

	@Column(name="ACCOUNTIDENTIFICATION2_103")
	private String accountidentification2103;

	@Column(name="ACQUIRUNGINSTITUTIONIDENTIFICATIONCODE_032")
	private String acquirunginstitutionidentificationcode032;

	@Column(name="ADDITIONALRESPONSEDATA_044")
	private String additionalresponsedata044;

	@Column(name="AMOUNTTRANSACTION_004")
	private BigDecimal amounttransaction004;

	private String bankcode;

	private String bankname;

	private String branchcode;

	private String branchname;

	private String cnic;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CURRENCYCODESETTLEMENT_050")
	private String currencycodesettlement050;

	@Column(name="CURRENCYCODETRANSACTION_049")
	private String currencycodetransaction049;

	@Column(name="DATELOCALTRANSACTION_013")
	private String datelocaltransaction013;

	private String displayname;

	private String employeeid;

	private String isomessage;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private String mail;

	@Column(name="MERCHANTTYPE_018")
	private String merchanttype018;

	@Column(name="MESSAGE")
	private String message;

	private String mti;

	private String name;

	@Column(name="PRIMARYACCOUNTNUMBER_002")
	private String primaryaccountnumber002;

	@Column(name="PROCESSINGCODE_003")
	private String processingcode003;

	private String relationshipno;

	@Column(name="RESERVEDPRIVATE_120")
	private String reservedprivate120;

	@Lob
	@Column(name="RESPONSE_JSON")
	private String responseJson;

	private String responsecode;

	@Column(name="RESPONSECODE_039")
	private String responsecode039;

	private String responsemessage;

	private String status;

	private String statuscode;

	@Column(name="SYSTEMSTRACEAUDITNUMBER_011")
	private String systemstraceauditnumber011;

	@Column(name="TIMELOCALTRANSACTION_012")
	private String timelocaltransaction012;

	private String title;

	private String toaccounttitle;

	@Column(name="TRANSACTIONDESCRIPTION_104")
	private String transactiondescription104;

	@Column(name="TRANSMISSIONDATETIME_007")
	private String transmissiondatetime007;

	private BigDecimal updateindex;

	private String userid;

	//bi-directional many-to-one association to TblRequest
	@ManyToOne
	@JoinColumn(name="REQUEST_ID")
	private TblRequest tblRequest;

	public TblResponse() {
	}

	public long getResponseId() {
		return this.responseId;
	}

	public void setResponseId(long responseId) {
		this.responseId = responseId;
	}

	public String getAccountidentification1102() {
		return this.accountidentification1102;
	}

	public void setAccountidentification1102(String accountidentification1102) {
		this.accountidentification1102 = accountidentification1102;
	}

	public String getAccountidentification2103() {
		return this.accountidentification2103;
	}

	public void setAccountidentification2103(String accountidentification2103) {
		this.accountidentification2103 = accountidentification2103;
	}

	public String getAcquirunginstitutionidentificationcode032() {
		return this.acquirunginstitutionidentificationcode032;
	}

	public void setAcquirunginstitutionidentificationcode032(String acquirunginstitutionidentificationcode032) {
		this.acquirunginstitutionidentificationcode032 = acquirunginstitutionidentificationcode032;
	}

	public String getAdditionalresponsedata044() {
		return this.additionalresponsedata044;
	}

	public void setAdditionalresponsedata044(String additionalresponsedata044) {
		this.additionalresponsedata044 = additionalresponsedata044;
	}

	public BigDecimal getAmounttransaction004() {
		return this.amounttransaction004;
	}

	public void setAmounttransaction004(BigDecimal amounttransaction004) {
		this.amounttransaction004 = amounttransaction004;
	}

	public String getBankcode() {
		return this.bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getBankname() {
		return this.bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBranchcode() {
		return this.branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

	public String getBranchname() {
		return this.branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getCnic() {
		return this.cnic;
	}

	public void setCnic(String cnic) {
		this.cnic = cnic;
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

	public String getCurrencycodesettlement050() {
		return this.currencycodesettlement050;
	}

	public void setCurrencycodesettlement050(String currencycodesettlement050) {
		this.currencycodesettlement050 = currencycodesettlement050;
	}

	public String getCurrencycodetransaction049() {
		return this.currencycodetransaction049;
	}

	public void setCurrencycodetransaction049(String currencycodetransaction049) {
		this.currencycodetransaction049 = currencycodetransaction049;
	}

	public String getDatelocaltransaction013() {
		return this.datelocaltransaction013;
	}

	public void setDatelocaltransaction013(String datelocaltransaction013) {
		this.datelocaltransaction013 = datelocaltransaction013;
	}

	public String getDisplayname() {
		return this.displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getIsomessage() {
		return this.isomessage;
	}

	public void setIsomessage(String isomessage) {
		this.isomessage = isomessage;
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

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMerchanttype018() {
		return this.merchanttype018;
	}

	public void setMerchanttype018(String merchanttype018) {
		this.merchanttype018 = merchanttype018;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMti() {
		return this.mti;
	}

	public void setMti(String mti) {
		this.mti = mti;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryaccountnumber002() {
		return this.primaryaccountnumber002;
	}

	public void setPrimaryaccountnumber002(String primaryaccountnumber002) {
		this.primaryaccountnumber002 = primaryaccountnumber002;
	}

	public String getProcessingcode003() {
		return this.processingcode003;
	}

	public void setProcessingcode003(String processingcode003) {
		this.processingcode003 = processingcode003;
	}

	public String getRelationshipno() {
		return this.relationshipno;
	}

	public void setRelationshipno(String relationshipno) {
		this.relationshipno = relationshipno;
	}

	public String getReservedprivate120() {
		return this.reservedprivate120;
	}

	public void setReservedprivate120(String reservedprivate120) {
		this.reservedprivate120 = reservedprivate120;
	}

	public String getResponseJson() {
		return this.responseJson;
	}

	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}

	public String getResponsecode() {
		return this.responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}

	public String getResponsecode039() {
		return this.responsecode039;
	}

	public void setResponsecode039(String responsecode039) {
		this.responsecode039 = responsecode039;
	}

	public String getResponsemessage() {
		return this.responsemessage;
	}

	public void setResponsemessage(String responsemessage) {
		this.responsemessage = responsemessage;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatuscode() {
		return this.statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getSystemstraceauditnumber011() {
		return this.systemstraceauditnumber011;
	}

	public void setSystemstraceauditnumber011(String systemstraceauditnumber011) {
		this.systemstraceauditnumber011 = systemstraceauditnumber011;
	}

	public String getTimelocaltransaction012() {
		return this.timelocaltransaction012;
	}

	public void setTimelocaltransaction012(String timelocaltransaction012) {
		this.timelocaltransaction012 = timelocaltransaction012;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getToaccounttitle() {
		return this.toaccounttitle;
	}

	public void setToaccounttitle(String toaccounttitle) {
		this.toaccounttitle = toaccounttitle;
	}

	public String getTransactiondescription104() {
		return this.transactiondescription104;
	}

	public void setTransactiondescription104(String transactiondescription104) {
		this.transactiondescription104 = transactiondescription104;
	}

	public String getTransmissiondatetime007() {
		return this.transmissiondatetime007;
	}

	public void setTransmissiondatetime007(String transmissiondatetime007) {
		this.transmissiondatetime007 = transmissiondatetime007;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public TblRequest getTblRequest() {
		return this.tblRequest;
	}

	public void setTblRequest(TblRequest tblRequest) {
		this.tblRequest = tblRequest;
	}

}