package org.ais.hr.user_management.service.impl;

import antlr.ASTNULLType;
import org.ais.hr.user_management.Repo.TblLocationRepo;
import org.ais.hr.user_management.Repo.TblMenuRepo;
import org.ais.hr.user_management.Repo.TblModuleRepo;
import org.ais.hr.user_management.Repo.TblRoleRepo;
import org.ais.hr.user_management.dto.LovResponse;
import org.ais.hr.user_management.model.TblLocation;
import org.ais.hr.user_management.model.TblMenu;
import org.ais.hr.user_management.model.TblModule;
import org.ais.hr.user_management.model.TblRole;
import org.ais.hr.user_management.service.JsHrUserManagementLovService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JsHrLovImpl implements JsHrUserManagementLovService {
    @Autowired
    TblRoleRepo tblRoleRepo;

    @Autowired
    TblMenuRepo tblMenuRepo;

    @Autowired
    TblModuleRepo tblModuleRepo;

    @Autowired
    TblLocationRepo tblLocationRepo;


    @Override
    public List<LovResponse> lovRoleRights() {

        List<TblRole> results = tblRoleRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblRole result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getRoleId()));
                lovResponse.setCode(result.getRoleDescr());
                lovResponse.setDescription(result.getCheckerComments());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovMenu() {

//        List<TblMenu> results = tblMenuRepo.findByStatus("Y");

        List<TblMenu> results = tblMenuRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblMenu result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getMenuId()));
                lovResponse.setCode(result.getMenuCode());
                lovResponse.setDescription(result.getMenuDescription());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }


    @Override
    public List<LovResponse> lovModule() {
        //        List<TblAreacode> results = tblAreacodeRepo.findByStatus("Y");
        List<TblModule> results = tblModuleRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblModule result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getModuleId()));
                lovResponse.setCode(result.getModuleDescr());
                lovResponse.setDescription(result.getModuleDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovRole() {
        List<TblRole> results = tblRoleRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblRole result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getRoleId()));
                lovResponse.setCode(result.getRoleDescr());
                lovResponse.setDescription(result.getRoleDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovLocation() {
        List<TblLocation> results = tblLocationRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblLocation result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getLocationId()));
                lovResponse.setCode(result.getLocationCode());
                lovResponse.setDescription(result.getLocationDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }
}
