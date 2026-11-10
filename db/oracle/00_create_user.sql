-- =============================================================================
-- Create Oracle schema user for Home Remittance
-- Run as SYS/SYSTEM (or container admin on XE/Free)
-- Called by: db/oracle/install.sh, docker entrypoint init
-- =============================================================================

WHENEVER SQLERROR CONTINUE

CREATE USER JS_HOMEREMIT IDENTIFIED BY hr
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP
  QUOTA UNLIMITED ON USERS;

GRANT CONNECT, RESOURCE TO JS_HOMEREMIT;
GRANT CREATE VIEW, CREATE PROCEDURE, CREATE SEQUENCE, CREATE TYPE, CREATE TRIGGER TO JS_HOMEREMIT;
GRANT CREATE SESSION TO JS_HOMEREMIT;
GRANT UNLIMITED TABLESPACE TO JS_HOMEREMIT;

PROMPT User JS_HOMEREMIT created.
