package org.ais.hr.fileupload.Service;

import org.ais.hr.fileupload.dto.*;
import org.ais.hr.fileupload.model.TblParserDetail;
import org.ais.hr.fileupload.model.TblTransDetail;

import java.util.List;

public interface ProcessTransactionService {
    List<TblTransDetail> getTransactionsAgainstRemitCompanyForProcess(ProcessTransAgnstRmtCmpny processTransAgnstRmtCmpny);

   void parkTransactionsForAmendments(ParkTransForAmndmntRequest parkTransForAmndmntRequest, long userId);

    TblTransDetail amendmentTransaction(AmendmentTransactionRequest amendmentTransactionRequest, long userId);

    List<TblTransDetail> getTbLTransDetailsByIds(List<Long> transDetailIds);

    List<TransactionInquiryResponse> getTransactionInquiry(TransactionInquiryRequest transactionInquiryRequest);



}
