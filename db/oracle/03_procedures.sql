-- =============================================================================
-- Home Remittance stored procedures
-- Reconstructed from Java CallableStatement signatures + observed status codes.
-- Original bank proc source was NOT in the repo. These stubs match the API
-- contract (P_STATUS=1 success) so local/dev can run.
-- Replace with production proc dumps when available.
-- Connect as JS_HOMEREMIT. Called by: db/oracle/install.sh
-- =============================================================================

WHENEVER SQLERROR EXIT SQL.SQLCODE

CREATE OR REPLACE PROCEDURE PROC_CHK_DUPLICATE_TRANS (
    P_TRANS_HEAD_ID IN  NUMBER,
    P_USER_ID       IN  NUMBER,
    P_STATUS        OUT NUMBER,
    P_STATUSDESCR   OUT VARCHAR2
) AS
    V_DUP_COUNT NUMBER := 0;
BEGIN
    UPDATE TBL_TRANS_DETAIL D
       SET D.STATUS = 'D',
           D.STATUS_DESCR = 'Duplicate transaction reference',
           D.LASTUPDATEDATE = SYSDATE,
           D.LASTUPDATEUSER = P_USER_ID,
           D.UPDATEINDEX = NVL(D.UPDATEINDEX, 0) + 1
     WHERE D.TRANS_HEAD_ID = P_TRANS_HEAD_ID
       AND D.TRANSACTION_REFERENCE IS NOT NULL
       AND EXISTS (
            SELECT 1
              FROM TBL_TRANS_DETAIL X
             WHERE X.TRANS_HEAD_ID = D.TRANS_HEAD_ID
               AND X.TRANSACTION_REFERENCE = D.TRANSACTION_REFERENCE
               AND X.TRANS_DETAIL_ID < D.TRANS_DETAIL_ID
       );

    V_DUP_COUNT := SQL%ROWCOUNT;

    UPDATE TBL_TRANS_HEAD
       SET LASTUPDATEDATE = SYSDATE,
           LASTUPDATEUSER = P_USER_ID,
           UPDATEINDEX = NVL(UPDATEINDEX, 0) + 1
     WHERE TRANS_HEAD_ID = P_TRANS_HEAD_ID;

    P_STATUS := 1;
    P_STATUSDESCR := 'Duplicate check complete. Marked=' || V_DUP_COUNT;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        P_STATUS := 99;
        P_STATUSDESCR := SUBSTR(SQLERRM, 1, 500);
END PROC_CHK_DUPLICATE_TRANS;
/

CREATE OR REPLACE PROCEDURE PROC_TRANS_BIFURCATE (
    P_TRANS_HEAD_ID IN  NUMBER,
    P_USER_ID       IN  NUMBER,
    P_STATUS        OUT NUMBER,
    P_STATUSDESCR   OUT VARCHAR2
) AS
    V_AC NUMBER := 0;
    V_IB NUMBER := 0;
BEGIN
    UPDATE TBL_TRANS_DETAIL D
       SET D.TRANS_TYPE = CASE
                WHEN UPPER(NVL(D.BENEFICIARY_BANK_NAME, ' ')) LIKE '%JS BANK%'
                  OR UPPER(NVL(D.BENEFICIARY_BANK_NAME, ' ')) LIKE '%JSBL%'
                  OR UPPER(NVL(D.BENEFICIARY_BANK_NAME, ' ')) LIKE '%JAHANGIR%'
                THEN 'AC'
                ELSE 'IB'
             END,
           D.TRANS_BT = NVL(D.TRANS_BT, 'N'),
           D.TRANS_AMD = NVL(D.TRANS_AMD, 'N'),
           D.STATUS = NVL(D.STATUS, 'N'),
           D.STATUS_DESCR = NVL(D.STATUS_DESCR, 'Bifurcated'),
           D.LASTUPDATEDATE = SYSDATE,
           D.LASTUPDATEUSER = P_USER_ID,
           D.UPDATEINDEX = NVL(D.UPDATEINDEX, 0) + 1
     WHERE D.TRANS_HEAD_ID = P_TRANS_HEAD_ID
       AND NVL(D.STATUS, 'X') NOT IN ('D');

    SELECT SUM(CASE WHEN TRANS_TYPE = 'AC' THEN 1 ELSE 0 END),
           SUM(CASE WHEN TRANS_TYPE = 'IB' THEN 1 ELSE 0 END)
      INTO V_AC, V_IB
      FROM TBL_TRANS_DETAIL
     WHERE TRANS_HEAD_ID = P_TRANS_HEAD_ID;

    P_STATUS := 1;
    P_STATUSDESCR := 'Bifurcation complete. AC=' || NVL(V_AC,0) || ' IB=' || NVL(V_IB,0);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        P_STATUS := 99;
        P_STATUSDESCR := SUBSTR(SQLERRM, 1, 500);
