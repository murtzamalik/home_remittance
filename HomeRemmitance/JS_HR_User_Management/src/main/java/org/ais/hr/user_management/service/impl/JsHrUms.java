package org.ais.hr.user_management.service.impl;

import org.ais.hr.user_management.Repo.*;
import org.ais.hr.user_management.dto.*;
import org.ais.hr.user_management.model.*;
import org.ais.hr.user_management.service.JsHrUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional(rollbackOn = Exception.class)
public class JsHrUms implements JsHrUserManagementService {
    @Autowired
    private TblModuleRepo tblModuleRepo;
    @Autowired
    private TblMenuRepo tblMenuRepo;

    @Autowired
    private TblRoleRepo tblRoleRepo;

    @Autowired
    private TblRoleRightRepo tblRoleRightsRepo;

    @Autowired
    private TblUserRepo tblUserRepo;

    @Autowired
    private TblLocationRepo tblLocationRepo;

    @Autowired
    private TblOrganizationRepo tblOrganizationRepo;

    @Autowired
    private TblAuthAccessTokenRepo authAccessTokenRepo;
    @Autowired
    private TblUserActivityLogRepo tblUserActivityLogRepo;

    @Override
    public List<TblModule> getAllModule() {
        return tblModuleRepo.findAll();
    }

    @Override
    public List<TblMenu> getAllMenuByModuleId(Long moduleId) {
        return tblMenuRepo.getMenuByModuleId(moduleId);
    }

    @Override
    public List<TblMenu> getAllMenu() {
        return tblMenuRepo.findAll();
    }

    @Override
    public List<TblRole> getAllRolebyStatus(String status) {
        return tblRoleRepo.findAll();
    }

    @Override
    public List<TblRoleRight> getMenuByRoleId(String roleId) {
        return tblRoleRightsRepo.findAll();
    }

    @Override
    public List<TblRoleRight> getAllRoleRightsbyRoleId(String roleId) {
        return tblRoleRightsRepo.findByTblRoleRoleId(Long.valueOf(roleId));
    }

    @Override
    public TblModule saveModule(TblModule tblModule) {
        return tblModuleRepo.saveAndFlush(tblModule);
    }

    @Override
    public TblMenu saveMenu(TblMenu tblMenu) {
        return tblMenuRepo.save(tblMenu);
    }

    @Override
    public TblRole saveRole(TblRole tblRole) {
        return tblRoleRepo.save(tblRole);
    }

    @Override
    public TblRoleRight saveRoleRights(TblRoleRight tblRoleRights) {
        return tblRoleRightsRepo.save(tblRoleRights);
    }

    @Override
    public TblModule updateModule(UpdateModuleReq updateModuleReq, LoggedUserDetail loggedUserDetail) {

        TblModule tblModule = tblModuleRepo.findById(updateModuleReq.getModuleId()).orElse(null);

        tblModule.setModuleDescr(updateModuleReq.getModuleDescr());
        tblModule.setLastupdatedate(new Date());
        tblModule.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblModule.setUpdateindex(tblModule.getUpdateindex() != null ? new BigDecimal(tblModule.getUpdateindex().longValue() + 1) : new BigDecimal(1));

        return tblModuleRepo.save(tblModule);
    }

    @Override
    public TblRole updateRole(UpdateRoleRequest updateRoleRequest, LoggedUserDetail loggedUserDetail) {

        TblRole tblRole = tblRoleRepo.findById(updateRoleRequest.getRoleId()).orElse(null);

        tblRole.setRoleDescr(updateRoleRequest.getRoleDescr());
        tblRole.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblRole.setLastupdatedate(new Date());
        tblRole.setUpdateindex(tblRole.getUpdateindex() != null ? new BigDecimal(tblRole.getUpdateindex().longValue() + 1) : new BigDecimal(1));

        return tblRoleRepo.save(tblRole);
    }

