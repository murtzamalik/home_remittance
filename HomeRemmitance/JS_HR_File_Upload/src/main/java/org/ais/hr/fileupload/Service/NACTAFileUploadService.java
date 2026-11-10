package org.ais.hr.fileupload.Service;

import org.ais.hr.fileupload.dto.NACTAAutoImportResponse;
import org.ais.hr.fileupload.model.TblAmlfileLog;
import org.ais.hr.fileupload.model.TblGlobalConfig;
import org.ais.hr.fileupload.model.TblNactaList;

import java.util.List;

public interface NACTAFileUploadService {


    List<TblNactaList> saveApiResponseToDB(List<NACTAAutoImportResponse> nactaAutoImportResponse, long userId);


    TblAmlfileLog saveAmlFileLog(TblAmlfileLog tblAmlfileLog);

    List<TblNactaList> saveNactaListFromManualUploadFromExcel(List<TblNactaList> tblNactaLists);


    List<TblNactaList> getNACTAList();

    TblGlobalConfig getFilePathByKey(String key);

}
