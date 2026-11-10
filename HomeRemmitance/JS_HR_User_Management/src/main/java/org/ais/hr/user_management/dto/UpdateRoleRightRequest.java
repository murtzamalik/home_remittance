package org.ais.hr.user_management.dto;

import java.math.BigDecimal;
import java.util.Date;

public class UpdateRoleRightRequest {

    private long roleRightsId;

    private String authorizeAllowed;

    private Date checkDate;

    private String checkerComments;

    private BigDecimal checkerId;

    private Date createdate;

    private BigDecimal createuser;

    private String hideYn;

    private String insertAllowed;

    private Date lastupdatedate;

    private BigDecimal lastupdateuser;

    private String mcStatus;

    private String selectAllowed;

    private String updateAllowed;

    private BigDecimal updateindex;


    public long getRoleRightsId() {
        return roleRightsId;
    }

    public void setRoleRightsId(long roleRightsId) {
        this.roleRightsId = roleRightsId;
    }

    public String getAuthorizeAllowed() {
        return authorizeAllowed;
    }

    public void setAuthorizeAllowed(String authorizeAllowed) {
        this.authorizeAllowed = authorizeAllowed;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckerComments() {
        return checkerComments;
    }

    public void setCheckerComments(String checkerComments) {
        this.checkerComments = checkerComments;
    }

    public BigDecimal getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(BigDecimal checkerId) {
        this.checkerId = checkerId;
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

    public String getHideYn() {
        return hideYn;
    }

    public void setHideYn(String hideYn) {
        this.hideYn = hideYn;
    }

    public String getInsertAllowed() {
        return insertAllowed;
    }

    public void setInsertAllowed(String insertAllowed) {
        this.insertAllowed = insertAllowed;
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

    public String getMcStatus() {
        return mcStatus;
    }

    public void setMcStatus(String mcStatus) {
        this.mcStatus = mcStatus;
    }

    public String getSelectAllowed() {
        return selectAllowed;
    }

    public void setSelectAllowed(String selectAllowed) {
        this.selectAllowed = selectAllowed;
    }

    public String getUpdateAllowed() {
        return updateAllowed;
    }

    public void setUpdateAllowed(String updateAllowed) {
        this.updateAllowed = updateAllowed;
    }

    public BigDecimal getUpdateindex() {
        return updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }
}