END PROC_TRANS_BIFURCATE;
/

CREATE OR REPLACE PROCEDURE PROC_TRANS_VALIDATE (
    P_TRANS_HEAD_ID IN  NUMBER,
    P_USER_ID       IN  NUMBER,
    P_STATUS        OUT NUMBER,
    P_STATUSDESCR   OUT VARCHAR2
) AS
    V_HIT NUMBER := 0;
BEGIN
    UPDATE TBL_TRANS_DETAIL D
       SET D.STATUS = 'R',
           D.STATUS_DESCR = 'AML/NACTA/OFAC name hit',
           D.LASTUPDATEDATE = SYSDATE,
           D.LASTUPDATEUSER = P_USER_ID,
           D.UPDATEINDEX = NVL(D.UPDATEINDEX, 0) + 1
     WHERE D.TRANS_HEAD_ID = P_TRANS_HEAD_ID
       AND NVL(D.STATUS, 'X') NOT IN ('D')
       AND EXISTS (
            SELECT 1
              FROM TBL_NACTA_LIST N
             WHERE UPPER(NVL(N.NAME, ' ')) <> ' '
               AND (
                    UPPER(NVL(D.BENEFICIARY_NAME, ' ')) LIKE '%' || UPPER(N.NAME) || '%'
                 OR UPPER(NVL(D.REMITTER_NAME, ' ')) LIKE '%' || UPPER(N.NAME) || '%'
               )
       );

    V_HIT := SQL%ROWCOUNT;

    UPDATE TBL_TRANS_DETAIL D
       SET D.STATUS = 'V',
           D.STATUS_DESCR = 'Validated',
           D.TRANS_BT = NVL(D.TRANS_BT, 'N'),
           D.LASTUPDATEDATE = SYSDATE,
           D.LASTUPDATEUSER = P_USER_ID,
           D.UPDATEINDEX = NVL(D.UPDATEINDEX, 0) + 1
     WHERE D.TRANS_HEAD_ID = P_TRANS_HEAD_ID
       AND NVL(D.STATUS, 'X') NOT IN ('D', 'R');

    UPDATE TBL_TRANS_HEAD
       SET STATUS = 'V',
           LASTUPDATEDATE = SYSDATE,
           LASTUPDATEUSER = P_USER_ID
     WHERE TRANS_HEAD_ID = P_TRANS_HEAD_ID;

    P_STATUS := 1;
    P_STATUSDESCR := 'Validation complete. AML hits=' || V_HIT;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        P_STATUS := 99;
        P_STATUSDESCR := SUBSTR(SQLERRM, 1, 500);
END PROC_TRANS_VALIDATE;
/

CREATE OR REPLACE PROCEDURE PROC_BIFURCATE_VALIDATE (
    P_TRANS_DETAIL_ID IN  NUMBER,
    P_USER_ID         IN  NUMBER,
    P_STATUS          OUT NUMBER,
    P_STATUSDESCR     OUT VARCHAR2
) AS
    V_HEAD_ID NUMBER;
