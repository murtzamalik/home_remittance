package org.ais.hr.transactions.Service.impl;

import org.ais.hr.transactions.Service.TransactionService;
import org.ais.hr.transactions.dto.*;
import org.ais.hr.transactions.model.LkpStatus;
import org.ais.hr.transactions.model.TblMt102Head;
import org.ais.hr.transactions.model.TblRemitMode;
import org.ais.hr.transactions.model.TblTransDetail;
import org.ais.hr.transactions.repo.*;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/27/2022
 * Time: 5:02 PM
 * Project : login
 */


@Service
@Transactional(rollbackOn = Exception.class)
public class TransactionServiceImpl implements TransactionService {

    @PersistenceContext
    @Autowired
    EntityManager em;

    @Autowired
    private TblTransDetailRepo tblTransDetailRepo;

    @Autowired
    private TblTransHeadRepo tblTransHeadRepo;

    @Autowired
    private TblRemitModeRepo tblRemitModeRepo;

    @Autowired
    private LkpStatusRepo lkpStatusRepo;

    @Autowired
    private TblMt102HeadRepo tblMt102HeadRepo;


    @Override
    public List<TblTransDetail> getAllAmendmentsTransactions() {
        return tblTransDetailRepo.findByTransAmdAndStatus("Y", "N1");
    }


