package org.ais.hr.user_management.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
public class TblRoleRequest {

    @NotNull(message = "Please Enter Role Description")
    @NotEmpty(message = "Please Enter Role Description")
    private String roleDescr;



    public String getRoleDescr() {
        return roleDescr;
    }

    public void setRoleDescr(String roleDescr) {
        this.roleDescr = roleDescr;
    }
}
