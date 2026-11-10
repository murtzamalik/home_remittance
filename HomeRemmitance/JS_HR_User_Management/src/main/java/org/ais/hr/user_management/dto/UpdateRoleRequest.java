package org.ais.hr.user_management.dto;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:54 PM
 * Project : jcash
 */
public class UpdateRoleRequest {


    private long roleId;

    private String roleDescr;



    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleDescr() {
        return roleDescr;
    }

    public void setRoleDescr(String roleDescr) {
        this.roleDescr = roleDescr;
    }


}