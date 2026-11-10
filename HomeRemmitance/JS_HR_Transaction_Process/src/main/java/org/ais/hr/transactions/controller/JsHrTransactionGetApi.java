package org.ais.hr.transactions.controller;

import io.swagger.annotations.Api;
import org.ais.hr.transactions.Service.ProcessTransactionService;
import org.ais.hr.transactions.Service.TransactionService;
import org.ais.hr.transactions.dto.LoggedUserDetail;
import org.ais.hr.transactions.model.TblGlobalConfig;
import org.ais.hr.transactions.model.TblTransDetail;
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
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/27/2022
 * Time: 4:59 PM
 * Project : login
 */



@Api(value = "JS HR Api Transaction Get Api", description = "GET TRANSACTION  API For JS HR")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/transaction")
public class JsHrTransactionGetApi extends AbstractApi {



    Logger LOG = LoggerFactory.getLogger(JsHrTransactionGetApi.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ProcessTransactionService processTransactionService;


    @RequestMapping(value = "/getAllAmendmentsTransactions", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllAmendmentsTransactions(HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblTransDetail> tblTransDetails  = transactionService.getAllAmendmentsTransactions();

                if (tblTransDetails != null && tblTransDetails.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record(s) Found ", tblTransDetails);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Reocrd(s) Found", null);
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Check Login Credentials", null);
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
