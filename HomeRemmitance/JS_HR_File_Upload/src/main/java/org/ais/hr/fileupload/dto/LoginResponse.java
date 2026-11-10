package org.ais.hr.fileupload.dto;



import java.awt.Menu;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:10 PM
 * Project : jcash
 */
public class LoginResponse {


    private String token;

    private List<java.awt.Menu> menu;

    private boolean login = false;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<java.awt.Menu> getMenu() {
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
}
