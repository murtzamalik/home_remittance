package org.ais.hr.user_management.controller.user_management;

import io.swagger.annotations.Api;
import org.ais.hr.user_management.controller.AbstractApi;
import org.ais.hr.user_management.dto.*;
import org.ais.hr.user_management.model.*;
import org.ais.hr.user_management.service.JsHrUserManagementService;

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
import java.util.Date;
import java.util.HashMap;


@Api(value = "JS HR User Management Post Api", description = "POST API For JS HR User Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/userManagement")
public class JsHrUserMngtPostApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsHrUserMngtPostApi.class);

    @Autowired
    JsHrUserManagementService jsHrUserManagementService;

    @RequestMapping(value = "/saveModule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveModule(@Valid @RequestBody TblModuleRequest saveModuleReq, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblModule tblModule = new TblModule();

                tblModule.setModuleDescr(saveModuleReq.getModuleDescr());
                tblModule.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

                tblModule = jsHrUserManagementService.saveModule(tblModule);

                if (tblModule != null && tblModule.getModuleId() > 0) {
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 18);
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblModule);

                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveMenu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveMenu(@Valid @RequestBody TblMenuRequest saveMenuRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblModule tblModule = new TblModule();
                TblMenu tblMenu = new TblMenu();

                tblMenu.setIconName(saveMenuRequest.getIconName());
                tblMenu.setIconPath(saveMenuRequest.getIconPath());
                tblMenu.setMenuCode(saveMenuRequest.getMenuCode());
                tblMenu.setMenuDescription(saveMenuRequest.getMenuDescription());
                tblMenu.setMenuPath(saveMenuRequest.getMenuPath());
                tblMenu.setMenuType(saveMenuRequest.getMenuType());
                tblMenu.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));


                tblModule.setModuleId(Long.valueOf(saveMenuRequest.getModuleId()));

                tblMenu.setTblModule(tblModule);

                tblMenu = jsHrUserManagementService.saveMenu(tblMenu);

                if (tblMenu != null && tblMenu.getMenuId() > 0) {
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 20);
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblMenu);

                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveRole(@Valid @RequestBody TblRoleRequest saveRoleRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblRole tblRole = new TblRole();

                tblRole.setRoleDescr(saveRoleRequest.getRoleDescr());
                tblRole.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));


                tblRole = jsHrUserManagementService.saveRole(tblRole);

                if (tblRole != null && tblRole.getRoleId() > 0) {
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 22);
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblRole);

                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveRoleRights", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveRoleRights(@Valid @RequestBody TblRoleRightRequest saveRoleRightRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblRoleRight tblRoleRights = new TblRoleRight();

                tblRoleRights.setSelectAllowed(saveRoleRightRequest.getSelectAllowed());
                tblRoleRights.setUpdateAllowed(saveRoleRightRequest.getUpdateAllowed());
                tblRoleRights.setAuthorizeAllowed(saveRoleRightRequest.getAuthorizeAllowed());
                tblRoleRights.setHideYn(saveRoleRightRequest.getHideYn());
                tblRoleRights.setInsertAllowed(saveRoleRightRequest.getInsertAllowed());
                tblRoleRights.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

                TblRole tblRole = new TblRole();
                TblMenu tblMenu = new TblMenu();

                tblRole.setRoleId(saveRoleRightRequest.getRoleId());
                tblMenu.setMenuId(saveRoleRightRequest.getMenuId());

                tblRoleRights.setTblRole(tblRole);
                tblRoleRights.setTblMenu(tblMenu);


                tblRoleRights = jsHrUserManagementService.saveRoleRights(tblRoleRights);

                if (tblRoleRights != null && tblRoleRights.getRoleRightsId() > 0) {
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 24);
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblRoleRights);

                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveUser(@Valid @RequestBody TblUserRequest tblUserRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblUser  tblUser = new TblUser();
                TblOrganization tblOrganization=jsHrUserManagementService.findByOrganizationCode("001");

                tblUser.setTblOrganization(tblOrganization);
                tblUser.setUsercode(tblUserRequest.getUsercode());
                tblUser.setUsername(tblUserRequest.getUsername());
                tblUser.setEmpCode(tblUserRequest.getEmpCode());
                tblUser.setEmployeeNo(tblUserRequest.getEmployeeNo());
                tblUser.setIsActive(tblUserRequest.getIsActive());
                tblUser.setRemarks(tblUserRequest.getRemarks());
                tblUser.setStartDate(new Date());
                tblUser.setPasswordExpired("N");
                tblUser.setEmail(tblUserRequest.getEmail());
                tblUser.setMobileno(tblUserRequest.getMobileno());
                tblUser.setInternalExternal(tblUserRequest.getInternalExternal());
                tblUser.setCreatedate(new Date());
                tblUser.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                tblUser.setCanVerify("Y");
                tblUser.setCanApprove("Y");
                tblUser.setApproveOwnTrans("Y");
                tblUser.setApproveVerTrans("Y");

                TblLocation tblLocation= new TblLocation();
                tblLocation.setLocationId(tblUserRequest.getLocationId());
                tblUser.setTblLocation(tblLocation);

                TblRole tblRole=new TblRole();
                tblRole.setRoleId(tblUserRequest.getRoleId());
                tblUser.setTblRole(tblRole);


                tblUser=jsHrUserManagementService.saveUser(tblUser);
                if (tblUser != null) {
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 26);
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblUser);

                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }


}
