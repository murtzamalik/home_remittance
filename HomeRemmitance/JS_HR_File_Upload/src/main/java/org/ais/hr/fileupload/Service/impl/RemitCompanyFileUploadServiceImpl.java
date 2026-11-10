package org.ais.hr.fileupload.Service.impl;

import org.ais.hr.fileupload.Service.RemitCompanyFileUploadService;
import org.ais.hr.fileupload.controller.AbstractApi;
import org.ais.hr.fileupload.dto.Transaction;
import org.ais.hr.fileupload.dto.TransactionUpload;
import org.ais.hr.fileupload.model.*;
import org.ais.hr.fileupload.repo.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/23/2022
 * Time: 3:31 PM
 * Project : login
 */


@Service
@Transactional(rollbackOn = Exception.class)
public class RemitCompanyFileUploadServiceImpl extends AbstractApi implements RemitCompanyFileUploadService {

    @PersistenceContext
    @Autowired
    EntityManager em;

    @Autowired
    private TblTransHeadRepo tblTransHeadRepo;

    @Autowired
    private TblRemitterRepo tblRemitterRepo;

    @Autowired
    private TblTransDetailRepo tblTransDetailRepo;

    @Autowired
    private TblFininstBankRepo tblFininstBankRepo;

    @Autowired
    private TblParserDetailRepo tblParserDetailRepo;

    @Autowired
    private TblParserHeadRepo tblParserHeadRepo;

    @Autowired
    private TblGlobalConfigRepo tblGlobalConfigRepo;

    @Override
    public TblTransHead saveTranshead(TblTransHead tblTransHead) {
        return tblTransHeadRepo.saveAndFlush(tblTransHead);
    }


    @Override
    public TblRemitter getRemittCompanyById(long remitCompId) {
        return tblRemitterRepo.findById(remitCompId).orElse(null);
    }


