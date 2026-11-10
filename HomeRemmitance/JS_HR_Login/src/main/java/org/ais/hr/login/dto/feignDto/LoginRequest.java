package org.ais.hr.login.dto.feignDto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:07 PM
 * Project : jcash
 */

@Validated
public class LoginRequest {

    @NotEmpty(message = "Email Cannot Be Empty")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message = "Please Enter Valid Email Address")
    private String emailId;

    @NotEmpty(message = "Password Cannot Be Empty")
    @NotNull(message = "Password Cannot Be Null")
    private String password;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
