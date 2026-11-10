package org.ais.hr.user_management.dto;

import java.math.BigDecimal;
import java.util.Date;

public class UpdateUserRequest {
    private long userId;
    private String activationComment;
    private String allowConcon;
    private String approveLmttran;
    private String approveOwnTrans;
    private String approveVerTrans;
    private String attemptAction;
    private BigDecimal attemptPeriod;
    private BigDecimal attempted;
    private String canApprove;
    private String canVerify;
    private BigDecimal conConnection;
    private String email;
    private String empCode;
    private String employeeNo;
    private Date endDate;
    private BigDecimal htype;
    private BigDecimal idleTime;
    private String idleTimeaction;
    private String inactivationComment;
    private BigDecimal inactiveDays;
    private String instructionOf;
    private String internalExternal;
    private String isActive;
    private String keyValue;
    private String ldapEmail;
    private String ldapEmpcode;
    private String ldapName;
    private String levelCode;
    private String mobileno;
    private BigDecimal noAttempt;
    private Date perInactivation;
    private String reason;
    private String remarks;
    private String signature;
    private Date startDate;
    private String statusCode;
    private String timeCheck;
    private BigDecimal timezone;
    private String usercode;
    private String username;
    private String verifyOwnTrans;
    private long locationId;
    private long roleId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getActivationComment() {
        return activationComment;
    }

    public void setActivationComment(String activationComment) {
        this.activationComment = activationComment;
    }

    public String getAllowConcon() {
        return allowConcon;
    }

    public void setAllowConcon(String allowConcon) {
        this.allowConcon = allowConcon;
    }

    public String getApproveLmttran() {
        return approveLmttran;
    }

    public void setApproveLmttran(String approveLmttran) {
        this.approveLmttran = approveLmttran;
    }

    public String getApproveOwnTrans() {
        return approveOwnTrans;
    }

    public void setApproveOwnTrans(String approveOwnTrans) {
        this.approveOwnTrans = approveOwnTrans;
    }

    public String getApproveVerTrans() {
        return approveVerTrans;
    }

    public void setApproveVerTrans(String approveVerTrans) {
        this.approveVerTrans = approveVerTrans;
    }

    public String getAttemptAction() {
        return attemptAction;
    }

    public void setAttemptAction(String attemptAction) {
        this.attemptAction = attemptAction;
    }

    public BigDecimal getAttemptPeriod() {
        return attemptPeriod;
    }

    public void setAttemptPeriod(BigDecimal attemptPeriod) {
        this.attemptPeriod = attemptPeriod;
    }

    public BigDecimal getAttempted() {
        return attempted;
    }

    public void setAttempted(BigDecimal attempted) {
        this.attempted = attempted;
    }

    public String getCanApprove() {
        return canApprove;
    }

    public void setCanApprove(String canApprove) {
        this.canApprove = canApprove;
    }

    public String getCanVerify() {
        return canVerify;
    }

    public void setCanVerify(String canVerify) {
        this.canVerify = canVerify;
    }

    public BigDecimal getConConnection() {
        return conConnection;
    }

    public void setConConnection(BigDecimal conConnection) {
        this.conConnection = conConnection;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getHtype() {
        return htype;
    }

    public void setHtype(BigDecimal htype) {
        this.htype = htype;
    }

    public BigDecimal getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(BigDecimal idleTime) {
        this.idleTime = idleTime;
    }

    public String getIdleTimeaction() {
        return idleTimeaction;
    }

    public void setIdleTimeaction(String idleTimeaction) {
        this.idleTimeaction = idleTimeaction;
    }

    public String getInactivationComment() {
        return inactivationComment;
    }

    public void setInactivationComment(String inactivationComment) {
        this.inactivationComment = inactivationComment;
    }

    public BigDecimal getInactiveDays() {
        return inactiveDays;
    }

    public void setInactiveDays(BigDecimal inactiveDays) {
        this.inactiveDays = inactiveDays;
    }

    public String getInstructionOf() {
        return instructionOf;
    }

    public void setInstructionOf(String instructionOf) {
        this.instructionOf = instructionOf;
    }

    public String getInternalExternal() {
        return internalExternal;
    }

    public void setInternalExternal(String internalExternal) {
        this.internalExternal = internalExternal;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getLdapEmail() {
        return ldapEmail;
    }

    public void setLdapEmail(String ldapEmail) {
        this.ldapEmail = ldapEmail;
    }

    public String getLdapEmpcode() {
        return ldapEmpcode;
    }

    public void setLdapEmpcode(String ldapEmpcode) {
        this.ldapEmpcode = ldapEmpcode;
    }

    public String getLdapName() {
        return ldapName;
    }

    public void setLdapName(String ldapName) {
        this.ldapName = ldapName;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public BigDecimal getNoAttempt() {
        return noAttempt;
    }

    public void setNoAttempt(BigDecimal noAttempt) {
        this.noAttempt = noAttempt;
    }

    public Date getPerInactivation() {
        return perInactivation;
    }

    public void setPerInactivation(Date perInactivation) {
        this.perInactivation = perInactivation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getTimeCheck() {
        return timeCheck;
    }

    public void setTimeCheck(String timeCheck) {
        this.timeCheck = timeCheck;
    }

    public BigDecimal getTimezone() {
        return timezone;
    }

    public void setTimezone(BigDecimal timezone) {
        this.timezone = timezone;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerifyOwnTrans() {
        return verifyOwnTrans;
    }

    public void setVerifyOwnTrans(String verifyOwnTrans) {
        this.verifyOwnTrans = verifyOwnTrans;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
