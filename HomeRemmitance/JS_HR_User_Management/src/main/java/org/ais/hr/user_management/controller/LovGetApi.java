package org.ais.hr.user_management.controller;

import io.swagger.annotations.Api;
import org.ais.hr.user_management.dto.LovResponse;
import org.ais.hr.user_management.service.JsHrUserManagementLovService;
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

@Api(value = "JS HR User Management Api Lov Get Api", description = "Get Lov Api For JS HR User Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/lov")
public class LovGetApi extends AbstractApi {

    @Autowired
    JsHrUserManagementLovService jsHrUserManagementLovService;
    @RequestMapping(value = "/lovRoleRights", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovRoleRights(HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();


        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);

            List<LovResponse> lovResponses = jsHrUserManagementLovService.lovRoleRights();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovMenu", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovMenu(HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();


        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);

            List<LovResponse> lovResponses = jsHrUserManagementLovService.lovMenu();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }


    @RequestMapping(value = "/lovModule", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovModule(HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();


        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);

            List<LovResponse> lovResponses = jsHrUserManagementLovService.lovModule();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovRole", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovRole(HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();


        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            List<LovResponse> lovResponses = jsHrUserManagementLovService.lovRole();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovLocation", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovLocation(HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();


        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            List<LovResponse> lovResponses = jsHrUserManagementLovService.lovLocation();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS ==  " + className + "  \n METHOD == " + methodName + "  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS =  " + className + "  \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }
}
