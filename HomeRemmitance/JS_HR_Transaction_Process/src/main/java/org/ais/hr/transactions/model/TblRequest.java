package org.ais.hr.transactions.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_REQUEST database table.
 * 
 */
@Entity
@Table(name="TBL_REQUEST")
@NamedQuery(name="TblRequest.findAll", query="SELECT t FROM TblRequest t")
public class TblRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_REQUEST_REQUESTID_GENERATOR", sequenceName="TBL_REQUEST_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_REQUEST_REQUESTID_GENERATOR")
	@Column(name="REQUEST_ID")
	private long requestId;

	@Column(name="ACCOUNTIDENTIFICATION1_102")
	private String accountidentification1102;

	@Column(name="ACCOUNTIDENTIFICATION2_103")
	private String accountidentification2103;

	@Column(name="ACQUIRINGINSTITUTIONIDENTIFICATIONCODE_032")
	private String acquiringinstitutionidentificationcode032;

	@Column(name="AMOUNTTRANSACTION_004")
	private BigDecimal amounttransaction004;

	@Column(name="AMOUNTTRANSACTIONFEE_028")
	private BigDecimal amounttransactionfee028;

	private String bankcode;

	private String bankname;

	private String branchname;

	private String cnic;

	private String consumertitle;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CURRENCYCODESETTLEMENT_050")
	private String currencycodesettlement050;

	@Column(name="CURRENCYCODETRANSACTION_049")
	private String currencycodetransaction049;

	@Column(name="DATELOCALTRANSACTION_013")
	private String datelocaltransaction013;

	private String emailid;

	@Column(name="END_POINT")
	private String endPoint;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MERCHANTTYPE_018")
	private String merchanttype018;

	private String mti;

	private String password;

	@Column(name="PERSONALIDENTIFICATIONNUMBERDATA_052")
	private String personalidentificationnumberdata052;

	@Column(name="PRIMARYACCOUNTNUMBER_002")
	private String primaryaccountnumber002;

	@Column(name="PROCESSINGCODE_003")
	private String processingcode003;

	private String reasoncode;

	@Lob
	@Column(name="REQUEST_JSON")
	private String requestJson;

	@Column(name="RESERVEDPRIVATE_120")
	private String reservedprivate120;

	@Column(name="RESERVEDPRIVATE_127")
	private String reservedprivate127;

	@Column(name="SYSTEMSTRACEAUDITNUMBER_011")
	private String systemstraceauditnumber011;

	@Column(name="TIMELOCALTRANSACTION_012")
	private String timelocaltransaction012;

	@Column(name="TRANSACTIONDESCRIPTION_104")
	private String transactiondescription104;

	@Column(name="TRANSMISSIONDATETIME_007")
	private String transmissiondatetime007;

	private BigDecimal updateindex;

	private String userid;

	//bi-directional many-to-one association to TblResponse
	@OneToMany(mappedBy="tblRequest")
	private List<TblResponse> tblResponses;

	public TblRequest() {
	}

	public long getRequestId() {
		return this.requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
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

	public String getAcquiringinstitutionidentificationcode032() {
		return this.acquiringinstitutionidentificationcode032;
	}

	public void setAcquiringinstitutionidentificationcode032(String acquiringinstitutionidentificationcode032) {
		this.acquiringinstitutionidentificationcode032 = acquiringinstitutionidentificationcode032;
	}

	public BigDecimal getAmounttransaction004() {
		return this.amounttransaction004;
	}

	public void setAmounttransaction004(BigDecimal amounttransaction004) {
		this.amounttransaction004 = amounttransaction004;
	}

	public BigDecimal getAmounttransactionfee028() {
		return this.amounttransactionfee028;
	}

	public void setAmounttransactionfee028(BigDecimal amounttransactionfee028) {
		this.amounttransactionfee028 = amounttransactionfee028;
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

	public String getConsumertitle() {
		return this.consumertitle;
	}

	public void setConsumertitle(String consumertitle) {
		this.consumertitle = consumertitle;
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

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getEndPoint() {
		return this.endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
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

	public String getMerchanttype018() {
		return this.merchanttype018;
	}

	public void setMerchanttype018(String merchanttype018) {
		this.merchanttype018 = merchanttype018;
	}

	public String getMti() {
		return this.mti;
	}

	public void setMti(String mti) {
		this.mti = mti;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonalidentificationnumberdata052() {
		return this.personalidentificationnumberdata052;
	}

	public void setPersonalidentificationnumberdata052(String personalidentificationnumberdata052) {
		this.personalidentificationnumberdata052 = personalidentificationnumberdata052;
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

	public String getReasoncode() {
		return this.reasoncode;
	}

	public void setReasoncode(String reasoncode) {
		this.reasoncode = reasoncode;
	}

	public String getRequestJson() {
		return this.requestJson;
	}

	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}

	public String getReservedprivate120() {
		return this.reservedprivate120;
	}

	public void setReservedprivate120(String reservedprivate120) {
		this.reservedprivate120 = reservedprivate120;
	}

	public String getReservedprivate127() {
		return this.reservedprivate127;
	}

	public void setReservedprivate127(String reservedprivate127) {
		this.reservedprivate127 = reservedprivate127;
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

	public List<TblResponse> getTblResponses() {
		return this.tblResponses;
	}

	public void setTblResponses(List<TblResponse> tblResponses) {
		this.tblResponses = tblResponses;
	}

	public TblResponse addTblRespons(TblResponse tblRespons) {
		getTblResponses().add(tblRespons);
		tblRespons.setTblRequest(this);

		return tblRespons;
	}

	public TblResponse removeTblRespons(TblResponse tblRespons) {
		getTblResponses().remove(tblRespons);
		tblRespons.setTblRequest(null);

		return tblRespons;
	}

}