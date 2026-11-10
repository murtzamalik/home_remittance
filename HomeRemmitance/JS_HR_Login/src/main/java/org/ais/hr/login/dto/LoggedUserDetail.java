package org.ais.hr.login.dto;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/18/2022
 * Time: 10:10 AM
 * Project : jcash
 */
public class LoggedUserDetail {

    private long userId;



    private long loginId;



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }



    public long getLoginId() {
        return loginId;
    }

    public void setLoginId(long loginId) {
        this.loginId = loginId;
    }


}
