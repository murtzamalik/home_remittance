package org.ais.hr.fileupload.Service;

import org.ais.hr.fileupload.dto.TransactionInquiryRequest;
import org.ais.hr.fileupload.dto.TransactionInquiryResponse;
import org.ais.hr.fileupload.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    List<TblTransDetail> getAllAmendmentsTransactions();


    TblAuthAccessToken tokenVerification(BigDecimal userId, String header);

    TblUserActivityLog acticityLogging(TblUserActivityLog tblUserActivityLog);
}
