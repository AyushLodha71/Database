# Database HTTP Utilities

This Spring Boot project (the second part of the [Splitwise Application](https://github.com/AyushLodha71/Splitwise)) is
responsible for connecting Splitwise to MySQL and exposes simple HTTP
endpoints to operate on multiple MySQL databases using `JdbcTemplate`.
It is intended as an internal/admin utility for quick operations (listing
tables, selecting rows, inserting, DDL, etc.).

## Quick overview

- Java: tested with Java 17+ (project compiles with Java 24 in developer environment)
- Build: Maven wrapper (`./mvnw`)
- Spring Boot version: 3.5.6
- Uses `spring-boot-starter-jdbc` + MySQL driver
- Multi-datasource setup (8 datasources) with `JdbcTemplate` beans named
  `db1JdbcTemplate` .. `db8JdbcTemplate`.
- Beans are `@Lazy` initialized so the application can start when some DBs are down.

## Important files

- `src/main/java/com/Splitwise/Database/MultiDBConfig.java` — configures DataSourceProperties,
  DataSource and JdbcTemplate beans (db1..db8).
- `src/main/java/com/Splitwise/Database/DatabaseApplication.java` — Spring Boot main class.
- `src/main/java/com/Splitwise/Database/controller/` — controllers exposing endpoints:
  - `GetTables`, `GetTableData`, `GetRowData`, `GetSpecificData`, `InsertData`,
    `CreateTable`, `AddColumn`, `DeleteColumn`, `DeleteRowData`, `UpdateData`, `GetCount`.

## How to run

1. Configure datasources in `src/main/resources/application.properties` (or the
   environment) using names like:

```
spring.datasource.sa-checkamountspentfolder.url=jdbc:mysql://host:3306/dbname
spring.datasource.sa-checkamountspentfolder.username=...
spring.datasource.sa-checkamountspentfolder.password=...

# repeat for keys used in MultiDBConfig:
# sa-credentials, sa-groupnames, sa-groups, sa-paymenthistory,
# sa-pendingamount, sa-personalfolders, sa-transactiondetails
```

2. Build jar (skip tests if you prefer):

```bash
./mvnw clean package -DskipTests
```

3. Run with Maven (development):

```bash
./mvnw spring-boot:run
```

or run the produced jar:

```bash
java -jar target/Database-0.0.1-SNAPSHOT.jar
```

If port 8080 is in use, either stop the other process or set `server.port` in
`application.properties`.

## Endpoint patterns & examples

All controllers expose one endpoint per configured database. Replace `dbN` with
`db1..db8`.

- Get tables:
  - GET `/db1/groups`
  - Returns: `List<Map<String,Object>>` as returned by `SHOW TABLES`.

- Get all rows from a table:
  - GET `/db1/GetTableData?table=Users`
  - Returns: `List<Map<String,Object>>` (each map is `column -> value`).

- Get rows with filters (returns 2D String array with header):
  - GET `/db1/GetRowData?table=Users&status=active`
  - Returns: `String[][]` where first row is column names, following rows are values.

- Get specific column values as String[] (one value per matching row):
  - GET `/db3/GetSpecificData?val=group_name&table=GroupNames&group_code=fvZ8x52`
  - Returns: `String[]` like `["value1","value2"]`.

- Insert (simple fragment-based):
  - GET `/db1/InsertData?table=Users&params=(name,email)&info=('bob','b@example.com')`
  - Returns: HTTP 200 on success (void body).

- Create table (DDL):
  - GET `/db1/CreateTable?table=MyTable&columns=id INT PRIMARY KEY, name VARCHAR(255)`

- Add column (safe-ish; column name validated to [A-Za-z0-9_]):
  - GET `/db1/AddColumn?table=Users&uname=extra_balance`
  - Returns plain text success/failure message.

- Delete column:
  - GET `/db1/DeleteColumn?table=Users&uname=extra_balance`

- Delete rows:
  - GET `/db1/DeleteRowData?table=Users&id=5`

- Update rows:
  - GET `/db1/UpdateData?table=Users&where=id=5&name=Alice` — `where` is a
    raw fragment used as the WHERE clause and `name` will be used for SET.

- Count rows:
  - GET `/db1/GetCount?table=Users` — returns integer count.

Example curl (GetSpecificData):

```bash
curl "http://localhost:8080/db3/GetSpecificData?val=group_name&table=GroupNames&group_code=fvZ8x52"
```

## SQL & security notes

- Table and column identifiers are wrapped in backticks (`` ` ``) before being
  embedded in SQL to handle MySQL reserved keywords (e.g. `Groups`).
- Parameters used in WHERE clauses are passed as prepared-statement parameters
  (placeholders) except for some helper methods that accept raw fragments
  (e.g. `where` in `UpdateData`).
- Many endpoints build SQL dynamically. This is convenient but risky: a
  malicious user could drive SQL injection if these endpoints are exposed.
  Recommended mitigations:
  - Whitelist allowed table and column names.
  - Avoid accepting raw SQL fragments like `where` from untrusted users.
  - Add authentication/authorization to the endpoints.

## Lazy initialization rationale

`MultiDBConfig` marks datasource and JdbcTemplate beans with `@Lazy`. That
allows the application context to start even if one or more databases are
unavailable. Each datasource will attempt to connect only when first used by a
controller method.

## What I changed recently

- Added Javadocs and comments across many controllers and configuration
  classes to explain purpose and behavior (see commits/changes in the repo).
- Fixed ambiguous mapping conflict between `GetRowData` and `GetSpecificData` by
  renaming the endpoints so both can coexist.
- Fixed SQL issues related to MySQL reserved keywords by wrapping identifiers
  with backticks.

## Troubleshooting

- Port already in use: find and stop the process using port 8080:

```bash
lsof -ti:8080 | xargs kill -9
```

- If a request returns HTTP 500: check logs printed to the console or the
  `app.log` if you redirected output. Typical causes: missing request
  parameters, malformed SQL fragments or DB connectivity problems.

## Next improvements (suggested)

- Tighten input validation (whitelists for table/column names).
- Replace dynamic fragments with strongly-typed parameterization where possible.
- Add authentication on admin endpoints.
- Add integration tests for each endpoint (mock datasources or run against a
  test DB).

## Contact / Notes

If you want, I can:
- Add endpoint-level Javadoc for each mapping.
- Harden SQL parameter handling and add a name whitelist.
- Produce a small Postman collection or a set of curl examples for all endpoints.

---
Generated on: 2025-11-27
