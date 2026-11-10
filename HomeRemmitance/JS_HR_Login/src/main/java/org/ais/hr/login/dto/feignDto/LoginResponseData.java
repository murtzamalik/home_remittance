package org.ais.hr.login.dto.feignDto;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 2/14/2022
 * Time: 2:15 PM
 * Project : login
 */
public class LoginResponseData {


    private String  branchCode;

    private String mail;
    private String displayName;
    private String name;
    private String employeeID;
    private String title;


    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
