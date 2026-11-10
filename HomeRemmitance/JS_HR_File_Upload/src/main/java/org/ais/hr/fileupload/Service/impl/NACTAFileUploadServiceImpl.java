package org.ais.hr.fileupload.Service.impl;

import org.ais.hr.fileupload.Service.NACTAFileUploadService;
import org.ais.hr.fileupload.dto.NACTAAutoImportResponse;
import org.ais.hr.fileupload.model.TblAmlfileLog;
import org.ais.hr.fileupload.model.TblGlobalConfig;
import org.ais.hr.fileupload.model.TblNactaList;
import org.ais.hr.fileupload.repo.TblAmlfileLogRepo;
import org.ais.hr.fileupload.repo.TblGlobalConfigRepo;
import org.ais.hr.fileupload.repo.TblNactaListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/21/2022
 * Time: 11:45 AM
 * Project : login
 */


@Service
@Transactional(rollbackOn = Exception.class)
public class NACTAFileUploadServiceImpl implements NACTAFileUploadService {

    @Autowired
    private TblNactaListRepo tblNactaListRepo;

    @Autowired
    private TblAmlfileLogRepo tblAmlfileLogRepo;

    @Autowired
    private TblGlobalConfigRepo tblGlobalConfigRepo;



    @Override
    public List<TblNactaList> saveApiResponseToDB(List<NACTAAutoImportResponse> nactaAutoImportResponses, long userId) {
        List<TblNactaList> tblNactaLists = new ArrayList<>();
        TblNactaList tblNactaList = null;

        for (NACTAAutoImportResponse nactaAutoImportResponse : nactaAutoImportResponses) {
            tblNactaList = new TblNactaList();

            tblNactaList.setCnic(nactaAutoImportResponse.getCnic());
            tblNactaList.setDistrict(nactaAutoImportResponse.getDistrict());
            tblNactaList.setFatherName(nactaAutoImportResponse.getFatherName());
            tblNactaList.setName(nactaAutoImportResponse.getName());
            tblNactaList.setProvince(nactaAutoImportResponse.getProvince());
            tblNactaList.setProcessName("NACTA");
            tblNactaList.setProcessType("A");
            tblNactaList.setCreateuser(BigDecimal.valueOf(userId));

            tblNactaLists.add(tblNactaList);

        }
        int delete = tblNactaListRepo.deleteAllPreviousRecordAgainstProcessNameAndType("NACTA", "A");
        tblNactaLists = tblNactaListRepo.saveAllAndFlush(tblNactaLists);
        if (tblNactaLists != null && tblNactaLists.size() > 0) {
            return tblNactaLists;
        } else {

            return null;
        }


    }

    @Override
    public TblAmlfileLog saveAmlFileLog(TblAmlfileLog tblAmlfileLog) {
        return tblAmlfileLogRepo.saveAndFlush(tblAmlfileLog);
    }

    @Override
    public List<TblNactaList> saveNactaListFromManualUploadFromExcel(List<TblNactaList> tblNactaLists) {
        int delete = tblNactaListRepo.deleteAllPreviousRecordAgainstProcessNameAndType("NACTA", "M");

        tblNactaLists = tblNactaListRepo.saveAllAndFlush(tblNactaLists);
        if (tblNactaLists != null && tblNactaLists.size() > 0) {
            return tblNactaLists;
        } else {

            return null;
        }

    }

    @Override
    public List<TblNactaList> getNACTAList() {
        return tblNactaListRepo.findByProcessName("NACTA");
    }

    @Override
    public TblGlobalConfig getFilePathByKey(String key) {
        return tblGlobalConfigRepo.findByKeyName(key);
    }
}