BEGIN
    SELECT TRANS_HEAD_ID INTO V_HEAD_ID
      FROM TBL_TRANS_DETAIL
     WHERE TRANS_DETAIL_ID = P_TRANS_DETAIL_ID;

    UPDATE TBL_TRANS_DETAIL D
       SET D.TRANS_TYPE = CASE
                WHEN UPPER(NVL(D.BENEFICIARY_BANK_NAME, ' ')) LIKE '%JS BANK%'
                  OR UPPER(NVL(D.BENEFICIARY_BANK_NAME, ' ')) LIKE '%JSBL%'
                  OR UPPER(NVL(D.BENEFICIARY_BANK_NAME, ' ')) LIKE '%JAHANGIR%'
                THEN 'AC' ELSE 'IB' END,
           D.TRANS_AMD = 'N',
           D.TRANS_BT = 'N',
           D.STATUS = 'V',
           D.STATUS_DESCR = 'Amended and re-validated',
           D.LASTUPDATEDATE = SYSDATE,
           D.LASTUPDATEUSER = P_USER_ID,
           D.UPDATEINDEX = NVL(D.UPDATEINDEX, 0) + 1
     WHERE D.TRANS_DETAIL_ID = P_TRANS_DETAIL_ID;

    UPDATE TBL_TRANS_DETAIL D
       SET D.STATUS = 'R',
           D.STATUS_DESCR = 'AML/NACTA/OFAC name hit after amendment'
     WHERE D.TRANS_DETAIL_ID = P_TRANS_DETAIL_ID
       AND EXISTS (
            SELECT 1 FROM TBL_NACTA_LIST N
             WHERE UPPER(NVL(N.NAME, ' ')) <> ' '
               AND (
                    UPPER(NVL(D.BENEFICIARY_NAME, ' ')) LIKE '%' || UPPER(N.NAME) || '%'
                 OR UPPER(NVL(D.REMITTER_NAME, ' ')) LIKE '%' || UPPER(N.NAME) || '%'
               )
       );

    P_STATUS := 1;
    P_STATUSDESCR := 'Detail ' || P_TRANS_DETAIL_ID || ' bifurcated/validated (head=' || V_HEAD_ID || ')';
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        P_STATUS := 99;
        P_STATUSDESCR := 'TRANS_DETAIL_ID not found: ' || P_TRANS_DETAIL_ID;
    WHEN OTHERS THEN
        ROLLBACK;
        P_STATUS := 99;
        P_STATUSDESCR := SUBSTR(SQLERRM, 1, 500);
END PROC_BIFURCATE_VALIDATE;
/

CREATE OR REPLACE PROCEDURE PROC_POPULATE_MT102 (
    P_BATCH_NO      IN  NUMBER,
    P_USER_ID       IN  NUMBER,
    P_MT102_HEAD_ID OUT T_MT102_HEAD_ID,
    P_STATUS        OUT NUMBER,
    P_STATUSDESCR   OUT VARCHAR2,
    P_HEAD_IDS      OUT VARCHAR2
) AS
    V_HEAD_ID   NUMBER;
    V_DET_ID    NUMBER;
    V_COUNT     NUMBER;
    V_AMOUNT    NUMBER;
    V_HEAD_LIST VARCHAR2(4000) := NULL;