    @Override
    public List<BtTransactionResponse> getAllBtTransactions(BtTransactionRequest btTransactionRequest) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String fdate = simpleDateFormat.format(btTransactionRequest.getFromDate());
        String tdate = simpleDateFormat.format(btTransactionRequest.getToDate());
        List<Object> rslt = tblTransHeadRepo.getBtTransactions(btTransactionRequest.getFininstBankId(), fdate, tdate);
        if (rslt != null && rslt.size() > 0) {
            List<BtTransactionResponse> btTransactionResponses = new ArrayList<>();
            for (Object record : rslt) {
                Object[] row = (Object[]) record;
                BtTransactionResponse btTransactionResponse = new BtTransactionResponse();

                btTransactionResponse.setTransDetialId((BigDecimal) row[0]);
                btTransactionResponse.setTransHeadId((BigDecimal) row[1]);
                btTransactionResponse.setRemitterName((String) row[2]);
                btTransactionResponse.setRemitterNumber((String) row[3]);
                btTransactionResponse.setRemitterAddress((String) row[4]);
                btTransactionResponse.setTransactionReference((String) row[5]);
                btTransactionResponse.setTransactionNumber((String) row[6]);
                btTransactionResponse.setAmount((BigDecimal) row[7]);
                btTransactionResponse.setBeneficaryName((String) row[8]);
                btTransactionResponse.setBeneficaryCnic((String) row[9]);
                btTransactionResponse.setBeneficaryAccNo((String) row[10]);
                btTransactionResponse.setBeneficaryBranchCode((String) row[11]);
                btTransactionResponse.setBeneficaryOtherContact((String) row[12]);
                btTransactionResponse.setBeneficaryBankName((String) row[13]);
                btTransactionResponse.setBeneficaryBranchAdd1((String) row[14]);
                btTransactionResponse.setBeneficaryBranchAdd2((String) row[15]);
                btTransactionResponse.setBeneficaryAccTitle((String) row[16]);
                btTransactionResponse.setStatus((String) row[17]);
                btTransactionResponse.setStatusDescr((String) row[18]);
                btTransactionResponse.setT24TransactionReference((String) row[19]);
                btTransactionResponse.setT24TransactionDescr((String) row[20]);
                btTransactionResponse.setCreateUser((BigDecimal) row[21]);
                btTransactionResponse.setCreateDate((Date) row[22]);
                btTransactionResponse.setLastUpdateUser((BigDecimal) row[23]);
                btTransactionResponse.setLastUpdateDate((Date) row[24]);
                btTransactionResponse.setUpdateIndex((BigDecimal) row[25]);
                btTransactionResponse.setTransType((String) row[26]);
                btTransactionResponse.setTransBt((String) row[27]);
                btTransactionResponse.setTransAmd((String) row[28]);
                btTransactionResponse.setT24TransactionDate((Date) row[29]);
                btTransactionResponse.setRemitterCountry((String) row[30]);


                btTransactionResponses.add(btTransactionResponse);


            }
            return btTransactionResponses;

        } else {
            return null;
        }
    }

    @Override
    public HashMap<String, String> callBifurcateAndValidate(TblTransDetail tblTransDetail, long userId) {
//        PROCEDURE PROC_BIFURCATE_VALIDATE
//        (
//         P_TRANS_DETAIL_ID IN NUMBER,
//        P_USER_ID IN NUMBER,
//        P_STATUS OUT NUMBER,
//        P_STATUSDESCR OUT VARCHAR2
//)
        HashMap<String, String> response = new HashMap<>();

        try {
            Session session = em.unwrap(Session.class);
            final int[] status = new int[1];
            final String[] statusDescr = new String[1];

            session.doWork(new Work() {
                public void execute(Connection connection) throws SQLException {
                    CallableStatement call = connection.prepareCall("{call PROC_BIFURCATE_VALIDATE(?,?,?,?) }");

                    call.setLong(1, tblTransDetail.getTransDetailId());
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
    public TblTransDetail updateTransactionStatus(TblTransDetail tblTransDetail, LoggedUserDetail loggedUserDetail) {
        tblTransDetail.setLastupdatedate(new Date());
        tblTransDetail.setLastupdateuser(new BigDecimal(loggedUserDetail.getUserId()));
        tblTransDetail.setUpdateindex(tblTransDetail.getUpdateindex() != null ? new BigDecimal(tblTransDetail.getUpdateindex().longValue() + 1) : new BigDecimal(1));
        return tblTransDetailRepo.saveAndFlush(tblTransDetail);
    }

    @Override
    public TblTransDetail findByIdforStatus(long transDetailId) {
        return tblTransDetailRepo.findById(transDetailId).orElse(null);
    }

    @Override
    public List<TblTransDetail> getTransactionDetailsByIds(List<Long> transDetailIds) {
        return tblTransDetailRepo.findAllById(transDetailIds);
    }

    @Override
    public List<LovResponse> lovTransType() {
        List<TblRemitMode> results = tblRemitModeRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblRemitMode result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getRemitCode()));

                lovResponse.setCode(result.getRemitCode());
                lovResponse.setDescription(result.getRemitDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<TblTransDetail> singleTransactionInquiry(SingleTransactionInquiryRequest singleTransactionInquiryRequest) {
        List<TblTransDetail> transDetail = new ArrayList<>();

            transDetail = tblTransDetailRepo.singleTransactionInquiry(singleTransactionInquiryRequest.getBeneficiaryAccountNo(), singleTransactionInquiryRequest.getAmount(),
                    singleTransactionInquiryRequest.getBeneficiaryAccountTitle(), singleTransactionInquiryRequest.getBeneficiaryBankName()
                    , singleTransactionInquiryRequest.getBeneficiaryBranchAdd1(), singleTransactionInquiryRequest.getBeneficiaryBranchAdd2(), singleTransactionInquiryRequest.getBeneficiaryBranchCode()
                    , singleTransactionInquiryRequest.getBeneficiaryCnic(), singleTransactionInquiryRequest.getBeneficiaryName(), singleTransactionInquiryRequest.getBeneficiaryOtherContact()
                    , singleTransactionInquiryRequest.getRemitterAddress(), singleTransactionInquiryRequest.getRemitterName(), singleTransactionInquiryRequest.getRemitterNumber(),
                    singleTransactionInquiryRequest.getStatus(), singleTransactionInquiryRequest.getStatusDescr(), singleTransactionInquiryRequest.getTransactionReference(), singleTransactionInquiryRequest.getTransType());


        if(transDetail!=null && transDetail.size()>0){
            return transDetail;
        }
        else {
            return null;
        }

    }

    @Override
    public List<LovResponse> lovStatus() {
        List<LkpStatus> statuses = lkpStatusRepo.findAll();

        if (statuses != null && statuses.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            for (LkpStatus lkpStatus : statuses) {
                LovResponse lovResponse = new LovResponse();
                lovResponse.setId(String.valueOf(lkpStatus.getStatusId()));
                lovResponse.setCode(lkpStatus.getStatusCode());
                lovResponse.setDescription(lkpStatus.getStatusDescr());
                lovResponses.add(lovResponse);
            }


        return lovResponses;
        }
     else {
        return null;
    }
}

    @Override
    public List<LovResponse> lovBankNames(long finInstId) {
        List<String> bankName=new ArrayList<>();
        if(finInstId==0){

            bankName= tblMt102HeadRepo.getBankName();
        }
        else {
            bankName= tblMt102HeadRepo.getBankNameByFinInst(finInstId);
        }





            if (bankName != null && bankName.size() > 0) {
                List<LovResponse> lovResponses = new ArrayList<>();
                for (String bn : bankName) {
                    LovResponse lovResponse = new LovResponse();

                    lovResponse.setCode(bn);

                    lovResponses.add(lovResponse);
                }


                return lovResponses;
            } else {
                return null;
            }
        }


    @Override
    public List<Mt102InquiryResponse> mt102Inquiry(Mt102InquiryRequest mt102InquiryRequest) {
        List<Object> result=new ArrayList<>();
        if(mt102InquiryRequest.getFinInstId()==0){
            result=tblMt102HeadRepo.mt102FilesAgainstBankName(mt102InquiryRequest.getBankName());
        }
        else{
            result=tblMt102HeadRepo.mt102FilesAgainstBankNameandfinInstId(mt102InquiryRequest.getFinInstId(),mt102InquiryRequest.getBankName());
        }
        if(result!=null && result.size()>0){
        List<Mt102InquiryResponse> mt102InquiryResponses=new ArrayList<>();
        for(Object record:result){
            Object[] row = (Object[]) record;
            Mt102InquiryResponse mt102InquiryResponse=new Mt102InquiryResponse();
            mt102InquiryResponse.setBankName((String)row[0]);
            mt102InquiryResponse.setFileRefNo((String)row[1]);
            mt102InquiryResponse.setAmount((BigDecimal)row[2]);
            mt102InquiryResponse.setFinInstDescr((String)row[3]);
            mt102InquiryResponse.setNoOfTrans((BigDecimal)row[4]);
            mt102InquiryResponses.add(mt102InquiryResponse);
        }
        return mt102InquiryResponses;
        }
        else {
       return null;
    }
    }
}
