package org.ais.hr.login.dto.feignDto;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 2/14/2022
 * Time: 2:16 PM
 * Project : login
 */
public class LdapLoginResponse {


    private String status;

    private String message;

    private LoginResponseData body;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginResponseData getBody() {
        return body;
    }

    public void setBody(LoginResponseData body) {
        this.body = body;
    }
}
