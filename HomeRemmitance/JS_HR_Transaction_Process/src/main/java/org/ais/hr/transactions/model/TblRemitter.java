package org.ais.hr.transactions.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_REMITTER database table.
 * 
 */
@Entity
@Table(name="TBL_REMITTER")
@NamedQuery(name="TblRemitter.findAll", query="SELECT t FROM TblRemitter t")
public class TblRemitter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_REMITTER_REMITTERID_GENERATOR", sequenceName="TBL_REMITTER_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_REMITTER_REMITTERID_GENERATOR")
	@Column(name="REMITTER_ID")
	private long remitterId;

	@Column(name="ADAPTEE_CODE")
	private String adapteeCode;

	@Column(name="AGENT_CODE")
	private String agentCode;

	@Column(name="AGENT_ID")
	private String agentId;

	private String agntcorsp;
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	private Date createdate;
	@JsonIgnore
	private BigDecimal createuser;

	private String details;

	@Column(name="ENABLE_INST_RESP")
	private String enableInstResp;

	@Column(name="ENABLE_PASS_ENC")
	private String enablePassEnc;

	@Column(name="FTEX_FIRST")
	private String ftexFirst;

	@Column(name="INST_RESP_CODE")
	private String instRespCode;

	@Column(name="INST_RESP_MSG")
	private String instRespMsg;

	@Column(name="INTG_TYPE")
	private String intgType;

	@Column(name="IS_ACTIVE")
	private String isActive;
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;
	@JsonIgnore
	private BigDecimal lastupdateuser;

	private String model;
	@JsonIgnore
	@Column(name="PARTNER_ID")
	private String partnerId;
	@JsonIgnore
	private String password;

	@Column(name="REMITTER_NAME")
	private String remitterName;

	@Column(name="REMITTER_PREFIX")
	private String remitterPrefix;

	@Column(name="SOAP_HDRPARM")
	private String soapHdrparm;

	@Column(name="SOAP_HDRPARMTYPE")
	private String soapHdrparmtype;

	@Column(name="SOAP_HDRSVC")
	private String soapHdrsvc;

	@Column(name="SRVICE_NAME")
	private String srviceName;
	@JsonIgnore
	private BigDecimal updateindex;

	private String wsurl;

	//bi-directional many-to-one association to TblFininstBank

	@ManyToOne
	@JoinColumn(name="FININST_BANK_ID")
	private TblFininstBank tblFininstBank;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TblUser tblUser;

	//bi-directional many-to-one association to TblRemitterDetail
	@JsonIgnore
	@OneToMany(mappedBy="tblRemitter")
	private List<TblRemitterDetail> tblRemitterDetails;

	//bi-directional many-to-one association to TblTransHead
	@JsonIgnore
	@OneToMany(mappedBy="tblRemitter")
	private List<TblTransHead> tblTransHeads;

	public TblRemitter() {
	}

	public long getRemitterId() {
		return this.remitterId;
	}

	public void setRemitterId(long remitterId) {
		this.remitterId = remitterId;
	}

	public String getAdapteeCode() {
		return this.adapteeCode;
	}

	public void setAdapteeCode(String adapteeCode) {
		this.adapteeCode = adapteeCode;
	}

	public String getAgentCode() {
		return this.agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentId() {
		return this.agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgntcorsp() {
		return this.agntcorsp;
	}

	public void setAgntcorsp(String agntcorsp) {
		this.agntcorsp = agntcorsp;
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

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getEnableInstResp() {
		return this.enableInstResp;
	}

	public void setEnableInstResp(String enableInstResp) {
		this.enableInstResp = enableInstResp;
	}

	public String getEnablePassEnc() {
		return this.enablePassEnc;
	}

	public void setEnablePassEnc(String enablePassEnc) {
		this.enablePassEnc = enablePassEnc;
	}

	public String getFtexFirst() {
		return this.ftexFirst;
	}

	public void setFtexFirst(String ftexFirst) {
		this.ftexFirst = ftexFirst;
	}

	public String getInstRespCode() {
		return this.instRespCode;
	}

	public void setInstRespCode(String instRespCode) {
		this.instRespCode = instRespCode;
	}

	public String getInstRespMsg() {
		return this.instRespMsg;
	}

	public void setInstRespMsg(String instRespMsg) {
		this.instRespMsg = instRespMsg;
	}

	public String getIntgType() {
		return this.intgType;
	}

	public void setIntgType(String intgType) {
		this.intgType = intgType;
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

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemitterName() {
		return this.remitterName;
	}

	public void setRemitterName(String remitterName) {
		this.remitterName = remitterName;
	}

	public String getRemitterPrefix() {
		return this.remitterPrefix;
	}

	public void setRemitterPrefix(String remitterPrefix) {
		this.remitterPrefix = remitterPrefix;
	}

	public String getSoapHdrparm() {
		return this.soapHdrparm;
	}

	public void setSoapHdrparm(String soapHdrparm) {
		this.soapHdrparm = soapHdrparm;
	}

	public String getSoapHdrparmtype() {
		return this.soapHdrparmtype;
	}

	public void setSoapHdrparmtype(String soapHdrparmtype) {
		this.soapHdrparmtype = soapHdrparmtype;
	}

	public String getSoapHdrsvc() {
		return this.soapHdrsvc;
	}

	public void setSoapHdrsvc(String soapHdrsvc) {
		this.soapHdrsvc = soapHdrsvc;
	}

	public String getSrviceName() {
		return this.srviceName;
	}

	public void setSrviceName(String srviceName) {
		this.srviceName = srviceName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getWsurl() {
		return this.wsurl;
	}

	public void setWsurl(String wsurl) {
		this.wsurl = wsurl;
	}

	public TblFininstBank getTblFininstBank() {
		return this.tblFininstBank;
	}

	public void setTblFininstBank(TblFininstBank tblFininstBank) {
		this.tblFininstBank = tblFininstBank;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public List<TblRemitterDetail> getTblRemitterDetails() {
		return this.tblRemitterDetails;
	}

	public void setTblRemitterDetails(List<TblRemitterDetail> tblRemitterDetails) {
		this.tblRemitterDetails = tblRemitterDetails;
	}

	public TblRemitterDetail addTblRemitterDetail(TblRemitterDetail tblRemitterDetail) {
		getTblRemitterDetails().add(tblRemitterDetail);
		tblRemitterDetail.setTblRemitter(this);

		return tblRemitterDetail;
	}

	public TblRemitterDetail removeTblRemitterDetail(TblRemitterDetail tblRemitterDetail) {
		getTblRemitterDetails().remove(tblRemitterDetail);
		tblRemitterDetail.setTblRemitter(null);

		return tblRemitterDetail;
	}

	public List<TblTransHead> getTblTransHeads() {
		return this.tblTransHeads;
	}

	public void setTblTransHeads(List<TblTransHead> tblTransHeads) {
		this.tblTransHeads = tblTransHeads;
	}

	public TblTransHead addTblTransHead(TblTransHead tblTransHead) {
		getTblTransHeads().add(tblTransHead);
		tblTransHead.setTblRemitter(this);

		return tblTransHead;
	}

	public TblTransHead removeTblTransHead(TblTransHead tblTransHead) {
		getTblTransHeads().remove(tblTransHead);
		tblTransHead.setTblRemitter(null);

		return tblTransHead;
	}

}