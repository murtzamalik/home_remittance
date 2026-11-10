package org.ais.hr.transactions.repo;

import org.ais.hr.transactions.model.TblMt102Head;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TblMt102HeadRepo extends JpaRepository<TblMt102Head,Long> {

    @Query(value="SELECT DISTINCT BANK_NAME\n" +
            "FROM TBL_MT102_HEAD\n" +
            "WHERE FININST_BANK_ID = NVL(:finInstId, FININST_BANK_ID)",nativeQuery = true)
    List<String> getBankNameByFinInst(@Param("finInstId") long finInstId);

    @Query(value="SELECT DISTINCT BANK_NAME\n" +
            "FROM TBL_MT102_HEAD\n" +
            "WHERE FININST_BANK_ID = NVL(NULL, FININST_BANK_ID)",nativeQuery = true)
    List<String> getBankName();

    @Query(value = "SELECT BANK_NAME,FILE_REFERENCE_NO,TOTAL_AMOUNT,FININST_DESCR,NO_OF_TRANSACTIONS\n" +
            "FROM TBL_MT102_HEAD H\n" +
            "INNER JOIN TBL_FININST_BANK B ON H.FININST_BANK_ID = B.FININST_BANK_ID\n" +
            "WHERE H.FININST_BANK_ID = NVL(:finInstId,H.FININST_BANK_ID)\n" +
            "AND H.BANK_NAME = NVL(:bankName,H.BANK_NAME)",nativeQuery = true)
    List<Object> mt102FilesAgainstBankNameandfinInstId(@Param("finInstId")long finInstId,@Param("bankName")String bankName);

    @Query(value = "SELECT BANK_NAME,FILE_REFERENCE_NO,TOTAL_AMOUNT,FININST_DESCR,NO_OF_TRANSACTIONS\n" +
            "FROM TBL_MT102_HEAD H\n" +
            "INNER JOIN TBL_FININST_BANK B ON H.FININST_BANK_ID = B.FININST_BANK_ID\n" +
            "WHERE H.FININST_BANK_ID = NVL(NULL,H.FININST_BANK_ID)\n" +
            "AND H.BANK_NAME = NVL(:bankName,H.BANK_NAME)",nativeQuery = true)
    List<Object> mt102FilesAgainstBankName(@Param("bankName")String bankName);
}
