# Local DB import (bank dump) + how to use the system

**Dump:** `JS_HOMEREMIT_LOCAL_06072022.sql` (created Wed 06-Jul-2022)  
**Imported into:** Docker Oracle `home_remit_oracle` / `JS_HOMEREMIT@FREEPDB1`  
**Sanitized copy:** `db/oracle/import/JS_HOMEREMIT_LOCAL_06072022_sanitized.sql`  
**Login patch:** `db/oracle/import/99_local_login_patch.sql`

## Dump vs source code — are they the same?

| Area | Verdict |
|------|---------|
| Tables | **Mostly same** — 43 tables each. Dump has `TBL_TRANS_FILE`; local reconstructed schema had `TBL_USER_ROLE` instead. |
| Columns | **Dump is richer / more accurate** for a few columns Java already uses via native SQL (`T24_TRANSACTION_DATE`, `TBL_MT102_HEAD.FININST_BANK_ID`, `TBL_TRANS_FIELDS.COLUMN_NAME`, `BIC_CODE`, `BENEFICIARY_BANK_ACCOUNT`). |
| Stored procedures | **Dump wins** — real `PROC_*` from bank. Local `03_procedures.sql` was reconstructed stubs. |
| App menus (`TBL_MENU` path/icon style) | **Aligned with React FE** (14 menus from dump + 4 we added for missing FE routes). |
| Age | Dump = **Jul 2022**. Java source looks contemporary with that dump (same menu paths). Neither is “newer” in a conflicting way for core schema — dump is the better DB truth. |

**Summary:** Schema and procs from the dump match this Java rewrite well. Prefer the dump for Oracle. Code is not “ahead” of this dump on DB objects; dump is ahead of our previous stub procs.

## What was imported (useful data)

| Object | Count (approx) | Use |
|--------|----------------|-----|
| Users | 2678 | Historical; local login uses USER_ID=1 / LDAP `1001` |
| FI banks | 138 | Profiles / LOVs |
| Locations | 370 | User location LOVs |
| GL accounts | ~1549 | FI GL mapping |
| Remit modes | includes AC / IB / BT | Processing modes |
| Parsers | 32 heads / 57 details | File upload mapping |
| Trans heads / details | 83 / 1134 | Inquiry / process practice data (statuses V,S,BT,D,N,…) |
| NACTA list | 13017 | AML screens |
| Menus + Admin rights | 18 menus | Full React nav for role 21 |
| Procedures | 5 real PROC_* | Duplicate / bifurcate / validate / MT102 |

Removed from dump before import: **DATABASE LINK** to `20.212.202.66` (external).

## How to open / use the system

1. Ensure stack is up: `docker compose up -d` (already running if you imported).
2. Open UI: **http://localhost**
3. Login:
   - **Email:** `admin@localhost` (must look like an email)
   - **Password:** anything (LDAP mock accepts all; it always returns empcode `1001`)
4. After login you should see modules: User Management, Profile, Operational Process, Capping/Parser, Transaction.

### Suggested first clicks

1. **Profile** — browse remitting companies / banks (138 rows).  
2. **Upload File** — needs a remitting company + parser; try after picking an FI with a parser.  
3. **Transaction Inquiry / Single Transaction** — explore imported 1134 details.  
4. **Transaction Process** — queue for status `V` (validated) rows; ESB calls will fail locally unless ESB is mocked (hardcoded IBFT test values still in code — do not treat payouts as real).  
5. **Import NACTA / OFAC** — list data already present for NACTA.  
6. **Bank Transfer / MT102 Inquiry** — BT-status sample rows exist.

### Direct API ports (optional)

| Service | URL |
|---------|-----|
| Login | http://localhost:8080/js-hr-login |
| File upload | http://localhost:8081/js_hr_fileupload |
| FI | http://localhost:8082/js-hr-fi |
| User mgmt | http://localhost:8083/js_hr_um |
| Transactions | http://localhost:8084/js_hr_transactions |
| LDAP mock | http://localhost:8089 |
| Oracle | `localhost:1521/FREEPDB1` user `JS_HOMEREMIT` / `hr` |

## Re-import later

```bash
docker exec -i home_remit_oracle bash -lc \
  'sqlplus -S JS_HOMEREMIT/hr@//localhost:1521/FREEPDB1 @/opt/homeremit-sql/import/JS_HOMEREMIT_LOCAL_06072022_sanitized.sql'
docker exec -i home_remit_oracle bash -lc \
  'sqlplus -S JS_HOMEREMIT/hr@//localhost:1521/FREEPDB1 @/opt/homeremit-sql/import/99_local_login_patch.sql'
# Then re-add missing menus if needed (see import notes) and:
# ALTER PROCEDURE ... COMPILE; for all five PROC_*
```

Bank procs also saved as `db/oracle/03b_procedures_from_bank_dump.sql` for future clean installs.
