package org.ais.hr.fileupload.Service.impl;

import org.ais.hr.fileupload.Service.HrLovService;
import org.ais.hr.fileupload.dto.LovResponse;
import org.ais.hr.fileupload.model.TblFininstBank;
import org.ais.hr.fileupload.model.TblRemitter;
import org.ais.hr.fileupload.repo.TblFininstBankRepo;
import org.ais.hr.fileupload.repo.TblRemitterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/24/2022
 * Time: 5:58 PM
 * Project : login
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class HrLovServiceImpl implements HrLovService {


    @Autowired
    private TblRemitterRepo tblRemitterRepo;
    @Autowired
    private TblFininstBankRepo tblFininstBankRepo;


    @Override
    public List<LovResponse> lovRemitCompany() {
        List<TblRemitter> results = tblRemitterRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblRemitter result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getRemitterId()));

                lovResponse.setCode(result.getRemitterName());
                lovResponse.setDescription(result.getRemitterName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovFinInstBank() {
        List<TblFininstBank> results = tblFininstBankRepo.findAllByFininstNature("E");
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblFininstBank result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getFininstBankId()));

                lovResponse.setCode(result.getFininstCode());
                lovResponse.setDescription(result.getFininstDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }
}
