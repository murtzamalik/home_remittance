package org.ais.hr.fileupload.controller.savefile;

import io.swagger.annotations.Api;
import org.ais.hr.fileupload.Service.OFACFileUploadService;
import org.ais.hr.fileupload.controller.AbstractApi;
import org.ais.hr.fileupload.dto.LoggedUserDetail;
import org.ais.hr.fileupload.model.TblAmlfileLog;
import org.ais.hr.fileupload.model.TblNactaList;
import org.ais.hr.fileupload.model.TblUserActivityLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/21/2022
 * Time: 3:00 PM
 * Project : login
 */


@Api(value = "JS HR NACTA Import/Export API", description = "POST NATCA auto import and manual upload")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/ofac")
public class JsHROFACUploadPostApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsHROFACUploadPostApi.class);


    @Autowired
    private OFACFileUploadService ofacFileUploadService;


    @RequestMapping(value = "/autoImportOFACList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> autoImportOFACList(@Valid @RequestBody String s, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                String url = "https://www.treasury.gov/ofac/downloads/sdn.xml";
                String resp = getResponseFromGetAPI(url);

                String[] suit = resp.split("</sdnEntry>");

                List<String> names = new ArrayList<>();
                for (String word : suit) {

                    String firstName = "";
                    try {
                        firstName = word.split("<firstName>")[1].split("</firstName>")[0];
                    } catch (Exception e) {
                    }
                    String lastName = "";
                    try {
                        lastName = word.split("<lastName>")[1].split("</lastName>")[0];
                    } catch (Exception e) {

                    }
                    String fullName = firstName + " " + lastName;
                    names.add(fullName);

                }

                if (names != null && names.size() > 0) {


                    TblAmlfileLog tblAmlfileLog = new TblAmlfileLog();

                    tblAmlfileLog.setProcessName("OFAC");
                    tblAmlfileLog.setProcessType("A");
                    tblAmlfileLog.setRecordCount(BigDecimal.valueOf(names.size()));
                    tblAmlfileLog.setUrl(url);
                    tblAmlfileLog.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));


                        tblAmlfileLog = ofacFileUploadService.saveAmlFileLog(tblAmlfileLog);

                    List<TblNactaList> tblNactaLists = new ArrayList<>();
                    TblNactaList tblNactaList = null;
                    for (String name : names) {
                        tblNactaList = new TblNactaList();

                        tblNactaList.setName(name);
                        tblNactaList.setProcessName("OFAC");
                        tblNactaList.setProcessType("A");
                        tblNactaList.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                        tblNactaLists.add(tblNactaList);
                    }
                    tblNactaLists = ofacFileUploadService.saveOFACList(tblNactaLists);
                    try {
                        activityLogging(loggedUserDetail, (long) 3);
                    } catch (Exception logEx) {
                        LOG.warn("OFAC auto import activity log skipped: {}", logEx.getMessage());
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
            LOG.error("\n CLASS == " + className + " \n METHOD == " + methodName + "  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }
    }


    @RequestMapping(value = "/getOFACList", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getNACTAList(HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblNactaList> tblNactaLists = ofacFileUploadService.getOFACList();

                if (tblNactaLists != null && tblNactaLists.size() > 0) {

                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record(s) Found", tblNactaLists);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", null);
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
