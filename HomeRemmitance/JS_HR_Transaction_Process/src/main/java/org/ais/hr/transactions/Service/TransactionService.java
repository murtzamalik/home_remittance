package org.ais.hr.transactions.Service;

import org.ais.hr.transactions.dto.*;
import org.ais.hr.transactions.model.TblMt102Head;
import org.ais.hr.transactions.model.TblRemitMode;
import org.ais.hr.transactions.model.TblTransDetail;

import java.util.HashMap;
import java.util.List;

public interface TransactionService {
    List<TblTransDetail> getAllAmendmentsTransactions();


    List<BtTransactionResponse> getAllBtTransactions(BtTransactionRequest btTransactionRequest);


    HashMap<String, String> callBifurcateAndValidate(TblTransDetail tblTransDetail, long userId);

    TblTransDetail updateTransactionStatus(TblTransDetail tblTransDetail, LoggedUserDetail loggedUserDetail);

    TblTransDetail findByIdforStatus(long transDetailId);

    List<TblTransDetail> getTransactionDetailsByIds(List<Long> transDetailIds);

    List<LovResponse> lovTransType();

    List<TblTransDetail> singleTransactionInquiry(SingleTransactionInquiryRequest singleTransactionInquiryRequest);

    List<LovResponse> lovStatus();

    List<LovResponse> lovBankNames(long finInstId);

    List<Mt102InquiryResponse> mt102Inquiry(Mt102InquiryRequest mt102InquiryRequest);
}
