package org.ais.hr.fileupload.controller;

import io.swagger.annotations.Api;
import org.ais.hr.fileupload.Service.HrLovService;
import org.ais.hr.fileupload.dto.LoggedUserDetail;
import org.ais.hr.fileupload.dto.LovResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/24/2022
 * Time: 5:24 PM
 * Project : login
 */




@Api(value = "JS HR LOV API", description = "GET NATCA auto import and manual upload")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/lov")
public class LovController extends AbstractApi {



    Logger LOG = LoggerFactory.getLogger(LovController.class);

    @Autowired
    private HrLovService hrLovService;


    @RequestMapping(value = "/lovRemitCompany", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovRemitCompany(HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<LovResponse> lovResponses = hrLovService.lovRemitCompany();

                if (lovResponses != null && lovResponses.size() > 0) {

                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record(s) Found", lovResponses);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.BAD_REQUEST, "No Record Found", null);
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


    @RequestMapping(value = "/lovFinInstBank", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovFinInstBank(HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<LovResponse> lovResponses = hrLovService.lovFinInstBank();

                if (lovResponses != null && lovResponses.size() > 0) {

                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record(s) Found", lovResponses);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.BAD_REQUEST, "No Record Found", null);
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