    @Override
    public TblMenu updateMenu(UpdateMenuRequest updateMenuRequest, LoggedUserDetail loggedUserDetail) {

        TblMenu tblMenu = tblMenuRepo.findById(updateMenuRequest.getMenuId()).orElse(null);

        tblMenu.setMenuDescription(updateMenuRequest.getMenuDescription());
        tblMenu.setIconName(updateMenuRequest.getIconName());
        tblMenu.setIconPath(updateMenuRequest.getIconPath());
        tblMenu.setMenuCode(updateMenuRequest.getMenuCode());
        tblMenu.setMenuPath(updateMenuRequest.getMenuPath());
        tblMenu.setMenuType(updateMenuRequest.getMenuType());
        tblMenu.setLastupdatedate(new Date());
        tblMenu.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblMenu.setUpdateindex(tblMenu.getUpdateindex() != null ? new BigDecimal(tblMenu.getUpdateindex().longValue() + 1) : new BigDecimal(1));

        return tblMenuRepo.save(tblMenu);

    }

    @Override
    public TblUser saveUser(TblUser tblUser) {
        return tblUserRepo.saveAndFlush(tblUser);
    }

    @Override
    public TblRoleRight updateRoleRights(UpdateRoleRightRequest updateRoleRightRequest, LoggedUserDetail loggedUserDetail) {
        TblRoleRight tblRoleRight1 = tblRoleRightsRepo.findById(updateRoleRightRequest.getRoleRightsId()).orElse(null);
        if (tblRoleRight1 != null) {
            tblRoleRight1.setAuthorizeAllowed(updateRoleRightRequest.getAuthorizeAllowed());
            tblRoleRight1.setHideYn(updateRoleRightRequest.getHideYn());
            tblRoleRight1.setInsertAllowed(updateRoleRightRequest.getInsertAllowed());
            tblRoleRight1.setUpdateAllowed(updateRoleRightRequest.getUpdateAllowed());
            tblRoleRight1.setSelectAllowed(updateRoleRightRequest.getSelectAllowed());
            tblRoleRight1.setLastupdatedate(new Date());
            tblRoleRight1.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblRoleRight1.setUpdateindex(tblRoleRight1.getUpdateindex() != null ? new BigDecimal(tblRoleRight1.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            tblRoleRight1 = tblRoleRightsRepo.saveAndFlush(tblRoleRight1);
        }
        return tblRoleRight1;
    }

    @Override
    public TblLocation findLocationById(Long tblLocation) {
        return tblLocationRepo.findById(tblLocation).orElse(null);
    }

    @Override
    public TblOrganization findByOrganizationCode(String organizationCode) {
        return tblOrganizationRepo.findByOrganizationCode(organizationCode);
    }

    @Override
    public TblUser updateUser(UpdateUserRequest updateUserRequest, LoggedUserDetail loggedUserDetail) {
        TblUser user = tblUserRepo.findById(updateUserRequest.getUserId()).orElse(null);
        if (user != null) {
            TblOrganization tblOrganization = tblOrganizationRepo.findByOrganizationCode("001");

            user.setTblOrganization(tblOrganization);
            user.setUsercode(updateUserRequest.getUsercode());
            user.setUsername(updateUserRequest.getUsername());
            user.setEmpCode(updateUserRequest.getEmpCode());
            user.setEmployeeNo(updateUserRequest.getEmployeeNo());
            user.setIsActive(updateUserRequest.getIsActive());
            user.setRemarks(updateUserRequest.getRemarks());
            user.setStartDate(new Date());
            user.setPasswordExpired("N");
            user.setEmail(updateUserRequest.getEmail());
            user.setMobileno(updateUserRequest.getMobileno());
            user.setInternalExternal(updateUserRequest.getInternalExternal());


            user.setCanVerify("Y");
            user.setCanApprove("Y");
            user.setApproveOwnTrans("Y");
            user.setApproveVerTrans("Y");

            TblLocation tblLocation = new TblLocation();
            tblLocation.setLocationId(updateUserRequest.getLocationId());
            user.setTblLocation(tblLocation);
            TblRole tblRole=new TblRole();
            tblRole.setRoleId(updateUserRequest.getRoleId());
            user.setTblRole(tblRole);

            user.setLastupdatedate(new Date());
            user.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            user.setUpdateindex(user.getUpdateindex() != null ? new BigDecimal(user.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            user = tblUserRepo.saveAndFlush(user);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public List<TblUser> getAllUsers() {
        List<Object> users = tblUserRepo.getAllUsers();
        List<TblUser> tblUsers = new ArrayList<>();

        if (users != null && users.size() > 0) {

            for (Object record : users) {

                TblUser tblUser = new TblUser();
                Object[] row = (Object[]) record;

                    if (((BigDecimal) row[0]) != null) {
                        tblUser.setUserId(((BigDecimal) row[0]).longValue());
                        tblUser.setUsercode((String) row[1]);
                        tblUser.setUsername((String) row[2]);
                        tblUser.setEmpCode((String) row[4]);
                        tblUser.setEmployeeNo((String) row[5]);
                        tblUser.setKeyValue((String) row[6]);
                        tblUser.setSignature((String) row[8]);
                        tblUser.setIsActive((String) row[9]);
                        tblUser.setLastLogin((Date) row[17]);
                        tblUser.setStartDate((Date) row[18]);
                        tblUser.setEndDate((Date) row[19]);
                        tblUser.setRemarks((String) row[20]);
                        tblUser.setAllowConcon((String) row[21]);
                        if ((BigDecimal) row[22] != null) {
                            tblUser.setConConnection((BigDecimal) row[22]);
                        }
                        if ((BigDecimal) row[23] != null) {
                            tblUser.setPassExpDays((BigDecimal) row[23]);
                        }
                        tblUser.setVerifyOwnTrans((String) row[24]);
                        if ((BigDecimal) row[25] != null) {
                            tblUser.setInactiveDays((BigDecimal) row[25]);
                        }
                        tblUser.setApproveLmttran((String) row[26]);

                        TblLocation tblLocation = new TblLocation();
                        TblRole tblRole = new TblRole();
                        if (((BigDecimal) row[29]) != null) {
                            tblLocation.setLocationId(((BigDecimal) row[29]).longValue());
                            tblLocation.setLocationDescr((String) row[64]);
                            tblLocation.setLocationCode((String) row[65]);
                            tblUser.setTblLocation(tblLocation);
                        } else {
                            tblUser.setTblLocation(null);
                        }

                        tblUser.setPasswordExpired((String) row[35]);
                        tblUser.setPassExpDate((Date) row[36]);
                        tblUser.setPassGracePeriod((BigDecimal) row[37]);
                        tblUser.setStatusCode((String) row[38]);
                        tblUser.setCanVerify((String) row[39]);
                        tblUser.setCanApprove((String) row[40]);
                        tblUser.setApproveOwnTrans((String) row[41]);
                        tblUser.setApproveVerTrans((String) row[42]);
                        if (((BigDecimal) row[43]) != null) {
                            tblUser.setCreateuser((BigDecimal) row[43]);
                        }
                        tblUser.setCreatedate((Date) row[44]);
                        if (((BigDecimal) row[45]) != null) {
                            tblUser.setLastupdateuser((BigDecimal) row[45]);
                        }
                        tblUser.setLastupdatedate((Date) row[46]);
                        if (((BigDecimal) row[47]) != null) {
                            tblUser.setUpdateindex((BigDecimal) row[47]);
                        }
                        tblUser.setEmail((String) row[53]);
                        tblUser.setMobileno((String) row[56]);
                        tblUser.setInternalExternal((String) row[58]);
                        tblUser.setLdapName((String) row[59]);
                        tblUser.setLdapEmail((String) row[60]);
                        tblUser.setLdapEmpcode((String) row[61]);
                        if (((BigDecimal) row[62]) != null) {
                            tblRole.setRoleId(((BigDecimal) row[62]).longValue());
                            tblRole.setRoleDescr((String) row[63]);
                            tblUser.setTblRole(tblRole);
                        } else {
                            tblUser.setTblRole(null);
                        }
                    } else {
                        System.out.println(((BigDecimal) row[0]));
                    }


                    tblUsers.add(tblUser);



            }
            if (tblUsers != null) {
                return tblUsers;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public TblUser getUserById(long id) {
        return tblUserRepo.findById(id).orElse(null);
    }

    @Override
    public TblRole getRoleByRoleId(long roleId) {
        return tblRoleRepo.findById(roleId).orElse(null);
    }

    @Override
    public TblLocation getLocationById(long locationId) {
        return tblLocationRepo.findById(locationId).orElse(null);
    }

    @Override
    public TblAuthAccessToken tokenVerification(BigDecimal userId, String header) {
        return authAccessTokenRepo.findByUserIdAndAccessToken(userId,header);
    }

    @Override
    public TblUserActivityLog acticityLogging(TblUserActivityLog tblUserActivityLog) {
        return tblUserActivityLogRepo.saveAndFlush(tblUserActivityLog);
    }
}
