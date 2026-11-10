package org.ais.hr.user_management.dto;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/30/2021
 * Time: 3:37 PM
 * Project : jcash
 */
public class UpdateModuleReq {


    private long moduleId;

    private String moduleDescr;

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleDescr() {
        return moduleDescr;
    }

    public void setModuleDescr(String moduleDescr) {
        this.moduleDescr = moduleDescr;
    }



}