BEGIN
    P_MT102_HEAD_ID := T_MT102_HEAD_ID();

    FOR R IN (
        SELECT NVL(D.BENEFICIARY_BANK_NAME, 'UNKNOWN') AS BANK_NAME
          FROM TBL_MT102_TEMP T
          JOIN TBL_TRANS_DETAIL D ON D.TRANS_DETAIL_ID = T.TRANS_DETAIL_ID
         WHERE T.BATCH_NO = P_BATCH_NO
           AND NVL(T.STATUS, 'S') = 'S'
         GROUP BY NVL(D.BENEFICIARY_BANK_NAME, 'UNKNOWN')
    ) LOOP
        SELECT TBL_MT102_HEAD_SEQ.NEXTVAL INTO V_HEAD_ID FROM DUAL;

        SELECT COUNT(*), NVL(SUM(D.AMOUNT), 0)
          INTO V_COUNT, V_AMOUNT
          FROM TBL_MT102_TEMP T
          JOIN TBL_TRANS_DETAIL D ON D.TRANS_DETAIL_ID = T.TRANS_DETAIL_ID
         WHERE T.BATCH_NO = P_BATCH_NO
           AND NVL(T.STATUS, 'S') = 'S'
           AND NVL(D.BENEFICIARY_BANK_NAME, 'UNKNOWN') = R.BANK_NAME;

        INSERT INTO TBL_MT102_HEAD (
            MT102_HEAD_ID, BANK_NAME, CREATEDATE, CREATEUSER,
            FILE_REFERENCE_NO, NO_OF_TRANSACTIONS, STATUS, STATUSDESCR,
            TOTAL_AMOUNT, UPDATEINDEX
        ) VALUES (
            V_HEAD_ID, R.BANK_NAME, SYSDATE, P_USER_ID,
            'MT102-' || P_BATCH_NO || '-' || V_HEAD_ID, V_COUNT, 'S', 'Generated',
            V_AMOUNT, 1
        );

        FOR D IN (
            SELECT D.*
              FROM TBL_MT102_TEMP T
              JOIN TBL_TRANS_DETAIL D ON D.TRANS_DETAIL_ID = T.TRANS_DETAIL_ID
             WHERE T.BATCH_NO = P_BATCH_NO
               AND NVL(T.STATUS, 'S') = 'S'
               AND NVL(D.BENEFICIARY_BANK_NAME, 'UNKNOWN') = R.BANK_NAME
        ) LOOP
            SELECT TBL_MT102_DETAIL_SEQ.NEXTVAL INTO V_DET_ID FROM DUAL;
            INSERT INTO TBL_MT102_DETAIL (
                MT102_DETAIL_ID, AMOUNT, BENEFICIARY_ACCOUNT_NO, BENEFICIARY_ACCOUNT_TITLE,
                BENEFICIARY_BANK_NAME, BENEFICIARY_BRANCH_ADD1, BENEFICIARY_BRANCH_ADD2,
                BENEFICIARY_BRANCH_CODE, BENEFICIARY_CNIC, BENEFICIARY_NAME,
                BENEFICIARY_OTHER_CONTACT, BIC_CODE, BENEFICIARY_BANK_ACCOUNT,
                CREATEDATE, CREATEUSER, REMITTER_ADDRESS, REMITTER_COUNTRY,
                REMITTER_NAME, REMITTER_NUMBER, TRANSACTION_REFERENCE, UPDATEINDEX,
                MT102_HEAD_ID
            ) VALUES (
                V_DET_ID, D.AMOUNT, D.BENEFICIARY_ACCOUNT_NO, D.BENEFICIARY_ACCOUNT_TITLE,
                D.BENEFICIARY_BANK_NAME, D.BENEFICIARY_BRANCH_ADD1, D.BENEFICIARY_BRANCH_ADD2,
                D.BENEFICIARY_BRANCH_CODE, D.BENEFICIARY_CNIC, D.BENEFICIARY_NAME,
                D.BENEFICIARY_OTHER_CONTACT, NULL, NULL,
                SYSDATE, P_USER_ID, D.REMITTER_ADDRESS, D.REMITTER_COUNTRY,
                D.REMITTER_NAME, D.REMITTER_NUMBER, D.TRANSACTION_REFERENCE, 1,
                V_HEAD_ID
            );
        END LOOP;

        P_MT102_HEAD_ID.EXTEND;
        P_MT102_HEAD_ID(P_MT102_HEAD_ID.COUNT) := V_HEAD_ID;

        IF V_HEAD_LIST IS NULL THEN
            V_HEAD_LIST := TO_CHAR(V_HEAD_ID);
        ELSE
            V_HEAD_LIST := V_HEAD_LIST || ',' || TO_CHAR(V_HEAD_ID);
        END IF;
    END LOOP;

    IF V_HEAD_LIST IS NULL THEN
        P_STATUS := 99;
        P_STATUSDESCR := 'No MT102 temp rows for batch ' || P_BATCH_NO;
        P_HEAD_IDS := NULL;
        ROLLBACK;
        RETURN;
    END IF;

    P_STATUS := 1;
    P_STATUSDESCR := 'MT102 populated for batch ' || P_BATCH_NO;
    P_HEAD_IDS := V_HEAD_LIST;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        P_STATUS := 99;
        P_STATUSDESCR := SUBSTR(SQLERRM, 1, 500);
        P_HEAD_IDS := NULL;
END PROC_POPULATE_MT102;
/

PROMPT Procedures created.
