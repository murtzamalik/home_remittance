package org.ais.hr.user_management.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
public class TblRoleRightRequest {

    @NotEmpty(message = "Please Select Authorized Allowed")
    @NotNull(message = "Please Select Authorized Allowed")
    private String authorizeAllowed;
    @NotEmpty(message = "Please Select Hidden Allowed")
    @NotNull(message = "Please Select Hidden Allowed")
    private String hideYn;
    @NotEmpty(message = "Please Select Insert Allowed")
    @NotNull(message = "Please Select Insert Allowed")
    private String insertAllowed;
    @NotEmpty(message = "Please Select  Allowed")
    @NotNull(message = "Please Select  Allowed")
    private String selectAllowed;
    @NotEmpty(message = "Please Select Update Allowed")
    @NotNull(message = "Please Select Update Allowed")
    private String updateAllowed;
    @NotNull(message = "Please Select Menu Id")
    //@Min(value=0,message="Please Select Menu Id")
    private long menuId;

    private long roleId;


    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getAuthorizeAllowed() {
        return authorizeAllowed;
    }

    public void setAuthorizeAllowed(String authorizeAllowed) {
        this.authorizeAllowed = authorizeAllowed;
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
}
