package org.ais.hr.login.Service.impl;

import org.ais.hr.login.Service.LoginService;
import org.ais.hr.login.dto.LoggedUserDetail;
import org.ais.hr.login.dto.feignDto.LdapLoginResponse;
import org.ais.hr.login.dto.feignDto.LoginRequest;
import org.ais.hr.login.model.*;
import org.ais.hr.login.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

/*
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 2/14/2022
 * Time: 2:01 PM
 * Project : login
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class LoginServiceImpl implements LoginService {


    @Autowired
    private TblUserRepo tblUserRepo;

    @Autowired
    private TblUserLoginHistoryRepo tblUserLoginHistoryRepo;

    @Autowired
    private TblAuthAccessTokenRepo tblAuthAccessTokenRepo;

    @Autowired
    private TblModuleRepo tblModuleRepo;

    @Autowired
    private TblRoleRightsRepo tblRoleRightsRepo;

    @Autowired
    private TblUserActivityLogRepo tblUserActivityLogRepo;

    @Autowired
    private Environment environment;

    @Override
    public TblUser findByLdapEmployeeId(String employeeID) {
        return tblUserRepo.findByLdapEmpcode(employeeID);
    }

    @Override
    public TblUser saveLdapFreshUser(TblUser tblUser) {
        return tblUserRepo.saveAndFlush(tblUser);
    }

    @Override
    public TblUserLoginHistory saveLoginHistory(TblUserLoginHistory tblUserLoginHistory) {

        return tblUserLoginHistoryRepo.saveAndFlush(tblUserLoginHistory);
    }

    @Override
    public TblUserLoginHistory logoutUser(LoggedUserDetail loggedUserDetail) {

        TblUserLoginHistory tblUserLoginHistory = tblUserLoginHistoryRepo.findById(loggedUserDetail.getLoginId()).orElse(null);
        if (tblUserLoginHistory != null) {


            tblUserLoginHistory.setLogoutDate(new Date());
            tblUserLoginHistory= tblUserLoginHistoryRepo.saveAndFlush(tblUserLoginHistory);
            TblUserActivityLog tblUserActivityLog=new TblUserActivityLog();
            TblUser tblUser=new TblUser();
            tblUser.setUserId(loggedUserDetail.getUserId());
            tblUserActivityLog.setTblUser(tblUser);
            TblActivity tblActivity=new TblActivity();
            tblActivity.setActivityId(14);
            tblUserActivityLog.setTblUserLoginHistory(tblUserLoginHistory);
            tblUserActivityLog.setTblActivity(tblActivity);
            tblUserActivityLog.setActivityDate(new Date());
            tblUserActivityLog=saveTblUserActivityLog(tblUserActivityLog);
            return tblUserLoginHistory;

        } else {
            return null;
        }
    }

    @Override
    public TblAuthAccessToken saveAuthAccessToken(TblAuthAccessToken tblAuthAccessToken) {
        return tblAuthAccessTokenRepo.save(tblAuthAccessToken);
    }



    @Override
    public List<TblModule> getModulesByRoleId(long roleCode) {
        return tblModuleRepo.getRoleWiseModule(roleCode);
    }

    @Override
    public List<TblRoleRight> getRoleAndModuelWisePages(long modulecodeId, long roleId) {
        return tblRoleRightsRepo.getRoleAndModuelWisePages(modulecodeId,roleId);
    }

    @Override
    public TblUserActivityLog saveTblUserActivityLog(TblUserActivityLog tblUserActivityLog) {
        return tblUserActivityLogRepo.saveAndFlush(tblUserActivityLog);
    }

    @Override
    public TblAuthAccessToken tokenVerification(BigDecimal userId, String token) {
        return tblAuthAccessTokenRepo.findByUserIdAndAccessToken(userId,token);
    }

    @Override
    public void revokeAuthAccessToken(BigDecimal userId, String token) {
        tblAuthAccessTokenRepo.deleteByUserIdAndAccessToken(userId, token);
    }

    @Override
    public void revokeAllAuthAccessTokens(BigDecimal userId) {
        tblAuthAccessTokenRepo.deleteByUserId(userId);
    }

    @Override
    public LdapLoginResponse LdapLogin(LoginRequest loginRequest) throws MalformedURLException {
        String url = environment.getProperty("login.url");

        try {
            ResponseEntity<LdapLoginResponse> response = new RestTemplate().postForEntity(url, loginRequest, LdapLoginResponse.class);
            if (response != null) {
                LdapLoginResponse ldapLoginResponse = response.getBody();
                return ldapLoginResponse;
            } else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }
}
