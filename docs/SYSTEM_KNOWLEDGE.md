# Home Remittance (RemitHome) — System Knowledge Base

Callers: team onboarding / later build work. No runtime code imports this file.
User instruction: `continue`.

Living reference from `HomeRemmitance` backends + `Home Remittance System` docs.

## 1. Product summary

JS Bank inbound **home remittance**: overseas remit companies send payouts (file / Push API / partner SOAP). Bank validates (duplicates, bifurcate AC/IB, AML NACTA/OFAC), then pays:

| Code | Channel | Notes |
|------|---------|-------|
| `AC` | IFT | On-us JS Bank via ESB |
| `IB` | IBFT | Other banks; failures can park as BT |
| `BT` | MT102 | Batch bank-transfer file |

Legacy names in docs: RemitHome, Centegy, Advance IBFT, STP/RTGS.

## 2. Runtime architecture

Five Spring Boot 2.6 / Java 11 services, shared Oracle schema `JS_HOMEREMIT`:

| Service | Port | Context |
|---------|------|---------|
| Login | 8080 | `/js-hr-login` |
| File Upload | 8081 | `/js_hr_fileupload` |
| Financial Institutions | 8082 | `/js-hr-fi` |
| User Management | 8083 | `/js_hr_um` |
| Transaction Process | 8084 | `/js_hr_transactions` |

### Auth

LDAP login → `TBL_USER` → JWT (AES Bearer) → `TBL_AUTH_ACCESS_TOKEN` → menus from role rights.

### Ingest → payout

Excel/Push API → `TBL_TRANS_HEAD`/`DETAIL` → `PROC_CHK_DUPLICATE_TRANS` → `PROC_TRANS_BIFURCATE` → `PROC_TRANS_VALIDATE` → process AC/IB via ESB → BT/MT102 via `PROC_POPULATE_MT102`.

Statuses: `N`, `V`, `D`, `R`, `S`, `FTU`, `FTT`, `BT`, `BTE`, `N1`.

## 3. API prefixes

- Login: `/login`, `/logout`
- Upload: `/remitFileUpload/*`, `/nacta/*`, `/ofac/*`
- FI: `/fi/*`, LOVs
- UMS: `/userManagement/*`, `/lov/*`
- Txn: `/transaction/*`

## 4. Database

See `db/README.md` and `db/oracle/schema_catalog.json`.

- Local Docker Oracle is up: `home_remit_oracle` → `FREEPDB1` / `JS_HOMEREMIT`
- 43 tables, ~40 sequences, `STAN_SEQ`, type `T_MT102_HEAD_ID`
- Procs in `db/oracle/03_procedures.sql` (reconstructed; original source not in repo)
- BT batch number oddly uses `LKP_CITY_SEQ.NEXTVAL`
- Gap columns (native SQL, not on entities) applied via `05_schema_gaps.sql`:
  - `TBL_TRANS_DETAIL.T24_TRANSACTION_DATE`
  - `TBL_MT102_HEAD.FININST_BANK_ID`
  - `TBL_TRANS_FIELDS.COLUMN_NAME`
- Known conflict: `TBL_MENU` has dual JPA shapes (UMS path/icon vs app HOTKEY/LEVEL)

## 5. Documentation corpus (legacy RemitHome)

`Home Remittance System/` (~89 files). Historical Centegy RemitHome (ASP.NET + SQL Server) vs current Java/Oracle rewrite.

| Area | Docs say |
|------|----------|
| Products | COTC/COC, own-account FT, IBFT, PRI→MT102, drafts |
| Channels | Push API, Pull API, Advance IBFT UI, STP/PRISM, file/SWIFT |
| Partners | RIA, Instant Cash, RemitONE, SMT, URemit, Aftab, SpotCash, Speed Remit, … |
| Core bank | T-24 + 1Link IBFT; STP via InfoBits/PRISM |
| Legacy deploy | IIS + SQL Server + Crystal Reports (`JSBHMREMITSSL`) |
| Text extracts | `_doc_extract/` (if present) |

Executable truth for rebuild = **Java services + Oracle**, not the old IIS/SQL Server docs.

## 6. Inter-service note

The five WARs **do not call each other over HTTP**. Shared Oracle + JWT from Login is the integration bus. ESB calls go only from Transaction Process (`js.esb` … `/esb-api/ift*`, `/esb-api/ibft*`).

## 7. Next steps

1. Run services with `--spring.profiles.active=docker`
2. Stub/mock LDAP for local login
3. Prefer original Oracle `PROC_*` dump when available
4. Integrate frontend when FE team delivers
5. Rotate secrets in checked-in properties
