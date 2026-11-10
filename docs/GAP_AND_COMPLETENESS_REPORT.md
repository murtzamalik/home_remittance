# Home Remittance — Gap Analysis & Completeness Report

**Date:** 9 Nov 2026  
**Scope:** Documentation corpus (`Home Remittance System/` + `_doc_extract/`) verified against current source (`HomeRemmitance/`, `Home_Remittance_FrontEnd/`, `db/oracle/`).  
**Constraint:** Code was **not** modified for this report.

---

## Executive verdict

| Lens | Completeness | Meaning |
|------|--------------|---------|
| **A. Full documentation requirements** (legacy Centegy RemitHome + partners + Advance IBFT + SMS + reports + tracker) | **~32%** | Far from a full product replacement |
| **B. Java rewrite intent** (bank ops portal + file/Push ingest + AC/IB/BT) | **~55% structural** | Most modules exist as screens/APIs |
| **C. Production-ready payout path** | **~35%** | IBFT/MT102 hardcodes + stub Oracle procs block go-live |

**Bottom line:** Foundations for an internal remittance ops system are largely present. The system is **not** complete versus historical RemitHome docs, and the **payout path is not production-safe**.

---

## What exists (verified in code)

### Backend — 5 Spring Boot services

| Service | Port | Present capabilities |
|---------|------|----------------------|
| Login | 8080 `/js-hr-login` | LDAP HTTP login → JWT → role menus; logout |
| File Upload | 8081 `/js_hr_fileupload` | Manual Excel upload; directory/auto upload hooks; NACTA/OFAC import; REST Push; SOAP Push (`TransactionRequest`) |
| Financial Institutions | 8082 `/js-hr-fi` | FI CRUD, parser head/detail, remit limits, LOVs |
| User Management | 8083 `/js_hr_um` | Modules, menus, roles, role rights, users, locations |
| Transaction Process | 8084 `/js_hr_transactions` | Process queue, ESB IFT/IBFT calls, park/amend, BT queue, MT102 generate/inquiry, status/batch/single inquiry |

Integration model: shared Oracle `JS_HOMEREMIT` + JWT. Services do **not** call each other over HTTP. ESB calls live only in Transaction Process.

### Frontend — React

Working screens (aligned with `06_seed_menus.sql`): login, user/role/menu/module/rights, FI profile/file setup/GL, remit mode/limit, parser, file upload, transaction process, amendment, NACTA/OFAC import, transaction inquiry, bank transfer (BT), MT102 inquiry, single transaction.

**Stub / incomplete FE screens:** Status Marking, Oversea Bulk Data, File Processing Batch Annex/Own Bank, Dashboard (welcome only).

### Database

- 43 tables + sequences (from JPA)
- Reconstructed procs in `db/oracle/03_procedures.sql` (duplicate / bifurcate / validate / MT102) — **not original bank source**
- Docker Oracle + seed menus for local bring-up

---

## What is missing — and where

### Critical (blocks production)

| Gap | Where | Detail |
|-----|-------|--------|
| **IBFT hardcoded identity** | `JS_HR_Transaction_Process/.../JsHRProcessTransactionPostApi.java` (~172–175) | Fixed `taimoorali`, CNIC `4220188556725`, title `SAMEED AHMED`, Summit Bank — not from transaction row |
| **MT102 / ESB hardcodes** | Same service + `AbstractApi` ESB builders | Hardcoded PAN/accounts/amount fragments; empty `JsTransactionApi` stub |
| **Original Oracle procedures** | `db/oracle/03_procedures.sql` | Stubs matching Java signatures only; business rules may be wrong vs bank |

### High (major product gaps vs docs)

| Gap | Where | Docs expect |
|-----|-------|-------------|
| **Partner Pull APIs** | Absent in all WARs | RIA, Instant Cash, SMT/Sigue, URemit, SpotCash, Speed Remit, AFTAB, RemitONE/Noble, Dex/EPS (+ more from MOMs tie-up list ~21) |
| **Full Centegy Push WS** | File Upload SOAP = upload only | `FetchAccountTitle`, `BalanceInquiry`, `GetBankCodes`, `TransfertoCOTC`, `TransfertoAccount`, `GetTransactionStatus`, AgentID auth, response-code catalog |
| **Advance IBFT acquisition UI** | Not in React FE | Agent/sub-agent maker-checker, title fetch UX, auth queues, limits, shadow GL (legacy Centegy module) |
| **SMS incorporation** | No implementation | CRF-0012015 / FS: insert into JSBL SMS table + retry UI + inquiry |
| **Reporting module** | FE Excel export only on View Status | Crystal Reports pack: outstanding/paid, SOA recon, volume, velocity, regulatory, IBFT success/fail |

