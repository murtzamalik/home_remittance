package org.ais.hr.fi.Service.impl;

import org.ais.hr.fi.Repo.*;
import org.ais.hr.fi.Service.FiLovService;
import org.ais.hr.fi.dto.LovResponse;
import org.ais.hr.fi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 4/1/2022
 * Time: 12:07 PM
 * Project : fi
 */



@Service
@Transactional(rollbackOn = Exception.class)
public class FiLovServiceImpl implements FiLovService {


    @Autowired
    private LkpCityRepo lkpCityRepo;

    @Autowired
    private LkpCountryRepo lkpCountryRepo;

    @Autowired
    private LkpGlAccountTypeRepo lkpGlAccountTypeRepo;

    @Autowired
    private TblOrganizationRepo tblOrganizationRepo;

    @Autowired
    private TblTransFieldRepo tblTransFieldRepo;

    @Autowired
    private TblParserHeadRepo tblParserHeadRepo;

    @Autowired
    private LkpFininstNatureRepo lkpFininstNatureRepo;

    @Autowired
    private TblRemitModeRepo tblRemitModeRepo;



    @Override
    public List<LovResponse> lovCountry() {
        List<LkpCountry> results = lkpCountryRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpCountry result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getCountryId()));
                lovResponse.setCode(result.getCountryCode());
                lovResponse.setDescription(result.getCountryDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovCity(Long countryId) {
        List<LkpCity> results = lkpCityRepo.findByLkpCountryCountryId(countryId);
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpCity result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getCityId()));
                lovResponse.setCode(result.getCityCode());
                lovResponse.setDescription(result.getCityDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovOrganization() {
        List<TblOrganization> results = tblOrganizationRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblOrganization result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getOrganizationId()));
                lovResponse.setCode(result.getOrganizationCode());
                lovResponse.setDescription(result.getOrganizationDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovGlAccType() {
        List<LkpGlAccountType> results = lkpGlAccountTypeRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpGlAccountType result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getGlAccountTypeId()));
                lovResponse.setCode(result.getGlAccountTypeCode());
                lovResponse.setDescription(result.getGlAccountTypeDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovTransFields() {
        List<TblTransField> results = tblTransFieldRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblTransField result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getTransFieldsId()));
                lovResponse.setCode(result.getName());
                lovResponse.setDescription(result.getName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovParsers() {
        List<TblParserHead> results = tblParserHeadRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblParserHead result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getParserHeadId()));
                lovResponse.setCode(result.getCode());
                lovResponse.setDescription(result.getDescription());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovNature() {
        List<LkpFininstNature> results = lkpFininstNatureRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpFininstNature result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getCode()));
                lovResponse.setCode(result.getCode());
                lovResponse.setDescription(result.getDescription());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovRemitMode() {
        List<TblRemitMode> results = tblRemitModeRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblRemitMode result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getRemitModeId()));
                lovResponse.setCode(result.getRemitCode());
                lovResponse.setDescription(result.getRemitDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }
}
