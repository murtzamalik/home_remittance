package org.ais.hr.transactions.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_REMITTER_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_REMITTER_DETAIL")
@NamedQuery(name="TblRemitterDetail.findAll", query="SELECT t FROM TblRemitterDetail t")
public class TblRemitterDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_REMITTER_DETAIL_REMITTERDETAILID_GENERATOR", sequenceName="TBL_REMITTER_DETAIL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_REMITTER_DETAIL_REMITTERDETAILID_GENERATOR")
	@Column(name="REMITTER_DETAIL_ID")
	private long remitterDetailId;

	private String agentid;

	@Column(name="API_TYPE_CODE")
	private String apiTypeCode;

	@Column(name="API_URL")
	private String apiUrl;

	@Column(name="API_VERSION")
	private String apiVersion;

	@Column(name="CLASS_NAME")
	private String className;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IP_ADDRESS")
	private String ipAddress;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private String model;

	private String password;

	@Column(name="REMIT_MODE")
	private String remitMode;

	private BigDecimal updateindex;

	@Column(name="USER_ID")
	private String userId;

	@Column(name="VALIDATE_IP")
	private String validateIp;

	//bi-directional many-to-one association to TblRemitter
	@ManyToOne
	@JoinColumn(name="REMITTER_ID")
	private TblRemitter tblRemitter;

	public TblRemitterDetail() {
	}

	public long getRemitterDetailId() {
		return this.remitterDetailId;
	}

	public void setRemitterDetailId(long remitterDetailId) {
		this.remitterDetailId = remitterDetailId;
	}

	public String getAgentid() {
		return this.agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public String getApiTypeCode() {
		return this.apiTypeCode;
	}

	public void setApiTypeCode(String apiTypeCode) {
		this.apiTypeCode = apiTypeCode;
	}

	public String getApiUrl() {
		return this.apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getApiVersion() {
		return this.apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemitMode() {
		return this.remitMode;
	}

	public void setRemitMode(String remitMode) {
		this.remitMode = remitMode;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getValidateIp() {
		return this.validateIp;
	}

	public void setValidateIp(String validateIp) {
		this.validateIp = validateIp;
	}

	public TblRemitter getTblRemitter() {
		return this.tblRemitter;
	}

	public void setTblRemitter(TblRemitter tblRemitter) {
		this.tblRemitter = tblRemitter;
	}

}