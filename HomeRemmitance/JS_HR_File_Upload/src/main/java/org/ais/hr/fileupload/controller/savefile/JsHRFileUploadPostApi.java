package org.ais.hr.fileupload.controller.savefile;

import io.swagger.annotations.Api;
import org.ais.hr.fileupload.Service.RemitCompanyFileUploadService;
import org.ais.hr.fileupload.Service.TransactionService;
import org.ais.hr.fileupload.controller.AbstractApi;
import org.ais.hr.fileupload.dto.CustomFile;
import org.ais.hr.fileupload.dto.LoggedUserDetail;
import org.ais.hr.fileupload.dto.RemitingCompanyFileUploadRequest;
import org.ais.hr.fileupload.dto.TransactionUpload;
import org.ais.hr.fileupload.model.*;
import org.ais.hr.fileupload.util.ReadFile;
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
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/18/2022
 * Time: 11:54 AM
 * Project : jcash
 */


@Api(value = "JS HR Api Login Post Api", description = "POST LOGIN  API For JS HR")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/remitFileUpload")
public class JsHRFileUploadPostApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsHRFileUploadPostApi.class);
    @Autowired
    private Environment env;

    @Autowired
    private RemitCompanyFileUploadService remitCompanyFileUploadService;

    @Autowired
    private TransactionService transactionService;


    @RequestMapping(value = "/manualUploadFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HashMap<String, Object>> manualUploadFile(@RequestParam("fininstBankId") long fininstBankId, @RequestParam("processDate") String processDate, @RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            LOG.info("point 1");
            // convertMultiPartToFile(multipartFile);
            LOG.info("point 2");
            if (loggedUserDetail != null) {

                String filename = multipartFile.getOriginalFilename();
                filename = filename.replaceFirst("[.][^.]+$", "");

                TblTransHead transHead = remitCompanyFileUploadService.findByName(filename);
                if(transHead != null){
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Duplicate File Name Found", null);

                }


                TblFininstBank tblFininstBank = remitCompanyFileUploadService.getById(fininstBankId);

                if (tblFininstBank != null) {
                    List<TblParserDetail> tblParserDetail = remitCompanyFileUploadService.getByParserHeadId(tblFininstBank.getTblParserHead().getParserHeadId());
                    if (tblParserDetail != null) {
                        LOG.info("point 3");
                        InputStream targetStream = multipartFile.getInputStream();
                        LOG.info("point 4");
                        HashMap<String, List<String>> excelColumns = readDataFromExcelFile(targetStream);

                        if (excelColumns != null && excelColumns.size() > 0) {
                            List<String> authenticColumnsNames = new ArrayList<>();
                            List<String> authenticColumnsTypes = new ArrayList<>();
                            List<String> columnsNames = excelColumns.get("columnName");
//                            List<String> columnsTypes=excelColumns.get("columnType");
                            if (tblParserDetail.size() == columnsNames.size()) {
                                for (int i = 0; i < columnsNames.size(); i++) {

                                    if (tblParserDetail.get(i).getRefFieldName().equalsIgnoreCase(columnsNames.get(i))) {

                                        authenticColumnsNames.add(columnsNames.get(i));

                                    }

                                }
                                if (authenticColumnsNames.size() != columnsNames.size()) {
                                    HashMap<String, List<String>> errorMap = new HashMap<>();
                                    errorMap.put("ColumnName", authenticColumnsNames);
                                    LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
                                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Parser Configuration Error Column Name ", errorMap);

                                }



                                TblTransHead tblTransHead = new TblTransHead();


                                tblTransHead.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                                tblTransHead.setFileName(filename);
                                tblTransHead.setTblFininstBank(tblFininstBank);
                                tblTransHead.setRecordCount(BigDecimal.valueOf(getNumberOfRecordsInExcelFile(multipartFile.getInputStream()) - 1));
                                tblTransHead.setStatus("N");


                                tblTransHead = remitCompanyFileUploadService.saveManualTransactions(tblTransHead, multipartFile.getInputStream(), loggedUserDetail.getUserId(), tblFininstBank.getTblParserHead().getParserHeadId());


                                if (tblTransHead != null && tblTransHead.getErrorsColumns().size() > 0) {
                                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Parsing While :: Total Errors = "+tblTransHead.getErrorsColumns().size() +"   "+tblTransHead.getErrorsColumns() ,null);

                                }

                                if (tblTransHead != null && tblTransHead.getTransHeadId() > 0) {

                                     //Logging Activity Record
                                    TblUserActivityLog tblUserActivityLog=activityLogging(loggedUserDetail,(long) 11);


                                    HashMap<String, String> chkDuplicateTransResponse = remitCompanyFileUploadService.callProcChkDuplicateTransa(tblTransHead.getTransHeadId(), loggedUserDetail.getUserId());

                                    if (chkDuplicateTransResponse.get("status").equals("1")) {

                                        HashMap<String, String> transBifurcateResponse = remitCompanyFileUploadService.callProcTransBifurcate(tblTransHead.getTransHeadId(), loggedUserDetail.getUserId());

                                        if (transBifurcateResponse.get("status").equals("1")) {


                                            HashMap<String, String> transValidateResponse = remitCompanyFileUploadService.callProcTransValidate(tblTransHead.getTransHeadId(), loggedUserDetail.getUserId());
                                            if (transValidateResponse.get("status").equals("1")) {


                                                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                                                return getResponseFormat(HttpStatus.OK, "File Uploaded \n File BiFurcated \n File AML/NACTA Validated ", tblTransHead.getTblTransDetails().size());

                                            } else {

                                                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                                                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While AML/Validate \n" + transValidateResponse.get("statusDescr"), null);
                                            }
                                        } else {
                                            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Bifurcating transactions \n " + transBifurcateResponse.get("statusDescr"), null);
                                        }

                                    } else {
                                        LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Finding Duplicates \n " + chkDuplicateTransResponse.get("statusDescr"), null);
                                    }

                                } else {
                                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Uploading File", null);
                                }


                            } else {
                                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Parser Configuration and File Upload Columns Are Different..!!", "No Parser Found Against Company");

                            }
                        } else {
                            LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Reading Excel", "Error While Reading Excel");
                        }
                    } else {
                        LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Parser Found Against Company", "No Parser Found Against Company");
                    }
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Financial Institute Found Against Id", "No Financial Institute Found Against Id");
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


    @RequestMapping(value = "/getRemitingCompaniesFilesFromDirectory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> readFilesandFolders(@Valid @RequestBody RemitingCompanyFileUploadRequest remitingCompanyFileUploadRequest, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

//                TblRemitter tblRemitter = remitCompanyFileUploadService.getRemittCompanyById(remitingCompanyFileUploadRequest.getRemitCompId());

                ReadFile fileHelper = new ReadFile();
                List<CustomFile> res = fileHelper.FileReader("C:\\home_remit_files\\REMIT_COMPANY_FILES\\ALICO");

                if (res != null && res.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "", res);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Check path", null);
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


    @GetMapping(value = "/autoFileUpload")
    public ResponseEntity<HashMap<String, Object>> autoFileUpload() throws FileNotFoundException {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        //Creating a File object for directory
        TblGlobalConfig tblGlobalConfig = remitCompanyFileUploadService.getFilePathByKey("auto.file.upload");
        String basepath = tblGlobalConfig.getKeyValue();

        File directoryPath = new File(basepath);
        //List of all files and directories
        String contents[] = directoryPath.list();
        List<String> foldersList = new ArrayList<>();
        for (int i = 0; i < contents.length; i++) {
            foldersList.add(contents[i]);
        }
        List<File> filesList = new ArrayList<>();
        for (String foldername : foldersList) {
            File directoryPath1 = new File(basepath + foldername);
            File files[] = directoryPath1.listFiles();
            for (File file : files) {
                String filename = file.getName();
                filename = filename.replaceFirst("[.][^.]+$", "");
                TblFininstBank tblFininstBank = remitCompanyFileUploadService.findByFinistCode(filename);

                if (tblFininstBank != null) {
                    List<TblParserDetail> tblParserDetail = remitCompanyFileUploadService.getByParserHeadId(tblFininstBank.getTblParserHead().getParserHeadId());
                    if (tblParserDetail != null) {
                        InputStream targetStream = new DataInputStream(new FileInputStream(file));
                        HashMap<String, List<String>> excelColumns = readDataFromExcelFile(targetStream);

                        if (excelColumns != null && excelColumns.size() > 0) {
                            List<String> authenticColumnsNames = new ArrayList<>();
                            List<String> authenticColumnsTypes = new ArrayList<>();
                            List<String> columnsNames = excelColumns.get("columnName");
//                            List<String> columnsTypes=excelColumns.get("columnType");
                            if (tblParserDetail.size() == columnsNames.size()) {
                                for (int i = 0; i < columnsNames.size(); i++) {

                                    if (tblParserDetail.get(i).getRefFieldName().equalsIgnoreCase(columnsNames.get(i))) {

                                        authenticColumnsNames.add(columnsNames.get(i));

                                    }

                                }
                                if (authenticColumnsNames.size() != columnsNames.size()) {
                                    HashMap<String, List<String>> errorMap = new HashMap<>();
                                    errorMap.put("ColumnName", authenticColumnsNames);

                                } else {

                                    TblTransHead tblTransHead = remitCompanyFileUploadService.findByName(filename);
                                    if (tblTransHead == null) {

                                        tblTransHead = new TblTransHead();
                                        tblTransHead.setCreateuser(BigDecimal.valueOf(1));
                                        tblTransHead.setFileName(filename);
                                        tblTransHead.setTblFininstBank(tblFininstBank);
                                        tblTransHead.setRecordCount(BigDecimal.valueOf(getNumberOfRecordsInExcelFile(new FileInputStream(file))));
                                        tblTransHead.setStatus("N");
                                        tblTransHead = remitCompanyFileUploadService.saveManualTransactions(tblTransHead, new FileInputStream(file), 1, tblFininstBank.getTblParserHead().getParserHeadId());
                                        if (tblTransHead != null) {
                                            filesList.add(file);
                                            HashMap<String, String> chkDuplicateTransResponse = remitCompanyFileUploadService.callProcChkDuplicateTransa(tblTransHead.getTransHeadId(), 1);

                                            if (chkDuplicateTransResponse.get("status").equals("1")) {

                                                HashMap<String, String> transBifurcateResponse = remitCompanyFileUploadService.callProcTransBifurcate(tblTransHead.getTransHeadId(), 1);

                                                if (transBifurcateResponse.get("status").equals("1")) {


                                                    HashMap<String, String> transValidateResponse = remitCompanyFileUploadService.callProcTransValidate(tblTransHead.getTransHeadId(), 1);
                                                    if (transValidateResponse.get("status").equals("1")) {


                                                        LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                                                        return getResponseFormat(HttpStatus.OK, "File Uploaded \n File BiFurcated \n File AML/NACTA Validated ", filesList);

                                                    } else {

                                                        LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                                                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While AML/Validate \n" + transValidateResponse.get("statusDescr"), null);
                                                    }
                                                } else {
                                                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                                                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Bifurcating transactions \n " + transBifurcateResponse.get("statusDescr"), null);
                                                }

                                            } else {
                                                LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                                                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Bifurcating transactions \n " + chkDuplicateTransResponse.get("statusDescr"), null);
                                            }

                                        }
                                    } else {
                                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "File Already Exists\n ", null);
                                    }

                                }
                            } else {
                                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Columns Not Matched\n ", null);
                            }
                        } else {
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Excel File Empty\n ", null);
                        }
                    } else {
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Unable to Parse\n ", null);
                    }
                } else {
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Fin Inst Founded !!\n ", null);
                }
            }


        }

        return null;

    }

    @RequestMapping(value = "/pushApiForTransactionUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> transactionUpload(@Valid @RequestBody TransactionUpload transactionUpload, HttpServletRequest request) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();

        try {
            LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblTransHead tblTransHead = new TblTransHead();


                TblRemitter tblRemitter = new TblRemitter();
                tblRemitter.setRemitterId(Long.valueOf(transactionUpload.getRemitId()));

                tblTransHead.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                tblTransHead.setApiType("PUSH");
                tblTransHead.setRecordCount(BigDecimal.valueOf(transactionUpload.getTransactionList().size()));
                tblTransHead.setStatus("A");
                tblTransHead.setTblRemitter(tblRemitter);
                tblTransHead = remitCompanyFileUploadService.savePUSHTransaction(tblTransHead, transactionUpload, BigDecimal.valueOf(loggedUserDetail.getUserId()));

                if (tblTransHead != null && tblTransHead.getTransHeadId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Transactions Uploaded", tblTransHead);
                } else {
                    LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Uploading Transactions", null);
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