    @Override
    public TblTransHead saveManualTransactions(TblTransHead tblTransHead, InputStream fileInputStream, long userId, long parserHeadId) {


        HashMap<String, Object> filevalidationresp = readColumnDataFromRemitCompanyExcel(fileInputStream, userId, tblTransHead, parserHeadId);
        List<TblTransDetail> tblTransDetails = new ArrayList<>();
        List<String> errorColumns = new ArrayList<>();
        errorColumns = (List<String>) filevalidationresp.get("errorColumns");
        if (errorColumns.size() > 0) {
            TblTransHead tblTransHead1 = new TblTransHead();
            tblTransHead1.setErrorsColumns((List<String>) errorColumns);
            return tblTransHead1;
        } else {
                tblTransHead = tblTransHeadRepo.saveAndFlush(tblTransHead);


            if (tblTransHead != null && tblTransHead.getTransHeadId() > 0) {
                tblTransDetails = (List<TblTransDetail>) filevalidationresp.get("tblTransDetail");

                tblTransDetails = tblTransDetailRepo.saveAllAndFlush(tblTransDetails);

                if (tblTransDetails != null && tblTransDetails.size() > 0) {
                    tblTransHead.setTblTransDetails(tblTransDetails);
                    return tblTransHead;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }


    }

    @Override
    public TblTransHead savePUSHTransaction(TblTransHead tblTransHead, TransactionUpload transactionUpload, BigDecimal loggedUserId) {
        tblTransHead = tblTransHeadRepo.saveAndFlush(tblTransHead);
        if (tblTransHead != null && tblTransHead.getTransHeadId() > 0) {

            TblTransDetail tblTransDetail = new TblTransDetail();
            List<TblTransDetail> tblTransDetails = new ArrayList<>();
            for (Transaction transaction : transactionUpload.getTransactionList()) {
                tblTransDetail.setBeneficiaryAccountTitle(transaction.getBeneficiaryAccountTitle());

                tblTransDetail.setBeneficiaryName(transaction.getBeneficiaryName());
                tblTransDetail.setBeneficiaryCnic(transaction.getBeneficiaryCnic());
                tblTransDetail.setBeneficiaryAccountNo(transaction.getBeneficiaryAccountNo());
                tblTransDetail.setTransactionReference(transaction.getTransactionReference());
                tblTransDetail.setAmount(transaction.getAmount());
                tblTransDetail.setBeneficiaryBranchCode(transaction.getBeneficiaryBranchCode());
                tblTransDetail.setBeneficiaryOtherContact(transaction.getBeneficiaryOtherContact());
                tblTransDetail.setBeneficiaryBankName(transaction.getBeneficiaryBankName());
                tblTransDetail.setBeneficiaryBranchAdd1(transaction.getBeneficiaryBranchAdd1());
                tblTransDetail.setBeneficiaryBranchAdd2(transaction.getBeneficiaryBranchAdd2());
                tblTransDetail.setRemitterName(transaction.getRemitterName());
                tblTransDetail.setStatus("N");
                tblTransDetail.setTblTransHead(tblTransHead);
                tblTransDetail.setCreateuser(loggedUserId);

                tblTransDetails.add(tblTransDetail);
            }
            tblTransDetails = tblTransDetailRepo.saveAllAndFlush(tblTransDetails);
            if (tblTransDetails != null && tblTransDetails.size() > 0) {

                return tblTransHead;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }


    @Override
    public HashMap<String, String> callProcChkDuplicateTransa(long transHeadId, long userId) {
//        PROCEDURE PROC_CHK_DUPLICATE_TRANS
//        (
//        P_TRANS_HEAD_ID IN NUMBER ,
//        P_USER_ID IN NUMBER ,
//        P_STATUS OUT NUMBER ,
//        P_STATUSDESCR OUT VARCHAR2
//)
        HashMap<String, String> response = new HashMap<>();

        try {
            Session session = em.unwrap(Session.class);
            final int[] status = new int[1];
            final String[] statusDescr = new String[1];

            session.doWork(new Work() {
                public void execute(Connection connection) throws SQLException {
                    CallableStatement call = connection.prepareCall("{call PROC_CHK_DUPLICATE_TRANS(?,?,?,?) }");

                    call.setLong(1, transHeadId);
                    call.setLong(2, userId);
                    call.registerOutParameter(3, Types.INTEGER);
                    call.registerOutParameter(4, Types.VARCHAR);

                    call.execute();

                    response.put("status", String.valueOf(call.getInt(3)));
                    response.put("statusDescr", String.valueOf(call.getString(4)));
                }
            });

            return response;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            response.put("status", "99");
            response.put("statusDescr", e.getLocalizedMessage());
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("status", "99");
            response.put("statusDescr", e.getLocalizedMessage());
            return response;
        }

    }

    public HashMap<String, String> callProcTransBifurcate(long transHeadId, long userId) {
//        PROCEDURE PROC_TRANS_BIFURCATE
//        (
//                P_TRANS_HEAD_ID IN NUMBER,
//                P_USER_ID IN NUMBER,
//                P_STATUS OUT NUMBER,
//                P_STATUSDESCR OUT VARCHAR2)

        HashMap<String, String> response = new HashMap<>();

        try {
            Session session = em.unwrap(Session.class);
            final int[] status = new int[1];
            final String[] statusDescr = new String[1];

            session.doWork(new Work() {
                public void execute(Connection connection) throws SQLException {
                    CallableStatement call = connection.prepareCall("{call PROC_TRANS_BIFURCATE(?,?,?,?) }");

                    call.setLong(1, transHeadId);
                    call.setLong(2, userId);
                    call.registerOutParameter(3, Types.INTEGER);
                    call.registerOutParameter(4, Types.VARCHAR);

                    call.execute();

                    response.put("status", String.valueOf(call.getInt(3)));
                    response.put("statusDescr", String.valueOf(call.getString(4)));
                }
            });

            return response;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            response.put("status", "99");
            response.put("statusDescr", e.getLocalizedMessage());
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("status", "99");
            response.put("statusDescr", e.getLocalizedMessage());
            return response;
        }


    }

    @Override
    public HashMap<String, String> callProcTransValidate(long transHeadId, long userId) {
        //        PROCEDURE PROC_TRANS_VALIDATE
//        (
//                P_TRANS_HEAD_ID IN NUMBER,
//                P_USER_ID IN NUMBER,
//                P_STATUS OUT NUMBER,
//                P_STATUSDESCR OUT VARCHAR2)

        HashMap<String, String> response = new HashMap<>();

        try {
            Session session = em.unwrap(Session.class);
            final int[] status = new int[1];
            final String[] statusDescr = new String[1];

            session.doWork(new Work() {
                public void execute(Connection connection) throws SQLException {
                    CallableStatement call = connection.prepareCall("{call PROC_TRANS_VALIDATE(?,?,?,?) }");

                    call.setLong(1, transHeadId);
                    call.setLong(2, userId);
                    call.registerOutParameter(3, Types.INTEGER);
                    call.registerOutParameter(4, Types.VARCHAR);

                    call.execute();

                    response.put("status", String.valueOf(call.getInt(3)));
                    response.put("statusDescr", String.valueOf(call.getString(4)));
                }
            });

            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("status", "99");
            response.put("statusDescr", e.getLocalizedMessage());
            return response;
        }
    }

    @Override
    public TblFininstBank getById(long fininstBankId) {
        return tblFininstBankRepo.findById(fininstBankId).orElse(null);
    }

    @Override
    public List<TblParserDetail> getByParserHeadId(long parserHeadId) {

        List<TblParserDetail> tblParserDetails = tblParserDetailRepo.findByTblParserHeadParserHeadIdOrderBySequenceAsc(parserHeadId);
        if (tblParserDetails != null) {
            return tblParserDetails;
        } else {
            return null;
        }
    }

    @Override
    public TblParserDetail getSeqNumFromColumnName(String parserHeadId, String columnName) {
        return tblParserDetailRepo.getSeqNumFromColumnName(parserHeadId, columnName);
    }


    private String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

    public HashMap<String, Object> readColumnDataFromRemitCompanyExcel(InputStream myInputStream, long loggedUserId, TblTransHead tblTransHead, long parserHeadId) {
        try {
            HashMap<String, Object> fileValidation = new HashMap<>();
            List<String> errorsColumns = new ArrayList<>();

            XSSFWorkbook myWorkbook = new XSSFWorkbook(myInputStream);
            // Referencing the Sheet inside the excel file using it's Name
            XSSFSheet mySheet = myWorkbook.getSheetAt(0);

            List<TblTransDetail> tblTransDetails = new ArrayList<>();

            for (int index = 1; index < mySheet.getPhysicalNumberOfRows(); index++) {

                XSSFRow row = mySheet.getRow(index);
                TblTransDetail tblTransDetail = new TblTransDetail();
                int totalCell = row.getLastCellNum();

                TblParserDetail tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "BENEFICIARY_NAME");
                if(tblParserDetail != null) {
                    String nValue = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (nValue != null && !nValue.isEmpty()) {
                            tblTransDetail.setBeneficiaryName(nValue);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setBeneficiaryName(nValue);
                    }
                }


                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "BENEFICIARY_CNIC");
                if(tblParserDetail != null) {
                    String cValue = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (cValue != null && !cValue.isEmpty()) {
                            tblTransDetail.setBeneficiaryCnic(cValue);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setBeneficiaryCnic(cValue);
                    }
                }

                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "BENEFICIARY_ACCOUNT_NO");
                if(tblParserDetail != null) {
                    String aValue = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (aValue != null && !aValue.isEmpty()) {
                            tblTransDetail.setBeneficiaryAccountNo(aValue);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setBeneficiaryAccountNo(aValue);
                    }
                }

                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "TRANSACTION_REFERENCE");
                if(tblParserDetail != null) {
                    String trValue = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (trValue != null && !trValue.isEmpty()) {
                            tblTransDetail.setTransactionReference(trValue);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setTransactionReference(trValue);
                    }
                }


                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "AMOUNT");
                if(tblParserDetail != null) {
                    String amValue = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (amValue != null && !amValue.isEmpty()) {
                            tblTransDetail.setAmount(new BigDecimal(amValue));
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setAmount(new BigDecimal(amValue));
                    }
                }


                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "BENEFICIARY_BRANCH_CODE");
                if(tblParserDetail != null) {
                    String bcValue = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (bcValue != null && !bcValue.isEmpty()) {
                            tblTransDetail.setBeneficiaryBranchCode(bcValue);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setBeneficiaryBranchCode(bcValue);
                    }
                }


                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "BENEFICIARY_OTHER_CONTACT");
                if(tblParserDetail != null) {
                    String ocValue = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (ocValue != null && !ocValue.isEmpty()) {
                            tblTransDetail.setBeneficiaryOtherContact(ocValue);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setBeneficiaryOtherContact(ocValue);
                    }
                }

                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "BENEFICIARY_BANK_NAME");
                if(tblParserDetail != null) {
                    String bbnValue = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (bbnValue != null && !bbnValue.isEmpty()) {
                            tblTransDetail.setBeneficiaryBankName(bbnValue);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setBeneficiaryBankName(bbnValue);
                    }
                }

                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "BENEFICIARY_BRANCH_ADD1");
                if(tblParserDetail != null) {
                    String bba1Value = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (bba1Value != null && !bba1Value.isEmpty()) {
                            tblTransDetail.setBeneficiaryBranchAdd1(bba1Value);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setBeneficiaryBranchAdd1(bba1Value);
                    }
                }


                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "BENEFICIARY_BRANCH_ADD2");
                if(tblParserDetail != null) {
                    String bba2Value = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (bba2Value != null && !bba2Value.isEmpty()) {
                            tblTransDetail.setBeneficiaryBranchAdd2(bba2Value);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setBeneficiaryBranchAdd2(bba2Value);
                    }
                }


                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "REMITTER_NAME");
                if(tblParserDetail != null) {
                    String rnValue = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (rnValue != null && !rnValue.isEmpty()) {
                            tblTransDetail.setRemitterName(rnValue);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setRemitterName(rnValue);
                    }
                }

                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "REMITTER_NUMBER");
                if(tblParserDetail != null) {
                    String rnmValue = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (rnmValue != null && !rnmValue.isEmpty()) {
                            tblTransDetail.setRemitterNumber(rnmValue);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setRemitterNumber(rnmValue);
                    }
                }

                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "REMITTER_ADDRESS");
                if(tblParserDetail != null) {
                    String raValue = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (raValue != null && !raValue.isEmpty()) {
                            tblTransDetail.setRemitterAddress(raValue);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setRemitterAddress(raValue);
                    }
                }


                tblParserDetail = getSeqNumFromColumnName(String.valueOf(parserHeadId), "REMITTER_COUNTRY");
                if(tblParserDetail != null) {
                    String rcValue = getCellValue(row, tblParserDetail.getSequence().intValue());
                    if (tblParserDetail.getIsMandatory().equals("Y")) {
                        if (rcValue != null && !rcValue.isEmpty()) {
                            tblTransDetail.setRemitterCountry(rcValue);
                        } else {
                            errorsColumns.add(tblParserDetail.getRefFieldName());
                        }

                    } else {
                        tblTransDetail.setRemitterCountry(rcValue);
                    }
                }

                tblTransDetail.setStatus("N");
                tblTransDetail.setTblTransHead(tblTransHead);
                tblTransDetail.setCreateuser(BigDecimal.valueOf(loggedUserId));


                tblTransDetails.add(tblTransDetail);

            }
            fileValidation.put("errorColumns", errorsColumns);
            fileValidation.put("tblTransDetail", tblTransDetails);

            return fileValidation;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TblTransHead findByName(String fileName) {
        return tblTransHeadRepo.findByFileName(fileName);
    }

    @Override
    public TblFininstBank findByFinistCode(String finInstCode) {
        return tblFininstBankRepo.findByFininstCode(finInstCode);
    }

    @Override
    public TblGlobalConfig getFilePathByKey(String key) {
        return tblGlobalConfigRepo.findByKeyName(key);
    }
}