### Medium

| Gap | Where | Detail |
|-----|-------|--------|
| Remittance Tracker public API | Missing | SOW 0001-2017: JSON API for public tracker site (backend only) |
| 4-eye / dual authorization | Partial | `authorizeAllowed` on role rights; no dual-auth transaction workflow |
| FX / multi-currency / rates engine | Missing / thin | Features checklist “parameterized FX & countries” |
| CRM unique ID + KYC profiles | Missing | Features checklist + Advance IBFT CRM |
| FE stubs | Listed above | Routed but non-functional |
| NACTA screen bug | `NactaFileImport.js` | Loads OFAC list instead of NACTA |
| Auth hygiene | FE `Auth.js` / logout | Token/nav not fully cleared; refresh/changePassword unused |
| Automated tests | Almost none | 2 empty `contextLoads` only |
| Secrets in source | Multiple `AbstractApi` | Hardcoded SMTP credentials |
| Hardcoded file paths | File Upload | e.g. Windows ALICO path in directory upload helper |

### Adjacent (docs present, not this rewrite)

| Item | Note |
|------|------|
| STP / RTGS / PRISM integrator | Separate product (InfoBits/Centegy); RemitHome is a *source channel* only |
| HC Remit (Hashcash) / CRPL HRS proposals | Replacement RFP material, not current codebase |

---

## Completeness by domain

| Domain | Docs | Backend | Frontend | Est. % |
|--------|------|---------|----------|--------|
| User / role / menu security | Yes | Yes | Yes | 75–85 |
| FI / parser / remit limit | Yes | Yes | Yes | 75–80 |
| Login LDAP + JWT | Yes | Yes | Yes | 70–75 |
| File Excel ingest | Yes | Yes | Yes | 65–75 |
| AML list import (OFAC/NACTA) | Yes | Import APIs | Partial | 50–55 |
| Core AC/IB/BT process UI | Yes | Partial (hardcodes) | Yes | 45–55 |
| MT102 BT file | Yes | Partial | Yes | 45–55 |
| Generic Push upload | Yes | Partial | N/A | 35–45 |
| Full Push WS ops | Yes | Missing | N/A | ~15 |
| Partner Pull (10–22) | Yes | Missing | Missing | ~5 |
| Advance IBFT channel | Yes | Missing | Missing | ~3 |
| SMS / alerts | Yes | Missing | Missing | ~2 |
| Reports / Crystal | Yes | Missing | Excel only | ~8 |
| Remittance Tracker API | Yes | Missing | N/A | ~2 |
| FX / CRM / KYC | Yes | Missing | Missing | ~10–15 |
| 4-eye workflow | Yes | Field only | Missing | ~20 |
| Docker / local DB | — | Present (stubs) | Present | ~70 |
| Tests | — | Missing | Missing | ~2 |

---

## Documentation “minimum requirements” (Sheet4) scorecard

From `New_Solutions__Home_Remittance_Features.xlsx` — bank’s stated minimum:

| # | Requirement | Status |
|---|-------------|--------|
| 1 | Overseas remitting entities management | **Mostly done** (FI module) |
| 2 | OFAC / sanctions AML | **Partial** (list import; weak screening pipeline) |
| 3 | Multi-currency / FX / countries engine | **Missing / thin** |
| 4 | CRM unique ID + KYC | **Missing** |
| 5 | Web acquisition for agents worldwide | **Missing** (Advance IBFT) |
| 6 | Bulk file + SWIFT MT-103 conversion | **Partial** (Excel yes; MT-103 transform no) |
| 7 | Products: Cash / Own / IBFT / PRI / Draft | **Partial** (AC/IB/BT path; COTC/PRI/draft unclear) |
| 8 | 4-eye security | **Mostly missing** |
| 9 | Online status tracking | **Partial** (internal inquiry; no public tracker) |
| 10 | Extensive reporting | **Missing** |
| 11 | Offline + online CBS modes | **Partial** (online ESB path only; not hardened) |
| 12 | Event SMS/Email | **Missing** (email helper unused/hardcoded) |
| 13 | Event financial vouchers | **Missing** |
| 14 | Audit / user / txn logs | **Partial** (activity/request tables exist) |
| 15 | Push & Pull remitting entities | **Push upload only; Pull absent** |

