package org.ais.hr.fileupload.Service.impl;

import org.ais.hr.fileupload.Service.OFACFileUploadService;
import org.ais.hr.fileupload.model.TblAmlfileLog;
import org.ais.hr.fileupload.model.TblNactaList;
import org.ais.hr.fileupload.repo.TblAmlfileLogRepo;
import org.ais.hr.fileupload.repo.TblNactaListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/24/2022
 * Time: 4:07 PM
 * Project : login
 */


@Service
@Transactional(rollbackOn = Exception.class)
public class OFACFileUploadServiceImpl implements OFACFileUploadService {

    @Autowired
    private TblNactaListRepo tblNactaListRepo;


    @Autowired
    private TblAmlfileLogRepo tblAmlfileLogRepo;

    @Override
    public List<TblNactaList> getOFACList() {
        return tblNactaListRepo.findByProcessName("OFAC");
    }


    @Override
    public List<TblNactaList> saveOFACList(List<TblNactaList> tblNactaLists) {
        int delete = tblNactaListRepo.deleteAllPreviousRecordAgainstProcessNameAndType("OFAC", "A");

        return tblNactaListRepo.saveAllAndFlush(tblNactaLists);

    }

    @Override
    public TblAmlfileLog saveAmlFileLog(TblAmlfileLog tblAmlfileLog) {
        return tblAmlfileLogRepo.saveAndFlush(tblAmlfileLog);
    }
}
