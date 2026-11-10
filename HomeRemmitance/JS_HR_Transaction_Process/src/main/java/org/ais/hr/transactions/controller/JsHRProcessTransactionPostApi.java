package org.ais.hr.transactions.controller;

import io.swagger.annotations.Api;
import org.ais.hr.transactions.Service.ProcessTransactionService;
import org.ais.hr.transactions.Service.TransactionService;
import org.ais.hr.transactions.dto.*;
import org.ais.hr.transactions.esb.*;
import org.ais.hr.transactions.model.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/26/2022
 * Time: 1:58 PM
 * Project : login
 */


@Api(value = "JS HR Api Process Transaction Post Api", description = "POST TRANSACTION PROCESS  API For JS HR")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/transaction")
public class JsHRProcessTransactionPostApi extends AbstractApi {


    Logger LOG = LoggerFactory.getLogger(JsHRProcessTransactionPostApi.class);
    @Autowired
    private Environment env;

    @Autowired
    private ProcessTransactionService processTransactionService;
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/getTransactionsAgainstRemitCompanyForProcess", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> getTransactionsAgainstRemitCompanyForProcess(@Valid @RequestBody ProcessTransAgnstRmtCmpny processTransAgnstRmtCmpny, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<TblTransDetail> tblTransDetails = processTransactionService.getTransactionsAgainstRemitCompanyForProcess(processTransAgnstRmtCmpny);

