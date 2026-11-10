package org.ais.hr.transactions.Service;

import org.ais.hr.transactions.dto.*;
import org.ais.hr.transactions.model.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ProcessTransactionService {

    List<TblTransDetail> getTransactionsAgainstRemitCompanyForProcess(ProcessTransAgnstRmtCmpny processTransAgnstRmtCmpny);

    void parkTransactionsForAmendments(ParkTransForAmndmntRequest parkTransForAmndmntRequest, long userId);

    TblTransDetail amendmentTransaction(AmendmentTransactionRequest amendmentTransactionRequest, long userId);

    List<TblTransDetail> getTbLTransDetailsByIds(List<Long> transDetailIds);

    List<TransactionInquiryResponse> getTransactionInquiry(TransactionInquiryRequest transactionInquiryRequest);


    Long getBatchNumberFrom();

    List<TblMt102Temp> saveMt102Temp(List<TblMt102Temp> tblMt102Temps);

    String getSystemTraceNumber();

    String getFromAccNumber(long fininstBankId);

    List<TblTransDetail> getTbLTransDetailsByTransType(List<Long> transDetailIds, String transType);

    List<TblMt102Detail> getAllTransactionForMT102File(long tblMt102HeadId);

    HashMap<String, Object> populateMt102(long batchNumber, long userId) throws SQLException;

    List<TblMt102Head> getTbmlMt102Head(List<String> headpks);

    List<FinInstWiseFileNameResponse> finInstWiseFileName(FinInstWiseFileNameRequest finInstWiseFileNameRequest);

    TblGlobalConfig getFilePathByKey(String key);

    List<BatchInquiryResponse> batchInquiry(BatchInquiryRequest batchInquiryRequest);

    List<TblTransDetail> transactionsInquiryStatusWise(TransactionsInqStatusWiseRequest transactionsInqStatusWiseRequest);

   TblRequest saveRequest(TblRequest tblRequest);

    TblResponse saveResponse(TblResponse tblResponse);

    TblAuthAccessToken tokenVerification(BigDecimal userId, String header);

    TblUserActivityLog activityLogging(TblUserActivityLog tblUserActivityLog);


}