**Sheet4 minimum met:** roughly **4/15 solid**, **5/15 partial**, **6/15 missing** → ~30–35%.

---

## Architecture completeness (rewrite platform)

| Layer | Status |
|-------|--------|
| Microservices split | Done (5 WARs) |
| Shared Oracle schema | Done (with gap columns script) |
| FE ↔ gateway routing | Done in docker/nginx |
| LDAP mock for local | Done |
| ESB client shape | Present but unsafe hardcodes |
| Partner adapters layer | **Not started** |
| Original DB business logic | **Not available in repo** |
| CI / automated test suite | **Not started** |

---

## Recommended priority (planning only — no code changed)

1. Obtain and install **original Oracle `PROC_*` dump**  
2. Fix **IBFT/MT102/ESB** to use real transaction fields (remove hardcodes)  
3. Decide Push strategy: restore **full Centegy Push WS** vs slim REST contract  
4. Build **Pull adapters** per priority partners (MOMs: Instant Cash, RIA, Noble first historically)  
5. Add **SMS insert**, **reports**, **public Tracker API**  
6. Scope **Advance IBFT** only if channel partners still need it  
7. Harden: tests, secret rotation, FE stubs, auth session cleanup  

---

## Method & sources

- Read: `docs/SYSTEM_KNOWLEDGE.md`, `db/README.md`, `_doc_extract/_INDEX.txt`, Features xlsx, MOMs 2017, BRD IBFT, Push API notes, SMS FS/CRF, Tracker SOW, Advance IBFT, partner API docs, STP BRDs (marked adjacent), deployment/risk docs  
- Inventoried: all REST/SOAP controllers, FE routes/pages, DB scripts  
- Spot-verified: IBFT hardcodes, SOAP XSD (`TransactionRequest` only), reconstructed procs  

**Not done in this pass:** live UAT against ESB/LDAP/partners; reading scanned PDFs with empty text extracts (IBFT limit memo / limit approval).

---

## Appendix — corpus vs executable truth

Historical docs describe **Centegy RemitHome** (ASP.NET + SQL Server + Crystal + IIS).  
Executable truth for this workspace is the **Java/Oracle rewrite + React FE**.  

Do not treat STP/RTGS or vendor replacement proposals as missing ticks against the five-WAR codebase unless product scope explicitly includes them.

---

## Update — after bank dump import (9 Nov 2026, afternoon)

**Source:** `JS_HOMEREMIT_LOCAL_06072022.sql` (06-Jul-2022) imported into local Docker Oracle.  
**See also:** `docs/LOCAL_DB_IMPORT_AND_USAGE.md`

### What changed

| Area | Before import | After import |
|------|---------------|--------------|
| Oracle schema | Reconstructed from JPA + gap scripts | **Bank dump DDL** (richer columns) |
| Stored procedures | Reconstructed stubs | **Real bank PROC_* (all VALID)** |
| Reference / ops data | Tiny synthetic seed | **138 FI, 32 parsers, 1134 txn details, 13k NACTA, 2678 users** |
| Login / menus | Synthetic admin + seed menus | **admin@localhost → LDAP 1001 → Admin + 18 menus** |
| Local stack | Up | Still up (UI/APIs healthy; login returns menu tree) |

### Revised completeness scores

| Lens | Before | **Now** | Why |
|------|--------|---------|-----|
| A. Full documentation requirements | ~32% | **~33%** | Partners / Push WS / SMS / reports / Advance IBFT still missing in code |
| B. Java rewrite intent (ops portal + ingest + AC/IB/BT) | ~55% | **~65%** | DB + procs + real sample data unlocked local use of existing screens |
| C. Production-ready payout | ~35% | **~38%** | Procs fixed, but **IBFT/MT102 hardcodes + no real ESB** still block go-live |
| D. **Local demo / learning readiness** (new) | ~40% | **~75%** | You can log in, browse FI/txns/AML, practice inquiry flows |

### Still missing (unchanged by dump)

- Partner Pull APIs (RIA, Instant Cash, …)
- Full Centegy Push WS ops
- Advance IBFT agent UI, SMS, Crystal reports, Tracker API
- IBFT/MT102 hardcoded identity in Java Transaction Process
- FE stubs (Status Marking, Oversea Bulk, FPB)
- Automated tests

### Bottom line (post-import)

System is now **usable locally for exploration and ops-screen practice**.  
It is **not** a complete RemitHome replacement and **not** production-safe for real payouts.
