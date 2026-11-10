package org.ais.hr.login.dto;


import org.ais.hr.login.dto.Menu;
import org.ais.hr.login.dto.feignDto.LoginResponseData;
import org.ais.hr.login.model.TblUser;


import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:10 PM
 * Project : jcash
 */
public class LoginResponse {


    private UserResponse userResponse;

    private String token;

    private List<Menu> menu;

    private boolean login = false;

    private String sessionTimeOut;

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getSessionTimeOut() {
        return sessionTimeOut;
    }

    public void setSessionTimeOut(String sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }
}
