package org.ais.hr.login.controller.usermngt;

import io.swagger.annotations.Api;

import org.ais.hr.login.Service.LoginService;
import org.ais.hr.login.controller.AbstractApi;
import org.ais.hr.login.dto.LoggedUserDetail;
import org.ais.hr.login.dto.LoginResponse;
import org.ais.hr.login.dto.Menu;
import org.ais.hr.login.dto.UserResponse;
import org.ais.hr.login.dto.feignDto.LdapLoginResponse;
import org.ais.hr.login.dto.feignDto.LoginRequest;
import org.ais.hr.login.model.*;
import org.ais.hr.login.util.AESencryption;
import org.ais.hr.login.util.JWTSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/18/2022
 * Time: 11:54 AM
 * Project : jcash
 */


@Api(value = "JS HR Api Login Post Api", description = "POST LOGIN  API For JS HR")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class JsHRLoginPostApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsHRLoginPostApi.class);

    @Autowired
    private LoginService loginService;




    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, Object>> accountLogin(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);


            LdapLoginResponse ldapLoginResponse = loginService.LdapLogin(loginRequest);
            if (ldapLoginResponse.getBody().getEmployeeID() != null && ldapLoginResponse.getBody().getBranchCode() != null) {

                LoginResponse loginResponse = new LoginResponse();

                TblUser tblUser = loginService.findByLdapEmployeeId(ldapLoginResponse.getBody().getEmployeeID());
                if (tblUser != null) {
                    Menu menu = null;
                    List<Menu> menus = new ArrayList<>();
                    List<TblModule> tblModules = loginService.getModulesByRoleId(tblUser.getTblRole().getRoleId());
                    if (tblModules != null && tblModules.size() > 0) {


                        for (TblModule tblModule : tblModules) {
                            List<Menu> childMenus = new ArrayList<>();
                            Menu childMenu = null;
                            List<TblRoleRight> tblPages = loginService.getRoleAndModuelWisePages(tblModule.getModuleId(), tblUser.getTblRole().getRoleId());
                            if (tblPages != null && tblPages.size() > 0) {
                                for (TblRoleRight tblPage : tblPages) {

//                                    if (tblPage.getMcStatus().equals("Y")) {
                                    childMenu = new Menu();
                                    childMenu.setLabel(tblPage.getTblMenu().getMenuDescription());
                                    childMenu.setTo(tblPage.getTblMenu().getMenuPath());
                                    childMenu.setSelectAllowed(tblPage.getSelectAllowed());
                                    childMenu.setUpdateAllowed(tblPage.getUpdateAllowed());
                                    childMenu.setAuthorizeAllowed(tblPage.getAuthorizeAllowed());
                                    childMenu.setHideYn(tblPage.getHideYn());
                                    childMenu.setInsertAllowed(tblPage.getInsertAllowed());
                                    childMenu.setIcon(tblPage.getTblMenu().getIconPath());

                                    childMenus.add(childMenu);
//                                    }
                                }

                            }


                            menu = new Menu();
                            menu.setLabel(tblModule.getModuleDescr());
                            menu.setItems(childMenus);
                            menu.setIcon("pi-angle-right");

                            menus.add(menu);
                        }


                        TblUserLoginHistory tblUserLoginHistory = new TblUserLoginHistory();

//                    tblUserLoginHistory.setUserId(BigDecimal.valueOf(tblUser.getUserId()));
                        tblUser.setUserId(Long.valueOf(tblUser.getUserId()));
                        tblUserLoginHistory.setTblUser(tblUser);
                        tblUserLoginHistory.setLoginDate(new Date());
                        tblUserLoginHistory.setIpAddress(getClientIpAddress(request));
                        tblUserLoginHistory.setHost("127.0.0.1");

                        tblUserLoginHistory = loginService.saveLoginHistory(tblUserLoginHistory);

                        JWTSecurity jwtSecurity = new JWTSecurity();
                        Map<String, Object> claims = new HashMap<>();
                        AESencryption aeSencryption = new AESencryption();

                        claims.put("userId", String.valueOf(tblUser.getUserId()));
                        claims.put("loginId", String.valueOf(tblUserLoginHistory.getUserLoginHistoryId()));


                        String jwt = jwtSecurity.createJWTWithClaims("JS_cash_User", claims);
                        UserResponse userResponse=new UserResponse();
                        userResponse.setUserId(tblUser.getUserId());
                        userResponse.setLdapName(tblUser.getLdapName());
                        userResponse.setEmail(tblUser.getEmail());
                        userResponse.setLdapEmail(tblUser.getLdapEmail());
                        userResponse.setEmpCode(tblUser.getEmpCode());
                        userResponse.setEmployeeNo(tblUser.getEmployeeNo());
                        userResponse.setStatusCode(tblUser.getStatusCode());


                        loginResponse.setLogin(true);
                        loginResponse.setMenu(menus);
                        loginResponse.setUserResponse(userResponse);

//                    loginResponse.setSessionTimeOut(""+100*60000);
                        loginResponse.setToken("Bearer " + aeSencryption.encrypt(jwt));
                        TblUserActivityLog tblUserActivityLog=new TblUserActivityLog();
                        tblUserActivityLog.setTblUser(tblUser);
                        TblActivity tblActivity=new TblActivity();
                        tblActivity.setActivityId(15);
                        tblUserActivityLog.setTblUserLoginHistory(tblUserLoginHistory);
                        tblUserActivityLog.setTblActivity(tblActivity);
                        tblUserActivityLog.setActivityDate(new Date());
                        tblUserActivityLog=loginService.saveTblUserActivityLog(tblUserActivityLog);
                        loginService.revokeAllAuthAccessTokens(BigDecimal.valueOf(tblUser.getUserId()));
                        TblAuthAccessToken tblAuthAccessToken=new TblAuthAccessToken();
                        tblAuthAccessToken.setUserId(BigDecimal.valueOf(tblUser.getUserId()));
                        tblAuthAccessToken.setAccessToken(loginResponse.getToken());
                        tblAuthAccessToken=loginService.saveAuthAccessToken(tblAuthAccessToken);
                        if(loginResponse!=null){
                            LOG.info("\n\n\nEXITING OF CLASS == " + className + " \n METHOD == " + methodName);
                            return getResponseFormat(HttpStatus.OK, "Login successful", loginResponse);
                        }
                        else {
                            LOG.info("\n\n\nEXITING OF CLASS == " + className + " \n METHOD == " + methodName);
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Login Failed", loginResponse);
                        }


                    }
                    else{
                        LOG.info("\n\n\nEXITING OF CLASS == " + className + " \n METHOD == " + methodName);
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No roles attached to this user ", "No roles attached to this user");
                    }

                }
                else{
                    LOG.info("\n\n\nEXITING OF CLASS == " + className + " \n METHOD == " + methodName);
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "USER LOGIN FAILED", "USER LOGIN FAILED");
            }
            ////////////////////////////////////////////////////////////////


        } else {
                LOG.info("\n\n\nEXITING OF CLASS == " + className + " \n METHOD == " + methodName);
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "LDAP LOGIN FAILED", "LDAP LOGIN FAILED");
        }


    } catch (Exception e) {
            LOG.error("\n CLASS == JsHRLoginPostApi \n METHOD == accountLogin();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == accountLogin(); OF CLASS = JsHRLoginPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> logout(HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            String authorization = request.getHeader("Authorization");
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(authorization);
            if (loggedUserDetail != null) {

                TblUserLoginHistory tblUserLoginHistory = loginService.logoutUser(loggedUserDetail);
                loginService.revokeAuthAccessToken(BigDecimal.valueOf(loggedUserDetail.getUserId()), authorization);
                if (tblUserLoginHistory != null) {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Logout SuccessFull", null);

                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.BAD_REQUEST, "Error While Logging Off", null);
                }


            } else {
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == " + className + " \n METHOD == " + methodName + "  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }
    }

}
