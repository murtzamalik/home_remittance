package org.ais.hr.fileupload.Service.impl;

import org.ais.hr.fileupload.Service.ProcessTransactionService;
import org.ais.hr.fileupload.dto.*;
import org.ais.hr.fileupload.model.TblParserDetail;
import org.ais.hr.fileupload.model.TblTransDetail;
import org.ais.hr.fileupload.repo.TblFininstBankRepo;
import org.ais.hr.fileupload.repo.TblParserDetailRepo;
import org.ais.hr.fileupload.repo.TblTransDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    @Autowired
    private TblTransDetailRepo tblTransDetailRepo;

    @Autowired
    TblFininstBankRepo tblFininstBankRepo;

    @Autowired
    TblParserDetailRepo tblParserDetailRepo;

    @Override
    public List<TblTransDetail> getTransactionsAgainstRemitCompanyForProcess(ProcessTransAgnstRmtCmpny processTransAgnstRmtCmpny) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String fdate = simpleDateFormat.format(processTransAgnstRmtCmpny.getFromDate());
        String tdate = simpleDateFormat.format(processTransAgnstRmtCmpny.getToDate());


        return tblTransDetailRepo.getTransactionsAgainstRemitCompanyForProcess(fdate, tdate,
                processTransAgnstRmtCmpny.getTransType(), processTransAgnstRmtCmpny.getRemitIds());
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

            tblTransDetail.setStatus("N");
            tblTransDetail.setTransBt("N");

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
        List<Object> rslt = tblFininstBankRepo.transactionInquiry(transactionInquiryRequest.getFininstBankId(),transactionInquiryRequest.getFileName(),fdate,tdate);
        if(rslt!=null&&rslt.size()>0){
            List<TransactionInquiryResponse> transactionInquiryResponses=new ArrayList<>();
            for(Object record:rslt){
                Object[] row = (Object[]) record;
                TransactionInquiryResponse transactionInquiryResponse=new TransactionInquiryResponse();

                    transactionInquiryResponse.setFinInstCode((String) row[0]);
                    transactionInquiryResponse.setFinInstDescr((String)row[1]);
                    transactionInquiryResponse.setRemitterName((String)row[2]);
                    transactionInquiryResponse.setRemitterNumber((String)row[3]);
                    transactionInquiryResponse.setRemitterAddress((String)row[4]);
                    transactionInquiryResponse.setRemitterCountry((String)row[5]);
                    transactionInquiryResponse.setTransactionReference((String)row[6]);
                    transactionInquiryResponse.setAmount((BigDecimal)row[7]);
                    transactionInquiryResponse.setBeneficaryName((String)row[8]);
                    transactionInquiryResponse.setBeneficaryCnic((String)row[9]);
                    transactionInquiryResponse.setBeneficaryAccNo((String)row[10]);
                    transactionInquiryResponse.setBeneficaryAccTitle((String)row[11]);
                    transactionInquiryResponse.setBeneficaryBranchCode((String)row[12]);
                    transactionInquiryResponse.setBeneficaryOtherContact((String)row[13]);
                    transactionInquiryResponse.setBeneficaryBankName((String)row[14]);
                    transactionInquiryResponse.setBeneficaryBranchAdd1((String)row[15]);
                    transactionInquiryResponse.setBeneficaryBranchAdd2((String)row[16]);
                    transactionInquiryResponse.setValueDate((String)row[17]);
                    transactionInquiryResponse.setPaymentDate((String)row[18]);
                    transactionInquiryResponse.setTransMode((String)row[19]);
                    transactionInquiryResponses.add(transactionInquiryResponse);




            }
            return transactionInquiryResponses;

        }
        else {
            return null;
        }

    }


}
