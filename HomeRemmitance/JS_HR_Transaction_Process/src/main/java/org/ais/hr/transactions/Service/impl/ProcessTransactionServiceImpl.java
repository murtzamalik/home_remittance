package org.ais.hr.transactions.Service.impl;


import org.ais.hr.transactions.Service.ProcessTransactionService;
import org.ais.hr.transactions.dto.*;
import org.ais.hr.transactions.model.*;
import org.ais.hr.transactions.repo.*;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/26/2022
 * Time: 2:01 PM
 * Project : login
 */


@Service
@Transactional(rollbackOn = Exception.class)
public class ProcessTransactionServiceImpl implements ProcessTransactionService {


    @PersistenceContext
    @Autowired
    EntityManager em;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Autowired
    private TblTransDetailRepo tblTransDetailRepo;

    @Autowired
    private TblFininstBankRepo tblFininstBankRepo;
    @Autowired
    private TblMt102TempRepo tblMt102TempRepo;

    @Autowired
    private TblMt102DetailRepo tblMt102DetailRepo;

    @Autowired
    private TblTransHeadRepo tblTransHeadRepo;

    @Autowired
    private TblMt102HeadRepo tblMt102HeadRepo;

    @Autowired
    private TblGlobalConfigRepo tblGlobalConfigRepo;

    @Autowired
    private TblRequestRepo tblRequestRepo;

    @Autowired
    private TblResponseRepo tblResponseRepo;

    @Autowired
    private TblAuthAccessTokenRepo authAccessTokenRepo;

    @Autowired
    private TblUserActivityLogRepo tblUserActivityLogRepo;

    @Override
    public List<TblTransDetail> getTransactionsAgainstRemitCompanyForProcess(ProcessTransAgnstRmtCmpny processTransAgnstRmtCmpny) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String fdate = simpleDateFormat.format(processTransAgnstRmtCmpny.getFromDate());
        String tdate = simpleDateFormat.format(processTransAgnstRmtCmpny.getToDate());