                if (tblTransDetails != null && tblTransDetails.size() > 0) {

                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record(s) Found", tblTransDetails);
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

    @RequestMapping(value = "/processTransactions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> processIBFTTransactions(@Valid @RequestBody ProcessIBFTTransactionRequest processIBFTTransactionRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblTransDetail> tblTransDetails = processTransactionService.getTbLTransDetailsByIds(processIBFTTransactionRequest.getTransDetailIds());
                if (tblTransDetails != null && tblTransDetails.size() > 0) {
                    int successfullTransCount = 0;
                    int unSuccessfullIFTCount = 0;
                    int unSuccessfullIBFTCount = 0;

                    for (TblTransDetail tblTransDetail : tblTransDetails) {
                        TblFininstBank tblFininstBank = tblTransDetail.getTblTransHead().getTblFininstBank();
                        String fromAcc = processTransactionService.getFromAccNumber(tblFininstBank.getFininstBankId());
                        JsTransactionApi jsTransactionApi = new JsTransactionApi();
                        String stan = processTransactionService.getSystemTraceNumber();
                        if (tblTransDetail.getTransType().equalsIgnoreCase("AC")) {
                            /// INTERNAL FUNDS TRANSFER PROCESS


                            // call funds transfer api of js bank
                            // from account will be remmiter account
                            // to account will be from transdetail object
                            // if the api call is successfull
                            // update the status of transdetail to S and status desctiption as well
                            // if api call is uncsuccessfull
                            // update the status of transdetail to FTU and status desciption as well


                            IftTitleFetchResponse iftTitleFetchResponse = callJSBankIFTTitleFetch(fromAcc, tblTransDetail.getBeneficiaryAccountNo(), tblTransDetail.getAmount().toString(), stan,loggedUserDetail);

//

                            if (iftTitleFetchResponse.getResponseCode039().equals("00")) {


                                IftResponse iftResponse = callJSBankIFTTransfer(fromAcc, tblTransDetail.getBeneficiaryAccountNo(), tblTransDetail.getAmount().toString(), stan,loggedUserDetail);


                                if (iftResponse.getResponseCode_039().equals("00")) {

                                    successfullTransCount++;
                                    tblTransDetail.setStatus("S");
                                    tblTransDetail.setStatusDescr("Funds Transfer SuccessFull");
                                    tblTransDetail = transactionService.updateTransactionStatus(tblTransDetail, loggedUserDetail);


                                } else {

                                    unSuccessfullIFTCount++;
                                    tblTransDetail.setStatus("FTU");
                                    tblTransDetail.setStatusDescr("Error On Funds Transfer");
                                    tblTransDetail = transactionService.updateTransactionStatus(tblTransDetail, loggedUserDetail);
                                }


                            } else {
                                unSuccessfullIFTCount++;
                                tblTransDetail.setStatus("FTT");
                                tblTransDetail.setStatusDescr("Error On Funds Transfer Title Fetch");
                                tblTransDetail = transactionService.updateTransactionStatus(tblTransDetail, loggedUserDetail);
                            }


                        } else if (tblTransDetail.getTransType().equalsIgnoreCase("IB")) {


                            IbftTitleFetchResponse ibftTitleFetchResponse =callJSBankIBFTTitleFetch(fromAcc, tblTransDetail.getBeneficiaryAccountNo(), "taimoorali", "4220188556725", "035", stan,loggedUserDetail);

                            if (ibftTitleFetchResponse.getResponseCode_039().equals("000")) {
                                IbftPaymentResponse ibftPaymentResponse = callJSBankIBFTPayment(fromAcc, tblTransDetail.getBeneficiaryAccountNo(), tblTransDetail.getAmount().toString(), "taimoorali", "4220188556725", "SAMEED AHMED", "627197", stan, "Summit Bank Limited", "Summit Bank Limited", "0307",loggedUserDetail);

                                if (ibftPaymentResponse.getResponseCode_039().equals("000")) {

                                    successfullTransCount++;
                                    tblTransDetail.setStatus("S");
                                    tblTransDetail.setStatusDescr("Funds Transfer SuccessFull");
                                    tblTransDetail = transactionService.updateTransactionStatus(tblTransDetail, loggedUserDetail);
                                } else {

                                    unSuccessfullIBFTCount++;
                                    tblTransDetail.setTransBt("Y");
                                    tblTransDetail.setStatus("BT");
                                    tblTransDetail.setStatusDescr("Error On IBFT Payment");
                                    tblTransDetail = transactionService.updateTransactionStatus(tblTransDetail, loggedUserDetail);
                                }

                            } else {

                                unSuccessfullIBFTCount++;
                                tblTransDetail.setStatus("BT");
                                tblTransDetail.setTransBt("Y");
                                tblTransDetail.setStatusDescr("Error On IBFT Title Fetch");
                                tblTransDetail = transactionService.updateTransactionStatus(tblTransDetail, loggedUserDetail);
                            }

                        }
                    }



                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 10);

                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    ProcessTransactionsResponse processTransactionsResponse = new ProcessTransactionsResponse();
                    processTransactionsResponse.setTotalTransactions(String.valueOf(tblTransDetails.size()));
                    processTransactionsResponse.setSucessfulTransactions(String.valueOf(successfullTransCount));
                    processTransactionsResponse.setUnsucessfullIFT(String.valueOf(unSuccessfullIFTCount));
                    processTransactionsResponse.setUnSucessfullIBFT(String.valueOf(unSuccessfullIBFTCount));
                    return getResponseFormat(HttpStatus.OK, "Transaction(s) Posted SuuccessFully", processTransactionsResponse);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Processing ", null);

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

    @RequestMapping(value = "/iftTitleFetch", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, Object>> iftTitleFetch(@Valid @RequestParam("accountNumber") String accountNumber, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                String stan = processTransactionService.getSystemTraceNumber();

                 IftTitleFetchResponse iftTitleFetchResponse = callJSBankIFTTitleFetch(accountNumber, accountNumber, "100".toString(), stan,loggedUserDetail);


                if (iftTitleFetchResponse.getResponseCode039().equals("00")) {
                    IFTTitleFtchRsp iftTitleFtchRsp = new IFTTitleFtchRsp();
                    iftTitleFtchRsp.setAccountNumber(iftTitleFetchResponse.getAccountIdentification1102());
                    iftTitleFtchRsp.setAccountTitle(iftTitleFetchResponse.getReservedPrivate120());
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, iftTitleFetchResponse.getMessage(), iftTitleFtchRsp);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, iftTitleFetchResponse.getMessage(), null);
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

    @RequestMapping(value = "/parkTransactionsForAmendments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> parkTransactionsForAmendments(@Valid @RequestBody ParkTransForAmndmntRequest parkTransForAmndmntRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                processTransactionService.parkTransactionsForAmendments(parkTransForAmndmntRequest, loggedUserDetail.getUserId());
                //Logging Activity Record
                TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 8);


                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record(s) Parked For Amendments", null);
//                } else {
//                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
//                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Parking Records", null);
//                }


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

    @RequestMapping(value = "/amendmentTransaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> amendmentTransaction(@Valid @RequestBody AmendmentTransactionRequest amendmentTransactionRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblTransDetail tblTransDetail = processTransactionService.amendmentTransaction(amendmentTransactionRequest, loggedUserDetail.getUserId());

                if (tblTransDetail != null && tblTransDetail.getTransDetailId() > 0) {
                    HashMap<String, String> bifurcateAndValidate = transactionService.callBifurcateAndValidate(tblTransDetail, loggedUserDetail.getUserId());
                    if (bifurcateAndValidate.get("status").equals("1")) {
                        //Logging Activity Record
                        TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 1);
                        LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                        return getResponseFormat(HttpStatus.OK, "Record Updated SuccessFully", null);
                    } else {
                        LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Finding Duplicates \n " + bifurcateAndValidate.get("statusDescr"), null);
                    }
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Parking Records", null);
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

    @RequestMapping(value = "/finInstWiseFileName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> finInstWiseFileName(@Valid @RequestBody FinInstWiseFileNameRequest finInstWiseFileNameRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<FinInstWiseFileNameResponse> response = processTransactionService.finInstWiseFileName(finInstWiseFileNameRequest);

                if (response != null && response.size() > 0) {

                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Batch Inquiry Sucessful", response);
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

    @RequestMapping(value = "/getAllBtTransactions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> getAllBtTransactions(@Valid @RequestBody BtTransactionRequest btTransactionRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<BtTransactionResponse> response = transactionService.getAllBtTransactions(btTransactionRequest);

                if (response != null && response.size() > 0) {

                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Bt Transactions Sucessful", response);
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

    private String writeDataIntoTxtFileFromDB(TblMt102Head tblMt102Head) throws IOException {

        TblGlobalConfig tblGlobalConfig = processTransactionService.getFilePathByKey("Mt102.file.location");
        String basePath = tblGlobalConfig.getKeyValue();
        File file = new File(basePath + tblMt102Head.getFileReferenceNo() + ".txt");
        List<TblMt102Detail> tblMt102Details = processTransactionService.getAllTransactionForMT102File(tblMt102Head.getMt102HeadId());
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date currentDate = new Date();
        String date = dateFormat.format(currentDate);
        int totalAmount=0;
        for (TblMt102Detail tblMt102Detail : tblMt102Details) {



            String contents =
                    "20:      Transaction Reference Number" + System.lineSeparator() +
                            "         " + tblMt102Head.getFileReferenceNo() + System.lineSeparator() +
                            "23:      Bank Operation Code" + System.lineSeparator() +
                            "         CREDIT" + System.lineSeparator() +
                            "26T:     Transaction Type Code" + System.lineSeparator() +
                            "         005" + System.lineSeparator() +
                            "71A:     Details of Charges" + System.lineSeparator() +
                            "         SHA" + System.lineSeparator() +
                            "21:      Related Reference" + System.lineSeparator() +
                            "         " + tblMt102Detail.getTransactionReference() + System.lineSeparator() +
                            "32B:     Transaction Amount" + System.lineSeparator() +
                            "         PKR" + tblMt102Detail.getAmount() + System.lineSeparator() +
                            "50K:     Ordering Customer" + System.lineSeparator() +
                            "         /" + tblMt102Detail.getRemitterName() + System.lineSeparator() +
                            "         " + tblMt102Detail.getBeneficiaryBankName() + System.lineSeparator() +
                            "52A:     Ordering Institution" + System.lineSeparator() +
                            "         /020211000720011 " + System.lineSeparator() +
                            "         JSBLPKKA" + System.lineSeparator() +
                            "57A:     Account With Institution - BIC" + System.lineSeparator() +
                            "         /D/" + tblMt102Detail.getBeneficaryBankAccount() + System.lineSeparator() +
                            "         " + tblMt102Detail.getBicCode() + System.lineSeparator() +
                            "59:      Beneficiary Customer" + System.lineSeparator() +
                            "         /" + tblMt102Detail.getBeneficiaryAccountNo() + System.lineSeparator() +
                            "         " + tblMt102Detail.getBeneficiaryName() + System.lineSeparator() +
                            "         " + tblMt102Detail.getBeneficiaryBranchCode() + System.lineSeparator() +
                            "70:      Remittance Information" + System.lineSeparator() +
                            "         HOMEREMITTANCE-9471" + System.lineSeparator() +
                            "77B:     Regulatory Reporting" + System.lineSeparator() +
                            "         SBP" + System.lineSeparator();

            totalAmount= (int) (totalAmount+tblMt102Detail.getAmount().intValue());
            printWriter.println(contents);
        }
        String content =
                "32A:     Value Date, Currency Code, Amt" + System.lineSeparator() +
                "         Date      : " + date + System.lineSeparator() +
                "         Currency  : PKR" + System.lineSeparator() +
                "         Amount    : " + "#" + totalAmount + ",#" + System.lineSeparator() +
                "72:      Sender to Receiver Information" + System.lineSeparator() +
                "         /CODTYPTR/005" + System.lineSeparator() + System.lineSeparator() + System.lineSeparator();
        printWriter.println(content);
        printWriter.close();
        return file.getName();
    }

    @RequestMapping(value = "/generateMT102FileForBT", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> generateMT102FileForBT(@Valid @RequestBody ParkTransForAmndmntRequest parkTransForAmndmntRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                Long batchNumber = processTransactionService.getBatchNumberFrom();
                List<TblTransDetail> response = transactionService.getTransactionDetailsByIds(parkTransForAmndmntRequest.getTransDetailIds());
                List<TblMt102Temp> tblMt102Temps = new ArrayList<>();
                for (TblTransDetail transDetail : response) {

                    String stan = processTransactionService.getSystemTraceNumber();


                    IftResponse iftResponse =callJSBankIFTTransfer("0000486547", "0000670111", "100", stan,loggedUserDetail);


                    if (iftResponse.getResponseCode_039().equals("00")) {

                        transDetail.setStatus("S");
                        transDetail.setStatusDescr("MT102 Generated Sucessfully");
                        transactionService.updateTransactionStatus(transDetail, loggedUserDetail);

                        TblMt102Temp tblMt102Temp = new TblMt102Temp();
                        tblMt102Temp.setStatus("S");
                        tblMt102Temp.setBatchNo(BigDecimal.valueOf(batchNumber));
                        tblMt102Temp.setTblTransDetail(transDetail);
                        tblMt102Temp.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                        tblMt102Temps.add(tblMt102Temp);
                    } else {

                        transDetail.setStatus("BTE");
                        transDetail.setStatusDescr(iftResponse.getResponseCode_039() + "  Error At IFT ");
                        transactionService.updateTransactionStatus(transDetail, loggedUserDetail);
                    }

                }

                List<TblMt102Temp> mt102TempList = processTransactionService.saveMt102Temp(tblMt102Temps);


                if (mt102TempList != null && tblMt102Temps.size() > 0) {

                    HashMap<String, Object> mt102HeadPks = processTransactionService.populateMt102(batchNumber, loggedUserDetail.getUserId());
                    List<String> headpks = (List<String>) mt102HeadPks.get("mt102HeadIds");
                    String[] status1 = (String[]) mt102HeadPks.get("status");


                    String status = status1[0];

                    if (status.equals("1")) {

//                        List<String>  headpks = (List<String>) mt102HeadPks.get("mt102HeadIds");
                        if (headpks != null) {
                            List<TblMt102Head> tblMt102Heads = processTransactionService.getTbmlMt102Head(headpks);
                            List<String> fileName = new ArrayList<>();
                            for (TblMt102Head tblMt102Head : tblMt102Heads) {

                                fileName.add(writeDataIntoTxtFileFromDB(tblMt102Head));

                            }
                            //Logging Activity Record
                            TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 7);
                            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                            return getResponseFormat(HttpStatus.OK, "MT102 Files Generated SuccessFully", fileName);
                        } else {
                            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Transaction Rejected !!", null);
                        }


                    } else {
                        LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Saving Records", null);
                    }

                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Saving Records", null);
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

    @RequestMapping(value = "/parkTransactionsForBt", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> parkTransactionsForBt(@Valid @RequestBody ProcessIBFTTransactionRequest processIBFTTransactionRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                List<TblTransDetail> tblTransDetails = processTransactionService.getTbLTransDetailsByTransType(processIBFTTransactionRequest.getTransDetailIds(), "IB");
                if (tblTransDetails != null && tblTransDetails.size() > 0) {

                    for (TblTransDetail tblTransDetail : tblTransDetails) {

                        tblTransDetail.setTransBt("Y");
                        tblTransDetail.setStatus("BT");
                        tblTransDetail.setStatusDescr("Manually BT Marked");
                        tblTransDetail = transactionService.updateTransactionStatus(tblTransDetail, loggedUserDetail);


                    }
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 9);

                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Transaction(s) Marked BT", null);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Processing ", null);
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


    @RequestMapping(value = "/batchInquiry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> batchInquiry(@Valid @RequestBody BatchInquiryRequest batchInquiryRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<BatchInquiryResponse> response = processTransactionService.batchInquiry(batchInquiryRequest);

                if (response != null && response.size() > 0) {
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 4);

                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Batch Inquiry Sucessful", response);
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

    @RequestMapping(value = "/transactionsInquiryStatusWise", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> transactionsInquiryStatusWise(@Valid @RequestBody TransactionsInqStatusWiseRequest transactionsInqStatusWiseRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<TblTransDetail> response = processTransactionService.transactionsInquiryStatusWise(transactionsInqStatusWiseRequest);

                if (response != null && response.size() > 0) {
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 5);

                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Batch Inquiry Sucessful", response);
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

    @RequestMapping(value = "/singleTransactionInquiry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> singleTransactionInquiry(@Valid @RequestBody SingleTransactionInquiryRequest singleTransactionInquiryRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<TblTransDetail> transDetail = transactionService.singleTransactionInquiry(singleTransactionInquiryRequest);

                if (transDetail != null && transDetail.size()>0) {
                    //Logging Activity Record
                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 6);

                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Single Transaction Inquiry Sucessful", transDetail);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "No Record Found", null);
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

    @RequestMapping(value = "/mt102Inquiry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> mt102Inquiry(@RequestBody Mt102InquiryRequest mt102InquiryRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {

            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblGlobalConfig tblGlobalConfig = processTransactionService.getFilePathByKey("Mt102.file.location");
                List<Mt102InquiryResponse> mt102InquiryResponses=transactionService.mt102Inquiry(mt102InquiryRequest);
                String basePath = tblGlobalConfig.getKeyValue();
                for(Mt102InquiryResponse mt102InquiryResponse :mt102InquiryResponses) {
                    String base64=convertFiletoBase64(basePath,mt102InquiryResponse.getFileRefNo());
                    mt102InquiryResponse.setFile(base64);
                }
                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Records Founded",mt102InquiryResponses );


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
