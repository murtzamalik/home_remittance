package org.ais.hr.user_management.service;



import org.ais.hr.user_management.dto.*;
import org.ais.hr.user_management.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface JsHrUserManagementService {

    List<TblModule> getAllModule();

    List<TblMenu> getAllMenuByModuleId(Long moduleId);

    List<TblMenu> getAllMenu();

    List<TblRole> getAllRolebyStatus(String status);

    List<TblRoleRight> getMenuByRoleId(String roleId);

    List<TblRoleRight> getAllRoleRightsbyRoleId(String roleId);

    TblModule saveModule(TblModule tblModule);

    TblMenu saveMenu(TblMenu tblMenu);

    TblRole saveRole(TblRole tblRole);

    TblRoleRight saveRoleRights(TblRoleRight tblRoleRights);

    TblModule updateModule(UpdateModuleReq updateModuleReq, LoggedUserDetail loggedUserDetail);

    TblMenu updateMenu(UpdateMenuRequest updateMenuRequest, LoggedUserDetail loggedUserDetail);

    TblRole updateRole(UpdateRoleRequest updateRoleRequest, LoggedUserDetail loggedUserDetail);

    TblUser saveUser(TblUser tblUser);

    TblRoleRight updateRoleRights(UpdateRoleRightRequest updateRoleRightRequest, LoggedUserDetail loggedUserDetail);

    TblUser updateUser(UpdateUserRequest updateUserRequest, LoggedUserDetail loggedUserDetail);

    TblLocation findLocationById(Long tblLocation);

    TblOrganization findByOrganizationCode(String organizationCode);

    List<TblUser> getAllUsers();

     TblUser getUserById(long id);

    TblRole getRoleByRoleId(long roleId);

    TblLocation getLocationById(long locationId);

    TblAuthAccessToken tokenVerification(BigDecimal userId, String header);

    TblUserActivityLog acticityLogging(TblUserActivityLog tblUserActivityLog);
}
