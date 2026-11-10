package org.ais.hr.fileupload.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_USER database table.
 * 
 */
@Entity
@Table(name="TBL_USER")
@NamedQuery(name="TblUser.findAll", query="SELECT t FROM TblUser t")
public class TblUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_USER_USERID_GENERATOR", sequenceName="TBL_USER_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_USER_USERID_GENERATOR")
	@Column(name="USER_ID")
	private long userId;

	@Column(name="ACTIVATION_COMMENT")
	private String activationComment;

	@Column(name="ALLOW_CONCON")
	private String allowConcon;

	@Column(name="APPROVE_LMTTRAN")
	private String approveLmttran;

	@Column(name="APPROVE_OWN_TRANS")
	private String approveOwnTrans;

	@Column(name="APPROVE_VER_TRANS")
	private String approveVerTrans;

	@Column(name="ATTEMPT_ACTION")
	private String attemptAction;

	@Column(name="ATTEMPT_PERIOD")
	private BigDecimal attemptPeriod;

	private BigDecimal attempted;

	@Column(name="CAN_APPROVE")
	private String canApprove;

	@Column(name="CAN_VERIFY")
	private String canVerify;

	@Column(name="CON_CONNECTION")
	private BigDecimal conConnection;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String email;

	@Column(name="EMP_CODE")
	private String empCode;

	@Column(name="EMPLOYEE_NO")
	private String employeeNo;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	private BigDecimal htype;

	@Column(name="IDLE_TIME")
	private BigDecimal idleTime;

	@Column(name="IDLE_TIMEACTION")
	private String idleTimeaction;

	@Column(name="INACTIVATION_COMMENT")
	private String inactivationComment;

	@Column(name="INACTIVE_DAYS")
	private BigDecimal inactiveDays;

	@Column(name="INSTRUCTION_OF")
	private String instructionOf;

	@Column(name="INTERNAL_EXTERNAL")
	private String internalExternal;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="KEY_VALUE")
	private String keyValue;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_LOGIN")
	private Date lastLogin;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_PASS_CHANGE")
	private Date lastPassChange;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="LDAP_EMAIL")
	private String ldapEmail;

	@Column(name="LDAP_EMPCODE")
	private String ldapEmpcode;

	@Column(name="LDAP_NAME")
	private String ldapName;

	@Column(name="LEVEL_CODE")
	private String levelCode;

	@Column(name="LOG_EVENT_BUTTON")
	private String logEventButton;

	@Column(name="LOG_EVENT_LISTER")
	private String logEventLister;

	@Column(name="LOG_EVENT_MENU")
	private String logEventMenu;

	@Column(name="LOG_EVENT_OPTION")
	private String logEventOption;

	private String mobileno;

	@Column(name="NO_ATTEMPT")
	private BigDecimal noAttempt;

	@Temporal(TemporalType.DATE)
	@Column(name="PASS_EXP_DATE")
	private Date passExpDate;

	@Column(name="PASS_EXP_DAYS")
	private BigDecimal passExpDays;

	@Column(name="PASS_GRACE_PERIOD")
	private BigDecimal passGracePeriod;

	private String password;

	@Column(name="PASSWORD_EXPIRED")
	private String passwordExpired;

	@Temporal(TemporalType.DATE)
	@Column(name="PER_INACTIVATION")
	private Date perInactivation;

	private String reason;

	private String remarks;

	private String signature;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="STATUS_CODE")
	private String statusCode;

	@Column(name="TIME_CHECK")
	private String timeCheck;

	private BigDecimal timezone;

	private BigDecimal updateindex;

	private String usercode;

	private String username;

	@Column(name="VERIFY_OWN_TRANS")
	private String verifyOwnTrans;

	//bi-directional many-to-one association to TblRemitter
	@OneToMany(mappedBy="tblUser")
	private List<TblRemitter> tblRemitters;

	//bi-directional many-to-one association to LkpReason
	@ManyToOne
	@JoinColumn(name="ACTIVATION_REASON_ID")
	private LkpReason lkpReason1;

	//bi-directional many-to-one association to LkpReason
	@ManyToOne
	@JoinColumn(name="INACTIVATION_REASON_ID")
	private LkpReason lkpReason2;

	//bi-directional many-to-one association to TblLocation
	@ManyToOne
	@JoinColumn(name="LOCATION_ID")
	private TblLocation tblLocation;

	//bi-directional many-to-one association to TblOrganization
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID")
	private TblOrganization tblOrganization;

	//bi-directional many-to-one association to TblUserLoginHistory
	@OneToMany(mappedBy="tblUser")
	private List<TblUserLoginHistory> tblUserLoginHistories;

	public TblUser() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getActivationComment() {
		return this.activationComment;
	}

	public void setActivationComment(String activationComment) {
		this.activationComment = activationComment;
	}

	public String getAllowConcon() {
		return this.allowConcon;
	}

	public void setAllowConcon(String allowConcon) {
		this.allowConcon = allowConcon;
	}

	public String getApproveLmttran() {
		return this.approveLmttran;
	}

	public void setApproveLmttran(String approveLmttran) {
		this.approveLmttran = approveLmttran;
	}

	public String getApproveOwnTrans() {
		return this.approveOwnTrans;
	}

	public void setApproveOwnTrans(String approveOwnTrans) {
		this.approveOwnTrans = approveOwnTrans;
	}

	public String getApproveVerTrans() {
		return this.approveVerTrans;
	}

	public void setApproveVerTrans(String approveVerTrans) {
		this.approveVerTrans = approveVerTrans;
	}

	public String getAttemptAction() {
		return this.attemptAction;
	}

	public void setAttemptAction(String attemptAction) {
		this.attemptAction = attemptAction;
	}

	public BigDecimal getAttemptPeriod() {
		return this.attemptPeriod;
	}

	public void setAttemptPeriod(BigDecimal attemptPeriod) {
		this.attemptPeriod = attemptPeriod;
	}

	public BigDecimal getAttempted() {
		return this.attempted;
	}

	public void setAttempted(BigDecimal attempted) {
		this.attempted = attempted;
	}

	public String getCanApprove() {
		return this.canApprove;
	}

	public void setCanApprove(String canApprove) {
		this.canApprove = canApprove;
	}

	public String getCanVerify() {
		return this.canVerify;
	}

	public void setCanVerify(String canVerify) {
		this.canVerify = canVerify;
	}

	public BigDecimal getConConnection() {
		return this.conConnection;
	}

	public void setConConnection(BigDecimal conConnection) {
		this.conConnection = conConnection;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpCode() {
		return this.empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmployeeNo() {
		return this.employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getHtype() {
		return this.htype;
	}

	public void setHtype(BigDecimal htype) {
		this.htype = htype;
	}

	public BigDecimal getIdleTime() {
		return this.idleTime;
	}

	public void setIdleTime(BigDecimal idleTime) {
		this.idleTime = idleTime;
	}

	public String getIdleTimeaction() {
		return this.idleTimeaction;
	}

	public void setIdleTimeaction(String idleTimeaction) {
		this.idleTimeaction = idleTimeaction;
	}

	public String getInactivationComment() {
		return this.inactivationComment;
	}

	public void setInactivationComment(String inactivationComment) {
		this.inactivationComment = inactivationComment;
	}

	public BigDecimal getInactiveDays() {
		return this.inactiveDays;
	}

	public void setInactiveDays(BigDecimal inactiveDays) {
		this.inactiveDays = inactiveDays;
	}

	public String getInstructionOf() {
		return this.instructionOf;
	}

	public void setInstructionOf(String instructionOf) {
		this.instructionOf = instructionOf;
	}

	public String getInternalExternal() {
		return this.internalExternal;
	}

	public void setInternalExternal(String internalExternal) {
		this.internalExternal = internalExternal;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getKeyValue() {
		return this.keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastPassChange() {
		return this.lastPassChange;
	}

	public void setLastPassChange(Date lastPassChange) {
		this.lastPassChange = lastPassChange;
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

	public String getLdapEmail() {
		return this.ldapEmail;
	}

	public void setLdapEmail(String ldapEmail) {
		this.ldapEmail = ldapEmail;
	}

	public String getLdapEmpcode() {
		return this.ldapEmpcode;
	}

	public void setLdapEmpcode(String ldapEmpcode) {
		this.ldapEmpcode = ldapEmpcode;
	}

	public String getLdapName() {
		return this.ldapName;
	}

	public void setLdapName(String ldapName) {
		this.ldapName = ldapName;
	}

	public String getLevelCode() {
		return this.levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLogEventButton() {
		return this.logEventButton;
	}

	public void setLogEventButton(String logEventButton) {
		this.logEventButton = logEventButton;
	}

	public String getLogEventLister() {
		return this.logEventLister;
	}

	public void setLogEventLister(String logEventLister) {
		this.logEventLister = logEventLister;
	}

	public String getLogEventMenu() {
		return this.logEventMenu;
	}

	public void setLogEventMenu(String logEventMenu) {
		this.logEventMenu = logEventMenu;
	}

	public String getLogEventOption() {
		return this.logEventOption;
	}

	public void setLogEventOption(String logEventOption) {
		this.logEventOption = logEventOption;
	}

	public String getMobileno() {
		return this.mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public BigDecimal getNoAttempt() {
		return this.noAttempt;
	}

	public void setNoAttempt(BigDecimal noAttempt) {
		this.noAttempt = noAttempt;
	}

	public Date getPassExpDate() {
		return this.passExpDate;
	}

	public void setPassExpDate(Date passExpDate) {
		this.passExpDate = passExpDate;
	}

	public BigDecimal getPassExpDays() {
		return this.passExpDays;
	}

	public void setPassExpDays(BigDecimal passExpDays) {
		this.passExpDays = passExpDays;
	}

	public BigDecimal getPassGracePeriod() {
		return this.passGracePeriod;
	}

	public void setPassGracePeriod(BigDecimal passGracePeriod) {
		this.passGracePeriod = passGracePeriod;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordExpired() {
		return this.passwordExpired;
	}

	public void setPasswordExpired(String passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	public Date getPerInactivation() {
		return this.perInactivation;
	}

	public void setPerInactivation(Date perInactivation) {
		this.perInactivation = perInactivation;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getTimeCheck() {
		return this.timeCheck;
	}

	public void setTimeCheck(String timeCheck) {
		this.timeCheck = timeCheck;
	}

	public BigDecimal getTimezone() {
		return this.timezone;
	}

	public void setTimezone(BigDecimal timezone) {
		this.timezone = timezone;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getUsercode() {
		return this.usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVerifyOwnTrans() {
		return this.verifyOwnTrans;
	}

	public void setVerifyOwnTrans(String verifyOwnTrans) {
		this.verifyOwnTrans = verifyOwnTrans;
	}

	public List<TblRemitter> getTblRemitters() {
		return this.tblRemitters;
	}

	public void setTblRemitters(List<TblRemitter> tblRemitters) {
		this.tblRemitters = tblRemitters;
	}

	public TblRemitter addTblRemitter(TblRemitter tblRemitter) {
		getTblRemitters().add(tblRemitter);
		tblRemitter.setTblUser(this);

		return tblRemitter;
	}

	public TblRemitter removeTblRemitter(TblRemitter tblRemitter) {
		getTblRemitters().remove(tblRemitter);
		tblRemitter.setTblUser(null);

		return tblRemitter;
	}

	public LkpReason getLkpReason1() {
		return this.lkpReason1;
	}

	public void setLkpReason1(LkpReason lkpReason1) {
		this.lkpReason1 = lkpReason1;
	}

	public LkpReason getLkpReason2() {
		return this.lkpReason2;
	}

	public void setLkpReason2(LkpReason lkpReason2) {
		this.lkpReason2 = lkpReason2;
	}

	public TblLocation getTblLocation() {
		return this.tblLocation;
	}

	public void setTblLocation(TblLocation tblLocation) {
		this.tblLocation = tblLocation;
	}

	public TblOrganization getTblOrganization() {
		return this.tblOrganization;
	}

	public void setTblOrganization(TblOrganization tblOrganization) {
		this.tblOrganization = tblOrganization;
	}

	public List<TblUserLoginHistory> getTblUserLoginHistories() {
		return this.tblUserLoginHistories;
	}

	public void setTblUserLoginHistories(List<TblUserLoginHistory> tblUserLoginHistories) {
		this.tblUserLoginHistories = tblUserLoginHistories;
	}

	public TblUserLoginHistory addTblUserLoginHistory(TblUserLoginHistory tblUserLoginHistory) {
		getTblUserLoginHistories().add(tblUserLoginHistory);
		tblUserLoginHistory.setTblUser(this);

		return tblUserLoginHistory;
	}

	public TblUserLoginHistory removeTblUserLoginHistory(TblUserLoginHistory tblUserLoginHistory) {
		getTblUserLoginHistories().remove(tblUserLoginHistory);
		tblUserLoginHistory.setTblUser(null);

		return tblUserLoginHistory;
	}

}