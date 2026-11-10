package org.ais.hr.transactions.repo;

import org.ais.hr.transactions.model.TblFininstBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblFininstBankRepo extends JpaRepository<TblFininstBank , Long> {
TblFininstBank findByFininstCode(String fininstCode);
    @Query(value = "SELECT B.FININST_CODE, B.FININST_DESCR, D.REMITTER_NAME, D.REMITTER_NUMBER, D.REMITTER_ADDRESS, D.REMITTER_COUNTRY, D.TRANSACTION_REFERENCE, D.AMOUNT, D.BENEFICIARY_NAME, D.BENEFICIARY_CNIC, D.BENEFICIARY_ACCOUNT_NO,\n" +
            "D.BENEFICIARY_ACCOUNT_TITLE, D.BENEFICIARY_BRANCH_CODE, D.BENEFICIARY_OTHER_CONTACT, D.BENEFICIARY_BANK_NAME, D.BENEFICIARY_BRANCH_ADD1, D.BENEFICIARY_BRANCH_ADD2,\n" +
            "TO_CHAR(H.CREATEDATE,'DD-MON-YYYY') VALUE_DATE, TO_CHAR(D.T24_TRANSACTION_DATE,'DD-MON-YYYY') PAYMENT_DATE, M.REMIT_DESCR TRANS_MODE\n" +
            "FROM TBL_TRANS_HEAD H\n" +
            "INNER JOIN TBL_TRANS_DETAIL D ON H.TRANS_HEAD_ID = D.TRANS_HEAD_ID\n" +
            "INNER JOIN TBL_FININST_BANK B ON H.FININST_BANK_ID = B.FININST_BANK_ID\n" +
            "LEFT OUTER JOIN TBL_REMIT_MODE M ON D.TRANS_TYPE = M.REMIT_CODE\n" +
            "WHERE H.FININST_BANK_ID IN :fininstBankId\n" +
            "AND H.FILE_NAME = NVL(:fileName,H.FILE_NAME)\n" +
            "AND H.CREATEDATE BETWEEN TO_DATE(:fromDate,'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(:toDate,'YYYY-MM-DD HH24:MI:SS')",nativeQuery = true)
    List<Object> transactionInquiry(@Param("fininstBankId") List<Long> fininstBankId, @Param("fileName") String fileName, @Param("fromDate")String fromDate, @Param("toDate")String toDate);

    @Query(value="SELECT G.GL_ACCOUNT_CODE\n" +
            "FROM TBL_GL_ACCOUNT G\n" +
            "INNER JOIN TBL_FININST_GL F ON G.GL_ACCOUNT_ID = F.GL_ACCOUNT_ID\n" +
            "WHERE F.FININST_BANK_ID = :fininstBankId\n" +
            "AND G.IS_ACTIVE = 'Y'\n" +
            "AND F.IS_ACTIVE = 'Y'",nativeQuery = true)
    String  getFromAccNumber(@Param("fininstBankId") long fininstBankId);
}
