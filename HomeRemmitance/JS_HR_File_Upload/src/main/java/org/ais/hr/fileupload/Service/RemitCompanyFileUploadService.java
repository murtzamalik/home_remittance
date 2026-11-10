package org.ais.hr.fileupload.Service;

import org.ais.hr.fileupload.dto.TransactionUpload;
import org.ais.hr.fileupload.model.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


public interface RemitCompanyFileUploadService {


    TblTransHead saveTranshead(TblTransHead tblTransHead);


    TblRemitter getRemittCompanyById(long remitCompId);

    TblTransHead saveManualTransactions(TblTransHead tblTransHead, InputStream fileInputStream, long userId, long parserHeadId);


    TblTransHead savePUSHTransaction(TblTransHead tblTransHead, TransactionUpload transactionUpload, BigDecimal loggedUserId);

    HashMap<String, String> callProcChkDuplicateTransa(long transHeadId, long userId);

    HashMap<String, String> callProcTransBifurcate(long transHeadId, long userId);

    HashMap<String, String> callProcTransValidate(long transHeadId, long userId);

    TblFininstBank getById(long fininstBankId);

    List<TblParserDetail> getByParserHeadId(long parserHeadId);

    TblParserDetail getSeqNumFromColumnName(String parserHeadId ,String columnName);

    TblTransHead findByName(String fileName);

    TblFininstBank findByFinistCode(String finInstCode);

    TblGlobalConfig getFilePathByKey(String key);



}
