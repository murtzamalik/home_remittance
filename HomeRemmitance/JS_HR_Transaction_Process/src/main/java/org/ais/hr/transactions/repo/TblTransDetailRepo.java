package org.ais.hr.transactions.repo;

import org.ais.hr.transactions.model.TblTransDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TblTransDetailRepo extends JpaRepository<TblTransDetail,Long> {

    @Query(value = "SELECT D.*\n" +
            "FROM TBL_TRANS_HEAD H\n" +
            "INNER JOIN TBL_TRANS_DETAIL D ON H.TRANS_HEAD_ID = D.TRANS_HEAD_ID\n" +
            "WHERE H.CREATEDATE BETWEEN TO_DATE(:fromDate,'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(:toDate,'YYYY-MM-DD HH24:MI:SS')" +
            "AND D.TRANS_TYPE IN :transType AND D.STATUS = 'V' AND D.TRANS_BT = 'N'\n" +
            "AND H.FININST_BANK_ID IN :finInstBankIds ",nativeQuery = true)
    List<TblTransDetail> getTransactionsAgainstRemitCompanyForProcess(@Param("fromDate")String fromDate,@Param("toDate")String toDate,@Param("transType") List<String> transType
                                                                    ,@Param("finInstBankIds") List<Long> finInstBankIds);
    @Modifying
    @Query("update TblTransDetail j set " +
            " j.lastupdatedate = :lastupdatedate, " +
            " j.lastupdateuser = :lastupdateuser," +
            "  j.updateindex = NVL(j.updateindex,0) + 1 ,  " +
            " j.transAmd = 'Y', " +
            " j.status = 'N1' " +
            "  where j.transDetailId = :transDetailId")
    int parkTransactionsForAmendments(@Param("transDetailId") Long transDetailId, @Param("lastupdatedate") Date lastupdatedate, @Param("lastupdateuser") BigDecimal lastupdateuser);


    List<TblTransDetail> findByTransBt(String bt);
    
    List<TblTransDetail> findByTransAmdAndStatus(String amdStatus,String status);

    List<TblTransDetail> findByTransDetailIdIn(List<Long> transDetailsIds);

    List<TblTransDetail> findByTransDetailIdInAndTransType(List<Long> transDetailsIds,String transType);

    @Query(value = "SELECT STAN_SEQ.NEXTVAL FROM DUAL ",nativeQuery = true)
    BigDecimal getSystemTraceAuditNumber();


    @Query(value = "SELECT *\n" +
            "FROM TBL_TRANS_DETAIL\n" +
            "WHERE TRANS_HEAD_ID = :transDetailId\n" +
            "AND STATUS = :statusCode\n" +
            "ORDER BY TRANS_DETAIL_ID ",nativeQuery = true)
    List<TblTransDetail> transactionsInquiryStatusWise(@Param("transDetailId")long transDetailId,@Param("statusCode")String statusCode);

    @Query(value = "SELECT *\n" +
            "FROM TBL_TRANS_DETAIL\n" +
            "WHERE NVL(BENEFICIARY_ACCOUNT_NO,'1') = NVL(:beneficaryAccNo, NVL(BENEFICIARY_ACCOUNT_NO,'1'))\n" +
            "AND NVL(AMOUNT,1) = NVL(:amount, NVL(AMOUNT,1))\n" +
            "AND NVL(BENEFICIARY_ACCOUNT_TITLE,'1') = NVL(:beneficaryAccTitle, NVL(BENEFICIARY_ACCOUNT_TITLE,'1'))\n" +
            "AND NVL(BENEFICIARY_BANK_NAME,'1') = NVL(:beneficaryBankName, NVL(BENEFICIARY_BANK_NAME,'1'))\n" +
            "AND NVL(BENEFICIARY_BRANCH_ADD1,'1') = NVL(:beneficaryBranchAdd1, NVL(BENEFICIARY_BRANCH_ADD1,'1'))\n" +
            "AND NVL(BENEFICIARY_BRANCH_ADD2,'1') = NVL(:beneficaryBranchAdd2, NVL(BENEFICIARY_BRANCH_ADD2,'1'))\n" +
            "AND NVL(BENEFICIARY_BRANCH_CODE,'1') = NVL(:beneficaryBranchCode, NVL(BENEFICIARY_BRANCH_CODE,'1'))\n" +
            "AND NVL(BENEFICIARY_CNIC,'1') = NVL(:beneficaryCnic, NVL(BENEFICIARY_CNIC,'1'))\n" +
            "AND NVL(BENEFICIARY_NAME,'1') = NVL(:beneficaryName, NVL(BENEFICIARY_NAME,'1'))\n" +
            "AND NVL(BENEFICIARY_OTHER_CONTACT,'1') = NVL(:beneficaryOtherContact, NVL(BENEFICIARY_OTHER_CONTACT,'1'))\n" +
            "AND NVL(REMITTER_ADDRESS,'1') = NVL(:remitterAdress, NVL(REMITTER_ADDRESS,'1'))\n" +
            "AND NVL(REMITTER_NAME,'1') = NVL(:remitterName, NVL(REMITTER_NAME,'1'))\n" +
            "AND NVL(REMITTER_NUMBER,'1') = NVL(:remitterNumber, NVL(REMITTER_NUMBER,'1'))\n" +
            "AND NVL(STATUS,'1') = NVL(:status, NVL(STATUS,'1'))\n" +
            "AND NVL(STATUS_DESCR,'1') = NVL(:statusDescr, NVL(STATUS_DESCR,'1'))\n" +
            "AND NVL(TRANSACTION_REFERENCE,'1') = NVL(:transactionReference, NVL(TRANSACTION_REFERENCE,'1'))\n" +
            "AND NVL(TRANS_TYPE,'1') = NVL(:transType, NVL(TRANS_TYPE,'1')) ",nativeQuery = true)
    List<TblTransDetail> singleTransactionInquiry(@Param("beneficaryAccNo")String beneficaryAccNo,@Param("amount")String amount,@Param("beneficaryAccTitle")String beneficaryAccTitle,
                                            @Param("beneficaryBankName")String beneficaryBankName,@Param("beneficaryBranchAdd1")String beneficaryBranchAdd1,
                                            @Param("beneficaryBranchAdd2")String beneficaryBranchAdd2,@Param("beneficaryBranchCode")String beneficaryBranchCode,
                                            @Param("beneficaryCnic")String beneficaryCnic,@Param("beneficaryName")String beneficaryName,@Param("beneficaryOtherContact")String beneficaryOtherContact,
                                            @Param("remitterAdress")String remitterAdress,@Param("remitterName")String remitterName,@Param("remitterNumber")String remitterNumber,@Param("status")String status,@Param("statusDescr")String statusDescr,
                                            @Param("transactionReference")String transactionReference,@Param("transType")String transType);




}
