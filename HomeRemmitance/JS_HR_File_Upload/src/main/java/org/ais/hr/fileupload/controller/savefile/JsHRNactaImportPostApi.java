package org.ais.hr.fileupload.controller.savefile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.ais.hr.fileupload.Service.NACTAFileUploadService;
import org.ais.hr.fileupload.controller.AbstractApi;
import org.ais.hr.fileupload.dto.LoggedUserDetail;
import org.ais.hr.fileupload.dto.NACTAAutoImportResponse;
import org.ais.hr.fileupload.dto.NACTAListFromDirectoryRequest;
import org.ais.hr.fileupload.model.TblAmlfileLog;
import org.ais.hr.fileupload.model.TblGlobalConfig;
import org.ais.hr.fileupload.model.TblNactaList;
import org.ais.hr.fileupload.model.TblUserActivityLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/18/2022
 * Time: 12:03 PM
 * Project : login
 */


@Api(value = "JS HR NACTA Import/Export API", description = "POST NATCA auto import and manual upload")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nacta")
class JsHRNactaImportPostApi extends AbstractApi {


    Logger LOG = LoggerFactory.getLogger(JsHRNactaImportPostApi.class);
    @Autowired
    private Environment env;

    @Autowired
    private NACTAFileUploadService nactaFileUploadService;

    @RequestMapping(value = "/autoImportNACTAList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> autoImportNACTAList(@Valid @RequestBody String s, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                String url = env.getProperty("nacta.auto.import.url", "https://nfs.punjab.gov.pk/Home/GetJosn?filter=");
                String resp = getResponseFromGetAPI(url);
                if (resp != null) {

                    ObjectMapper mapper = new ObjectMapper();
                    List<NACTAAutoImportResponse> nactaAutoImportResponse = mapper.readValue(resp, new TypeReference<List<NACTAAutoImportResponse>>() {
                    });
                    if (nactaAutoImportResponse != null && nactaAutoImportResponse.size() > 0) {

                        TblAmlfileLog tblAmlfileLog = new TblAmlfileLog();

                        tblAmlfileLog.setProcessName("NACTA");
                        tblAmlfileLog.setProcessType("A");
                        tblAmlfileLog.setRecordCount(BigDecimal.valueOf(nactaAutoImportResponse.size()));
                        tblAmlfileLog.setUrl(url);
                        tblAmlfileLog.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));


                        tblAmlfileLog = nactaFileUploadService.saveAmlFileLog(tblAmlfileLog);

                        List<TblNactaList> tblNactaLists = nactaFileUploadService.saveApiResponseToDB(nactaAutoImportResponse, loggedUserDetail.getUserId());
                        // Logging must not fail the import (missing ACTIVITY / LOGIN_HISTORY FKs in local)
                        try {
                            activityLogging(loggedUserDetail, (long) 2);
                        } catch (Exception logEx) {
                            LOG.warn("NACTA auto import activity log skipped: {}", logEx.getMessage());
                        }
                        if (tblNactaLists != null && tblNactaLists.size() > 0) {

                            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                            return getResponseFormat(HttpStatus.OK, "Auto Import NACTA List Uploaded SuccessFully", null);
                        } else {
                            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Updating DB", null);
                        }

                    } else {
                        LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Response From API", null);
                    }

                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Reading Data From API", null);
                }


            } else {
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            String msg = e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName();
            if (e.getCause() != null && e.getCause().getMessage() != null) {
                msg = msg + " | cause: " + e.getCause().getMessage();
            }
            LOG.error("\n CLASS == " + className + " \n METHOD == " + methodName + "  ERROR ----- " + msg, e);
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + msg, null);
        }
    }


    @RequestMapping(value = "/uploadNACTAListFromDirectory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> uploadNACTAListFromDirectory(@Valid @RequestBody NACTAListFromDirectoryRequest nactaListFromDirectoryRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                TblGlobalConfig tblGlobalConfig=nactaFileUploadService.getFilePathByKey("nacta.fileupload.path");
                String basepath = tblGlobalConfig.getKeyValue();
                String path = basepath+nactaListFromDirectoryRequest.getFileName();

                File file = new File(path);

                if (file.exists()) {

                    TblAmlfileLog tblAmlfileLog = new TblAmlfileLog();

                    tblAmlfileLog.setProcessName("NACTA");
                    tblAmlfileLog.setProcessType("M");
                    tblAmlfileLog.setFileName(nactaListFromDirectoryRequest.getFileName());
                    tblAmlfileLog.setFileFormat(".xlsx");
                    tblAmlfileLog.setRecordCount(BigDecimal.valueOf(getNumberOfRecordsInExcelFile(new FileInputStream(file))));
                    tblAmlfileLog.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));


                    tblAmlfileLog = nactaFileUploadService.saveAmlFileLog(tblAmlfileLog);

                    List<TblNactaList> tblNactaLists = readColumnDataFromNACTAExcel(new FileInputStream(file), loggedUserDetail.getUserId());
                    if (tblNactaLists != null && tblNactaLists.size() > 0) {


                        tblNactaLists = nactaFileUploadService.saveNactaListFromManualUploadFromExcel(tblNactaLists);

                        if (tblNactaLists.get(0).getNactaListId() > 0) {

                            //Logging Activity Record
                            TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 13);
                            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                            return getResponseFormat(HttpStatus.OK, "Manual Import NACTA List Uploaded SuccessFully", null);
                        } else {
                            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Inserting Records In DB", null);
                        }

                    } else {
                        LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Extracting Data From Excel", null);
                    }

                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Reading From Location    "+ path, null);
                }

            } else {
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == " + className + " \n METHOD == " + methodName + "  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }
    }


}
