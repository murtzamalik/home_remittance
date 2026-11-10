-- =============================================================================
-- Schema gaps: native @Query SQL columns missing from JPA entities
-- Callers: install.sh after 04_seed.sql; docker apply on live DB
-- Columns: TBL_TRANS_DETAIL.T24_TRANSACTION_DATE, TBL_MT102_HEAD.FININST_BANK_ID,
--          TBL_TRANS_FIELDS.COLUMN_NAME
-- User instruction (follow-up): Perform any necessary follow-up actions...
-- =============================================================================

WHENEVER SQLERROR CONTINUE

ALTER TABLE TBL_TRANS_DETAIL ADD T24_TRANSACTION_DATE DATE;

ALTER TABLE TBL_MT102_HEAD ADD FININST_BANK_ID NUMBER(19);

ALTER TABLE TBL_TRANS_FIELDS ADD COLUMN_NAME VARCHAR2(500);

BEGIN
  EXECUTE IMMEDIATE
    'UPDATE TBL_TRANS_FIELDS SET COLUMN_NAME = NAME WHERE COLUMN_NAME IS NULL AND NAME IS NOT NULL';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

PROMPT Schema gaps applied.
