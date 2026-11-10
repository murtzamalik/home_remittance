package org.ais.hr.login.Service;

import org.ais.hr.login.dto.LoggedUserDetail;
import org.ais.hr.login.dto.feignDto.LdapLoginResponse;
import org.ais.hr.login.dto.feignDto.LoginRequest;
import org.ais.hr.login.model.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.List;

public interface LoginService {
    TblUser findByLdapEmployeeId(String employeeID);

    TblUser saveLdapFreshUser(TblUser tblUser);

    TblAuthAccessToken saveAuthAccessToken(TblAuthAccessToken tblAuthAccessToken);

    TblUserLoginHistory saveLoginHistory(TblUserLoginHistory tblUserLoginHistory);

    TblUserLoginHistory logoutUser(LoggedUserDetail loggedUserDetail);

    List<TblModule> getModulesByRoleId(long roleCode);

    List<TblRoleRight> getRoleAndModuelWisePages(long modulecodeId, long roleId);

    TblUserActivityLog saveTblUserActivityLog(TblUserActivityLog tblUserActivityLog);

    TblAuthAccessToken tokenVerification(BigDecimal userId, String token);

    void revokeAuthAccessToken(BigDecimal userId, String token);

    void revokeAllAuthAccessTokens(BigDecimal userId);

    LdapLoginResponse LdapLogin(LoginRequest loginRequest) throws MalformedURLException;
}
