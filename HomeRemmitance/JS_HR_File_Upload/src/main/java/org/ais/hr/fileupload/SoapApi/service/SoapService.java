package org.ais.hr.fileupload.SoapApi.service;

import org.ais.hr.fileupload.Service.RemitCompanyFileUploadService;
import org.ais.hr.fileupload.SoapApi.dto.TransactionRequest;

import org.ais.hr.fileupload.dto.LoggedUserDetail;
import org.ais.hr.fileupload.dto.Transaction;
import org.ais.hr.fileupload.dto.TransactionUpload;
import org.ais.hr.fileupload.model.TblRemitter;
import org.ais.hr.fileupload.model.TblTransHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SoapService {
    @Autowired
    private RemitCompanyFileUploadService remitCompanyFileUploadService;


    public TblTransHead transactionupload(JAXBElement<TransactionRequest> transactionUploadrequest, LoggedUserDetail loggedUserDetail){
        TblTransHead tblTransHead = new TblTransHead();


        TblRemitter tblRemitter = new TblRemitter();


        tblRemitter.setRemitterId(Long.valueOf(transactionUploadrequest.getValue().getRemitId()));

        tblTransHead.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblTransHead.setApiType("PUSH");
        tblTransHead.setCreatedate(new Date());
        tblTransHead.setRecordCount(BigDecimal.valueOf(transactionUploadrequest.getValue().getTransactionlist().getTransaction().size()));
        tblTransHead.setStatus("A");
        tblTransHead.setTblRemitter(tblRemitter);
        TransactionUpload transactionUpload=new TransactionUpload();
        transactionUpload.setRemitId((int) transactionUploadrequest.getValue().getRemitId());
        List<Transaction> transactionList=new ArrayList<>();
        Transaction transaction=new Transaction();
        List<org.ais.hr.fileupload.SoapApi.dto.Transaction> transactions=transactionUploadrequest.getValue().getTransactionlist().getTransaction();
        for(int i=0;i<transactions.size();i++){
            transaction.setAmount(transactions.get(i).getAmount());
            transaction.setBeneficiaryAccountNo(transactions.get(i).getBeneficiaryAccountNo());
            transaction.setBeneficiaryAccountTitle(transactions.get(i).getBeneficiaryAccountTitle());
            transaction.setBeneficiaryBankName(transactions.get(i).getBeneficiaryBankName());
            transaction.setBeneficiaryBranchAdd1(transactions.get(i).getBeneficiaryBranchAdd1());
            transaction.setBeneficiaryBranchAdd2(transactions.get(i).getBeneficiaryBranchAdd2());
            transaction.setBeneficiaryBranchCode(transactions.get(i).getBeneficiaryBranchCode());
            transaction.setBeneficiaryCnic(transactions.get(i).getBeneficiaryCnic());
            transaction.setBeneficiaryName(transactions.get(i).getBeneficiaryName());
            transaction.setBeneficiaryOtherContact(transactions.get(i).getBeneficiaryOtherContact());
            transaction.setRemitterAddress(transactions.get(i).getRemitterAddress());
            transaction.setRemitterName(transactions.get(i).getRemitterName());
            transaction.setRemitterNumber(transactions.get(i).getRemitterNumber());
            transaction.setTransactionReference(transactions.get(i).getTransactionReference());
            transactionList.add(transaction);
            transaction=new Transaction();
        }
        transactionUpload.setTransactionList(transactionList);

        tblTransHead = remitCompanyFileUploadService.savePUSHTransaction(tblTransHead,transactionUpload,BigDecimal.valueOf(loggedUserDetail.getUserId()));
        return tblTransHead;
    }

}
