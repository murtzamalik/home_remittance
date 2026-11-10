# Home Remittance — Oracle database

Callers: developers / install.sh docs. User instruction: `continue`.
Generated from JPA entities under `HomeRemmitance/*` plus reconstructed stored procedures matching Java `CallableStatement` contracts.

## Contents (`db/oracle/`)

| Script | Purpose |
|--------|---------|
| `00_create_user.sql` | Create `JS_HOMEREMIT` user |
| `01_schema.sql` | 43 tables + sequences |
| `02_extra_objects.sql` | `STAN_SEQ`, `TBL_MT102_DETAIL_SEQ`, type `T_MT102_HEAD_ID` |
| `03_procedures.sql` | Duplicate / bifurcate / validate / MT102 procs |
| `04_seed.sql` | Synthetic statuses, remit modes, demo FI/user |
| `05_schema_gaps.sql` | Columns used in native SQL but missing from JPA |
| `install.sh` | Runs the above in order |
| `schema_catalog.json` | Machine-readable table/column catalog |

## Start local Oracle

1. Start **Docker Desktop**.
2. From repo root: `docker compose up -d`
3. Wait until healthy, then:

```bash
cd db/oracle && SYS_PASSWORD=OraclePassword123 ./install.sh
```

Or inside the container:

```bash
docker exec -it home_remit_oracle bash -lc '
  sqlplus -S JS_HOMEREMIT/hr@//localhost:1521/FREEPDB1 @/opt/homeremit-sql/01_schema.sql
  sqlplus -S JS_HOMEREMIT/hr@//localhost:1521/FREEPDB1 @/opt/homeremit-sql/02_extra_objects.sql
  sqlplus -S JS_HOMEREMIT/hr@//localhost:1521/FREEPDB1 @/opt/homeremit-sql/03_procedures.sql
  sqlplus -S JS_HOMEREMIT/hr@//localhost:1521/FREEPDB1 @/opt/homeremit-sql/04_seed.sql
'
```

(If compose `APP_USER` already created the user, skip `00_create_user.sql`.)

## JDBC for services

```
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/FREEPDB1
spring.datasource.username=JS_HOMEREMIT
spring.datasource.password=hr
```

| Service | Port | Context |
|---------|------|---------|
| Login | 8080 | `/js-hr-login` |
| File Upload | 8081 | `/js_hr_fileupload` |
| FI | 8082 | `/js-hr-fi` |
| User Mgmt | 8083 | `/js_hr_um` |
| Transactions | 8084 | `/js_hr_transactions` |

## Caveats

- Procedures are **reconstructed stubs**, not original bank source — replace when you have a dump.
- Column sizes are inferred; tighten against a live catalog if available.
- Rotate passwords in legacy `application-*.properties` before real deploy.
