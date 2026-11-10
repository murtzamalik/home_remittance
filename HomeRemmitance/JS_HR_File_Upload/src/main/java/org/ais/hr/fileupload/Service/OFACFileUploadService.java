package org.ais.hr.fileupload.Service;

import org.ais.hr.fileupload.model.TblAmlfileLog;
import org.ais.hr.fileupload.model.TblNactaList;

import java.util.List;

public interface OFACFileUploadService {
    List<TblNactaList> getOFACList();

    List<TblNactaList> saveOFACList(List<TblNactaList> tblNactaLists);

    TblAmlfileLog saveAmlFileLog(TblAmlfileLog tblAmlfileLog);
}
