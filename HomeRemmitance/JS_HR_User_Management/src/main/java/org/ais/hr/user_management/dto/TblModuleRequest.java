package org.ais.hr.user_management.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
public class TblModuleRequest {


    @NotEmpty(message = "Please Enter Module Description")
    @NotNull(message = "Please Enter Module Description")
    private String moduleDescr;


    public String getModuleDescr() {
        return moduleDescr;
    }

    public void setModuleDescr(String moduleDescr) {
        this.moduleDescr = moduleDescr;
    }
}
