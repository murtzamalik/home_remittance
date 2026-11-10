package org.ais.hr.fileupload.repo;

import org.ais.hr.fileupload.model.TblTransDetail;
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
            "AND D.TRANS_TYPE = :transType AND D.STATUS = 'V' AND D.TRANS_BT = 'N'\n" +
            "AND H.REMITTER_ID IN :remitCompanies ",nativeQuery = true)
    List<TblTransDetail> getTransactionsAgainstRemitCompanyForProcess(@Param("fromDate")String fromDate,@Param("toDate")String toDate,@Param("transType")String transType
                                                                    ,@Param("remitCompanies") List<Long> remitCompanies);

    @Modifying
    @Query("update TblTransDetail j set " +
            " j.lastupdatedate = :lastupdatedate, " +
            " j.lastupdateuser = :lastupdateuser," +
            "  j.updateindex = NVL(j.updateindex,0) + 1 ,  " +
            " j.transAmd = 'Y' " +
            "  where j.transDetailId = :transDetailId")
    int parkTransactionsForAmendments(@Param("transDetailId") Long transDetailId, @Param("lastupdatedate") Date lastupdatedate, @Param("lastupdateuser") BigDecimal lastupdateuser);


    List<TblTransDetail> findByTransBt(String bt);
    
    List<TblTransDetail> findByTransAmd(String bt);

    List<TblTransDetail> findByTransDetailIdIn(List<Long> transDetailsIds);


}
