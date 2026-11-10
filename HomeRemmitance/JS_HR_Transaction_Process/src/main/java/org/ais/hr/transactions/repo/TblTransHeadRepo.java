package org.ais.hr.transactions.repo;

import org.ais.hr.transactions.model.TblTransHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblTransHeadRepo extends JpaRepository<TblTransHead,Long> {
    TblTransHead findByFileName(String fileName);
    @Query(value = "SELECT D.*\n" +
            "FROM TBL_TRANS_HEAD H\n" +
            "INNER JOIN TBL_TRANS_DETAIL D ON H.TRANS_HEAD_ID = D.TRANS_HEAD_ID\n" +
            "WHERE H.CREATEDATE BETWEEN TO_DATE(:fromDate,'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(:toDate,'YYYY-MM-DD HH24:MI:SS')\n" +
            "AND D.STATUS IN ('BT','BTE') " +
            "AND D.TRANS_BT = 'Y'\n" +
            "AND D.TRANS_TYPE = 'IB'\n" +
            "AND H.FININST_BANK_ID IN :fininstBankId ",nativeQuery = true)
    List<Object> getBtTransactions(@Param("fininstBankId") List<Long> fininstBankIds, @Param("fromDate")String fromDate, @Param("toDate")String toDate);

    @Query(value = "SELECT TRANS_HEAD_ID, FILE_NAME\n" +
            "FROM TBL_TRANS_HEAD\n" +
            "WHERE FININST_BANK_ID IN :fininstBankId\n" +
            "AND CREATEDATE BETWEEN NVL(TO_DATE(:fromDate,'YYYY-MM-DD'),CREATEDATE) AND NVL(TO_DATE(:toDate,'YYYY-MM-DD'),CREATEDATE)",nativeQuery = true)
    List<Object> getAllBatchTransactions(@Param("fininstBankId") List<Long> fininstBankIds, @Param("fromDate")String fromDate, @Param("toDate")String toDate);



    @Query(value = "SELECT H.TRANS_HEAD_ID, NVL(H.API_TYPE, H.FILE_NAME) SOURCE_FILENAME, TO_CHAR(H.CREATEDATE,'DD-MON-YYYY') FILE_DATE, B.FININST_DESCR, H.RECORD_COUNT TOTAL_RECORDS,\n" +
            "TAMT.TAMT TOTAL_AMOUNT, NVL(PAMT.PAMT,0) PAID_AMOUNT, TAMT.TAMT - NVL(PAMT.PAMT,0) REMAINING_AMOUNT\n" +
            "FROM TBL_TRANS_HEAD H\n" +
            "INNER JOIN (SELECT TRANS_HEAD_ID, SUM(AMOUNT) TAMT\n" +
            "FROM TBL_TRANS_DETAIL\n" +
            "GROUP BY TRANS_HEAD_ID) TAMT ON H.TRANS_HEAD_ID = TAMT.TRANS_HEAD_ID\n" +
            "LEFT OUTER JOIN (SELECT TRANS_HEAD_ID, SUM(AMOUNT) PAMT\n" +
            "FROM TBL_TRANS_DETAIL\n" +
            "WHERE STATUS = 'S'\n" +
            "GROUP BY TRANS_HEAD_ID) PAMT ON H.TRANS_HEAD_ID = PAMT.TRANS_HEAD_ID\n" +
            "INNER JOIN TBL_FININST_BANK B ON H.FININST_BANK_ID = B.FININST_BANK_ID\n" +
            "WHERE H.FININST_BANK_ID IN :fininstBankId \n" +
            "AND H.CREATEDATE BETWEEN NVL(TO_DATE(:fromDate,'YYYY-MM-DD'),H.CREATEDATE) AND NVL(TO_DATE(:toDate,'YYYY-MM-DD'),H.CREATEDATE)\n" +
            "AND (NVL(H.API_TYPE,'A') IN :apiType \n" +
            "OR NVL(H.FILE_NAME,'A') IN :fileNames)\n" +
            "ORDER BY H.TRANS_HEAD_ID",nativeQuery = true)
    List<Object> getAllBatchWiseInquiry(@Param("fininstBankId") List<Long> fininstBankIds, @Param("fromDate")String fromDate, @Param("toDate")String toDate,@Param("apiType") List<String> apiType,@Param("fileNames") List<String> fileNames);


 @Query(value = "SELECT S.STATUS_CODE, S.STATUS_DESCR, COUNT(D.TRANS_DETAIL_ID) QTY\n" +
         "FROM LKP_STATUS S\n" +
         "LEFT OUTER JOIN TBL_TRANS_DETAIL D ON D.STATUS = S.STATUS_CODE AND D.TRANS_HEAD_ID = :transHeadId\n" +
         "GROUP BY D.TRANS_HEAD_ID, S.STATUS_CODE, S.STATUS_DESCR",nativeQuery = true)
    List<Object> statusWiseCount(@Param("transHeadId") Long transHeadId);

}
