-- =============================================================================
-- Extra Oracle objects required by Java (not always present as @SequenceGenerator)
-- Connect as JS_HOMEREMIT
-- Called by: db/oracle/install.sh after 01_schema.sql
-- =============================================================================

WHENEVER SQLERROR CONTINUE

-- Used by TblTransDetailRepo.getSystemTraceAuditNumber()
CREATE SEQUENCE STAN_SEQ START WITH 100000 INCREMENT BY 1 NOCACHE NOCYCLE;

-- Used if MT102 detail IDs are allocated in PROC_POPULATE_MT102
-- May already exist from 01_schema.sql — ignore ORA-00955
CREATE SEQUENCE TBL_MT102_DETAIL_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- Nested table type returned by PROC_POPULATE_MT102 (Java Types.ARRAY "T_MT102_HEAD_ID")
CREATE OR REPLACE TYPE T_MT102_HEAD_ID AS TABLE OF NUMBER;
/

PROMPT Extra objects created.
