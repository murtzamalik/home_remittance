package org.ais.hr.login.dto;

import java.math.BigDecimal;

public class UserResponse {
    private long userId;

    private BigDecimal attempted;


    private String email;


    private String empCode;


    private String employeeNo;


    private BigDecimal htype;


    private String ldapEmail;


    private String ldapEmpcode;


    private String ldapName;


    private String statusCode;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getAttempted() {
        return attempted;
    }

    public void setAttempted(BigDecimal attempted) {
        this.attempted = attempted;
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

    public BigDecimal getHtype() {
        return htype;
    }

    public void setHtype(BigDecimal htype) {
        this.htype = htype;
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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
