#!/usr/bin/env bash
# Applies Home Remittance Oracle schema in order.
# Callers: manual local setup; documented in db/README.md
# User instruction: "continue"
# Usage:
#   ORACLE_HOST=localhost ORACLE_PORT=1521 ORACLE_SERVICE=FREEPDB1 \
#   SYS_PASSWORD=OraclePassword123 HR_PASSWORD=hr ./install.sh
set -euo pipefail
ROOT="$(cd "$(dirname "$0")" && pwd)"

ORACLE_HOST="${ORACLE_HOST:-localhost}"
ORACLE_PORT="${ORACLE_PORT:-1521}"
ORACLE_SERVICE="${ORACLE_SERVICE:-FREEPDB1}"
SYS_USER="${SYS_USER:-sys}"
SYS_PASSWORD="${SYS_PASSWORD:-OraclePassword123}"
HR_USER="${HR_USER:-JS_HOMEREMIT}"
HR_PASSWORD="${HR_PASSWORD:-hr}"

SQLPLUS_BIN="${SQLPLUS_BIN:-sqlplus}"
CONN_SYS="${SYS_USER}/${SYS_PASSWORD}@//${ORACLE_HOST}:${ORACLE_PORT}/${ORACLE_SERVICE} AS SYSDBA"
CONN_HR="${HR_USER}/${HR_PASSWORD}@//${ORACLE_HOST}:${ORACLE_PORT}/${ORACLE_SERVICE}"

echo "==> Creating user (as SYS)"
"$SQLPLUS_BIN" -S "$CONN_SYS" @"$ROOT/00_create_user.sql"

echo "==> Schema DDL (as $HR_USER)"
"$SQLPLUS_BIN" -S "$CONN_HR" @"$ROOT/01_schema.sql"

echo "==> Extra objects"
"$SQLPLUS_BIN" -S "$CONN_HR" @"$ROOT/02_extra_objects.sql"

echo "==> Procedures"
"$SQLPLUS_BIN" -S "$CONN_HR" @"$ROOT/03_procedures.sql"

echo "==> Seed"
"$SQLPLUS_BIN" -S "$CONN_HR" @"$ROOT/04_seed.sql"

echo "==> Schema gaps (native-SQL columns missing from entities)"
"$SQLPLUS_BIN" -S "$CONN_HR" @"$ROOT/05_schema_gaps.sql"

echo "==> Menu seed"
"$SQLPLUS_BIN" -S "$CONN_HR" @"$ROOT/06_seed_menus.sql"

echo "==> Done. JDBC: jdbc:oracle:thin:@${ORACLE_HOST}:${ORACLE_PORT}/${ORACLE_SERVICE} user=${HR_USER}"
