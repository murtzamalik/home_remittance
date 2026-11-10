package org.ais.hr.fi.controller;

import io.swagger.annotations.Api;
import org.ais.hr.fi.Service.FinInstService;
import org.ais.hr.fi.dto.*;
import org.ais.hr.fi.model.*;
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
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;

@Api(value = "JS HR FI Post Api", description = "POST API For JS HR FI")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/fi")
public class FinInstPostApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(FinInstPostApi.class);

    @Autowired
   private FinInstService finInstService;


    @RequestMapping(value = "/saveFinInst", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveFinInst(@Valid @RequestBody FinIstBankRequest finIstBankRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblFininstBank tblFininstBank = new TblFininstBank();

                LkpCity lkpCity = new LkpCity();
                LkpCountry lkpCountry1 = new LkpCountry();
                TblGlAccount tblGlAccount = new TblGlAccount();
                LkpGlAccountType lkpGlAccountType = new LkpGlAccountType();
              TblParserHead tblParserHead = new TblParserHead();

                lkpCity.setCityId(finIstBankRequest.getLkpCityId());
                lkpCountry1.setCountryId(finIstBankRequest.getLkpCountry1Id());
                lkpGlAccountType.setGlAccountTypeId(finIstBankRequest.getGlLkpGlAccountTypeId());
                tblParserHead.setParserHeadId(finIstBankRequest.getParserHeadId());



                tblFininstBank.setCcEmail(finIstBankRequest.getCcEmail());
                tblFininstBank.setContactPerson(finIstBankRequest.getContactPerson());
                tblFininstBank.setEmail(finIstBankRequest.getEmail());
                tblFininstBank.setFax(finIstBankRequest.getFax());
                tblFininstBank.setFininstBankcode(finIstBankRequest.getFininstBankcode());
                tblFininstBank.setFininstCode(finIstBankRequest.getFininstCode());
                tblFininstBank.setFininstDefault(finIstBankRequest.getFininstDefault());
                tblFininstBank.setFininstDescr(finIstBankRequest.getFininstDescr());
                tblFininstBank.setFininstNature(finIstBankRequest.getFininstNature());
                tblFininstBank.setFininstSbpcode(finIstBankRequest.getFininstSbpcode());
                tblFininstBank.setFininstShort(finIstBankRequest.getFininstShort());
                tblFininstBank.setIbftAccountFormat(finIstBankRequest.getIbftAccountFormat());
                tblFininstBank.setIbftAccountLength(finIstBankRequest.getIbftAccountLength());
                tblFininstBank.setIsActive(finIstBankRequest.getIsActive());
                tblFininstBank.setMobile(finIstBankRequest.getMobile());
                tblFininstBank.setPhone(finIstBankRequest.getPhone());
                tblFininstBank.setPobox(finIstBankRequest.getPobox());
                tblFininstBank.setPostal(finIstBankRequest.getPostal());
                tblFininstBank.setRegAddress1(finIstBankRequest.getRegAddress1());
                tblFininstBank.setRegAddress2(finIstBankRequest.getRegAddress2());
                tblFininstBank.setRegAddress3(finIstBankRequest.getRegAddress3());
                tblFininstBank.setValidateIban(finIstBankRequest.getValidateIban());
                tblFininstBank.setWebsite(finIstBankRequest.getWebsite());


                tblGlAccount.setFcAllowed(finIstBankRequest.getGlFcAllowed());
                tblGlAccount.setGlAccountCode(finIstBankRequest.getGlAccountCode());
                tblGlAccount.setGlAccountDescr(finIstBankRequest.getGlAccountDescr());
                tblGlAccount.setGlAccountShort(finIstBankRequest.getGlAccountShort());
                tblGlAccount.setIsActive(finIstBankRequest.getIsActive());

                tblGlAccount.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

                tblGlAccount.setLkpGlAccountType(lkpGlAccountType);




                tblFininstBank.setTblGlAccount(tblGlAccount);
                tblFininstBank.setLkpCountry1(lkpCountry1);
               tblFininstBank.setLkpCountry2(null);
                tblFininstBank.setParserHeadId(tblParserHead);

                tblFininstBank.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));


                TblFininstBank tblFininstBankresp = finInstService.saveFinInst(tblFininstBank);

                if (tblFininstBankresp != null) {
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 16);
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record(s) Save ", tblFininstBankresp);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Reocrd(s) not Save", null);
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", null);
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

    @RequestMapping(value = "/saveParserHead", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveParserHead(@Valid @RequestBody ParserHeadRequest parserHeadRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblParserHead tblParserHead = finInstService.saveParserHead(parserHeadRequest, loggedUserDetail);


                if (tblParserHead != null) {
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 28);
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Sucessfully ", tblParserHead);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Reocrd(s) not Save", null);
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == ", methodName + " OF CLASS = " + className + " \n\n\n");
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

    @RequestMapping(value = "/saveParserDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveParserDetails(@Valid @RequestBody ParserDetailRequest parserDetailRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblParserDetail tblParserDetail = finInstService.saveParserDetails(parserDetailRequest, loggedUserDetail);


                if (tblParserDetail != null) {
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 30);
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Sucessfully ", tblParserDetail);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Reocrd(s) not Save", null);
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


    @RequestMapping(value = "/saveRemitLimit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveRemitLimit(@Valid @RequestBody RemitModeRequest remitModeRequest, HttpServletRequest request) {

        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblRemitMode tblRemitMode = new TblRemitMode();

                tblRemitMode.setRemitDescr(remitModeRequest.getRemitDescr());
                tblRemitMode.setRemitDefault(remitModeRequest.getRemitDefault());
                tblRemitMode.setRemitCode(remitModeRequest.getRemitCode());
                tblRemitMode.setAmountCapping(remitModeRequest.getAmountCapping());
                tblRemitMode.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                tblRemitMode = finInstService.saveRemitMode(tblRemitMode);


                if (tblRemitMode != null) {
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 32);
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Sucessfully ", tblRemitMode);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Reocrd(s) not Save", null);
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