        List<TblTransDetail> tblTransDetails = tblTransDetailRepo.getTransactionsAgainstRemitCompanyForProcess(fdate, tdate,
                processTransAgnstRmtCmpny.getTransType(), processTransAgnstRmtCmpny.getRemitIds());
        return tblTransDetails;
    }


    @Override
    public void parkTransactionsForAmendments(ParkTransForAmndmntRequest parkTransForAmndmntRequest, long userId) {

        for (Long transdetailId : parkTransForAmndmntRequest.getTransDetailIds()) {
            tblTransDetailRepo.parkTransactionsForAmendments(transdetailId, new Date(), BigDecimal.valueOf(userId));
        }

    }

    @Override
    public TblTransDetail amendmentTransaction(AmendmentTransactionRequest amendmentTransactionRequest, long userId) {

        TblTransDetail tblTransDetail = tblTransDetailRepo.findById(amendmentTransactionRequest.getTransDetailId()).orElse(null);
        if (tblTransDetail != null) {

            tblTransDetail.setAmount(amendmentTransactionRequest.getAmount());
            tblTransDetail.setBeneficiaryAccountNo(amendmentTransactionRequest.getBeneficiaryAccountNo());
            tblTransDetail.setBeneficiaryAccountTitle(amendmentTransactionRequest.getBeneficiaryAccountTitle());
            tblTransDetail.setBeneficiaryBankName(amendmentTransactionRequest.getBeneficiaryBankName());
            tblTransDetail.setBeneficiaryBranchAdd1(amendmentTransactionRequest.getBeneficiaryBranchAdd1());
            tblTransDetail.setBeneficiaryBranchAdd2(amendmentTransactionRequest.getBeneficiaryBranchAdd2());
            tblTransDetail.setBeneficiaryBranchCode(amendmentTransactionRequest.getBeneficiaryBranchCode());
            tblTransDetail.setBeneficiaryCnic(amendmentTransactionRequest.getBeneficiaryCnic());
            tblTransDetail.setBeneficiaryName(amendmentTransactionRequest.getBeneficiaryName());
            tblTransDetail.setBeneficiaryOtherContact(amendmentTransactionRequest.getBeneficiaryOtherContact());
            tblTransDetail.setRemitterAddress(amendmentTransactionRequest.getRemitterAddress());
            tblTransDetail.setRemitterNumber(amendmentTransactionRequest.getRemitterNumber());
            tblTransDetail.setRemitterName(amendmentTransactionRequest.getRemitterName());
            tblTransDetail.setTransactionReference(amendmentTransactionRequest.getTransactionReference());
            tblTransDetail.setLastupdatedate(new Date());
            tblTransDetail.setLastupdateuser(new BigDecimal(userId));
            tblTransDetail.setUpdateindex(tblTransDetail.getUpdateindex() != null ? new BigDecimal(tblTransDetail.getUpdateindex().longValue() + 1) : new BigDecimal(1));

            return tblTransDetailRepo.saveAndFlush(tblTransDetail);

        } else {
            return null;
        }
    }


    @Override
    public List<TblTransDetail> getTbLTransDetailsByIds(List<Long> transDetailIds) {
        return tblTransDetailRepo.findByTransDetailIdIn(transDetailIds);
    }

    @Override
    public List<TransactionInquiryResponse> getTransactionInquiry(TransactionInquiryRequest transactionInquiryRequest) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String fdate = simpleDateFormat.format(transactionInquiryRequest.getFromDate());
        String tdate = simpleDateFormat.format(transactionInquiryRequest.getToDate());
        List<Object> rslt = tblFininstBankRepo.transactionInquiry(transactionInquiryRequest.getFininstBankId(), transactionInquiryRequest.getFileName(), fdate, tdate);
        if (rslt != null && rslt.size() > 0) {
            List<TransactionInquiryResponse> transactionInquiryResponses = new ArrayList<>();
            for (Object record : rslt) {
                Object[] row = (Object[]) record;
                TransactionInquiryResponse transactionInquiryResponse = new TransactionInquiryResponse();

                transactionInquiryResponse.setFinInstCode((String) row[0]);
                transactionInquiryResponse.setFinInstDescr((String) row[1]);
                transactionInquiryResponse.setRemitterName((String) row[2]);
                transactionInquiryResponse.setRemitterNumber((String) row[3]);
                transactionInquiryResponse.setRemitterAddress((String) row[4]);
                transactionInquiryResponse.setRemitterCountry((String) row[5]);
                transactionInquiryResponse.setTransactionReference((String) row[6]);
                transactionInquiryResponse.setAmount((BigDecimal) row[7]);
                transactionInquiryResponse.setBeneficaryName((String) row[8]);
                transactionInquiryResponse.setBeneficaryCnic((String) row[9]);
                transactionInquiryResponse.setBeneficaryAccNo((String) row[10]);
                transactionInquiryResponse.setBeneficaryAccTitle((String) row[11]);
                transactionInquiryResponse.setBeneficaryBranchCode((String) row[12]);
                transactionInquiryResponse.setBeneficaryOtherContact((String) row[13]);
                transactionInquiryResponse.setBeneficaryBankName((String) row[14]);
                transactionInquiryResponse.setBeneficaryBranchAdd1((String) row[15]);
                transactionInquiryResponse.setBeneficaryBranchAdd2((String) row[16]);
                transactionInquiryResponse.setValueDate((String) row[17]);
                transactionInquiryResponse.setPaymentDate((String) row[18]);
                transactionInquiryResponse.setTransMode((String) row[19]);
                transactionInquiryResponses.add(transactionInquiryResponse);


            }
            return transactionInquiryResponses;

        } else {
            return null;
        }
    }

    @Override
    public Long getBatchNumberFrom() {
        return tblMt102TempRepo.getBatchNumberForBT().longValue();
    }


    @Override
    public List<TblMt102Temp> saveMt102Temp(List<TblMt102Temp> tblMt102Temps) {
        return tblMt102TempRepo.saveAllAndFlush(tblMt102Temps);
    }

    @Override
    public String getSystemTraceNumber() {
//        Random rnd = new Random();
//        int number = rnd.nextInt(999999);
//
//        // this will convert any number sequence into 6 character.
//        return String.format("%06d", number);
        return tblTransDetailRepo.getSystemTraceAuditNumber().toString();

    }

    @Override
    public String getFromAccNumber(long fininstBankId) {

        String fromAccNum = tblFininstBankRepo.getFromAccNumber(fininstBankId);
        if (fromAccNum != null) {
            return fromAccNum;
        } else {
            return null;
        }
    }

    @Override
    public List<TblTransDetail> getTbLTransDetailsByTransType(List<Long> transDetailIds, String transType) {
        return tblTransDetailRepo.findByTransDetailIdInAndTransType(transDetailIds, transType);
    }

    @Override
    public List<TblMt102Detail> getAllTransactionForMT102File(long tblMt102HeadId) {
        return tblMt102DetailRepo.findByTblMt102HeadMt102HeadId(tblMt102HeadId);
    }

    @Override
    public HashMap<String, Object> populateMt102(long batchNumber, long userId) throws SQLException {

        Connection con = dataSource.getConnection();
        Session session = em.unwrap(Session.class);
        final String[] status = new String[1];
        final String[] headpks = new String[1];
        final String[] statusDescr = new String[1];

//        create or replace PROCEDURE              PROC_POPULATE_MT102
//                (
//                        P_BATCH_NO IN NUMBER,
//                        P_USER_ID IN NUMBER,
//                        P_MT102_HEAD_ID OUT T_MT102_HEAD_ID,
//                        P_STATUS OUT NUMBER,
//                        P_STATUSDESCR OUT VARCHAR2,
//                        P_HEAD_IDS OUT VARCHAR2
//                )


        session.doWork(new Work() {
            public void execute(Connection connection) throws SQLException {
                CallableStatement call = connection
                        .prepareCall("{call PROC_POPULATE_MT102(?,?,?,?,?,?) }");

                call.setLong(1, batchNumber);
                call.setLong(2, userId);

                call.registerOutParameter(3, Types.ARRAY, "T_MT102_HEAD_ID");
                call.registerOutParameter(4, Types.INTEGER);
                call.registerOutParameter(5, Types.VARCHAR);
                call.registerOutParameter(6, Types.VARCHAR);

                call.execute();


                status[0] = String.valueOf(call.getInt(4));
                statusDescr[0] = call.getString(5);
                headpks[0] = call.getString(6);

            }
        });
        HashMap<String, Object> response = new HashMap<>();
        if (headpks != null) {
            List<String> items = Arrays.asList(headpks[0].split("\\s*,\\s*"));
            response.put("mt102HeadIds", items);
        } else {
            response.put("mt102HeadIds", null);
        }


        response.put("status", status);
        response.put("statusDescr", statusDescr);

        return response;
    }

    @Override
    public List<TblMt102Head> getTbmlMt102Head(List<String> headpks) {
        List<Long> pks = new ArrayList<>();
        for (String s : headpks) {

            pks.add(Long.valueOf(s));
        }
        return tblMt102HeadRepo.findAllById(pks);
    }

    @Override
    public List<FinInstWiseFileNameResponse> finInstWiseFileName(FinInstWiseFileNameRequest finInstWiseFileNameRequest) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String fdate = simpleDateFormat.format(finInstWiseFileNameRequest.getFromDate());
        String tdate = simpleDateFormat.format(finInstWiseFileNameRequest.getToDate());
        List<Object> result = tblTransHeadRepo.getAllBatchTransactions(finInstWiseFileNameRequest.getFininstBankId(), fdate, tdate);
        if (result != null && result.size() > 0) {
            List<FinInstWiseFileNameResponse> batchInquiryResponseList = new ArrayList<>();
            for (Object record : result) {
                Object[] row = (Object[]) record;
                FinInstWiseFileNameResponse batchInquiryResponse = new FinInstWiseFileNameResponse();
                batchInquiryResponse.setTransHeadId(((BigDecimal) row[0]).longValue());
                batchInquiryResponse.setFileName((String) row[1]);
                batchInquiryResponseList.add(batchInquiryResponse);
            }
            return batchInquiryResponseList;
        } else {
            return null;
        }

    }

    @Override
    public TblGlobalConfig getFilePathByKey(String key) {
        return tblGlobalConfigRepo.findByKeyName(key);
    }

    @Override
    public List<BatchInquiryResponse> batchInquiry(BatchInquiryRequest batchInquiryRequest) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String fdate = simpleDateFormat.format(batchInquiryRequest.getFromDate());
        String tdate = simpleDateFormat.format(batchInquiryRequest.getToDate());
        List<Object> result=new ArrayList<>();
        if (batchInquiryRequest.getFileNames() != null && batchInquiryRequest.getFileNames().size()>0) {

            result = tblTransHeadRepo.getAllBatchWiseInquiry(batchInquiryRequest.getFininstBankId(), fdate, tdate, batchInquiryRequest.getFileNames(), batchInquiryRequest.getFileNames());
        } else {
                List<String> apiTypes=new ArrayList<>();
                apiTypes.add(batchInquiryRequest.getApiType());
            result = tblTransHeadRepo.getAllBatchWiseInquiry(batchInquiryRequest.getFininstBankId(), fdate, tdate, apiTypes, apiTypes);
            }




        if (result != null && result.size() > 0) {
            List<BatchInquiryResponse> batchInquiryResponseList = new ArrayList<>();
            for (Object record : result) {
                Object[] row = (Object[]) record;


                BatchInquiryResponse batchInquiryResponse = new BatchInquiryResponse();


                batchInquiryResponse.setTransHeadId(((BigDecimal) row[0]));
                batchInquiryResponse.setSourceFileName((String) row[1]);
                batchInquiryResponse.setFileDate((String) row[2]);
                batchInquiryResponse.setFinInstDescr((String) row[3]);
                batchInquiryResponse.setTotalRecords(((BigDecimal) row[4]));
                batchInquiryResponse.setTotalAmount(((BigDecimal) row[5]));
                batchInquiryResponse.setPaidAmount(((BigDecimal) row[6]));
                batchInquiryResponse.setRemainingAmount(((BigDecimal) row[7]));


                List<Object> result2 = tblTransHeadRepo.statusWiseCount(batchInquiryResponse.getTransHeadId().longValue());
                if (result2 != null && result2.size() > 0) {
                    List<TransStatusCount> transStatusCounts = new ArrayList<>();
                    for (Object record2 : result2) {
                        Object[] row2 = (Object[]) record2;
                        TransStatusCount transStatusCount = new TransStatusCount();

                        transStatusCount.setStatusCode((String) row2[0]);
                        transStatusCount.setStatusDescr((String) row2[1]);
                        transStatusCount.setQty((BigDecimal) row2[2]);


                        transStatusCounts.add(transStatusCount);
                    }

                    batchInquiryResponse.setTransStatusCounts(transStatusCounts);
                }

                batchInquiryResponseList.add(batchInquiryResponse);
            }
            return batchInquiryResponseList;
        } else {
            return null;
        }
    }

    @Override
    public List<TblTransDetail> transactionsInquiryStatusWise(TransactionsInqStatusWiseRequest transactionsInqStatusWiseRequest) {

        List<TblTransDetail> tblTransDetails = tblTransDetailRepo.transactionsInquiryStatusWise(transactionsInqStatusWiseRequest.getTransHeadId(), transactionsInqStatusWiseRequest.getStatusCode());
        if (tblTransDetails != null && tblTransDetails.size() > 0) {
            return tblTransDetails;
        } else {
            return null;
        }
    }

    @Override
    public TblRequest saveRequest(TblRequest tblRequest) {

        TblRequest tblRequest1=tblRequestRepo.saveAndFlush(tblRequest);
        if(tblRequest1!=null){
            return tblRequest1;
        }
        else{
            return null;
        }
    }


    @Override
    public TblResponse saveResponse(TblResponse tblResponse) {
        TblResponse tblResponse1=tblResponseRepo.saveAndFlush(tblResponse);
        if(tblResponse1!=null){
            return tblResponse1;
        }
        else{
            return null;
        }
    }
    @Override
    public TblAuthAccessToken tokenVerification(BigDecimal userId, String header) {
        return authAccessTokenRepo.findByUserIdAndAccessToken(userId,header);
    }

    @Override
    public TblUserActivityLog activityLogging(TblUserActivityLog tblUserActivityLog) {
        return tblUserActivityLogRepo.saveAndFlush(tblUserActivityLog);
    }
}
