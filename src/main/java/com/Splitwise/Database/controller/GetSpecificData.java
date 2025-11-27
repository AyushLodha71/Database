package com.Splitwise.Database.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * Controller to return a single column's values from a table as a String[]
 * (one value per matching row). Endpoints are provided per-database (db1..db8).
 *
 * Request parameters:
 * - val: the column name to select
 * - table: the target table name
 * - other query parameters are treated as WHERE filters (column=value)
 *
 * Note: Identifiers are wrapped in backticks, but inputs are otherwise not
 * validated. In production you should whitelist/validate table and column
 * names to avoid SQL injection risks.
 */
public class GetSpecificData {

    @Autowired @Qualifier("db1JdbcTemplate") private JdbcTemplate db1;
    @Autowired @Qualifier("db2JdbcTemplate") private JdbcTemplate db2;
    @Autowired @Qualifier("db3JdbcTemplate") private JdbcTemplate db3;
    @Autowired @Qualifier("db4JdbcTemplate") private JdbcTemplate db4;
    @Autowired @Qualifier("db5JdbcTemplate") private JdbcTemplate db5;
    @Autowired @Qualifier("db6JdbcTemplate") private JdbcTemplate db6;
    @Autowired @Qualifier("db7JdbcTemplate") private JdbcTemplate db7;
    @Autowired @Qualifier("db8JdbcTemplate") private JdbcTemplate db8;

    // Core reusable method
    private String[] getData(JdbcTemplate db, String val, String table, Map<String, String> params) {
        params.remove("table");
        params.remove("val");
        
        StringBuilder sql = new StringBuilder("SELECT `" + val + "` FROM `" + table + "`");
        Object[] values = new Object[params.size()];

        if (!params.isEmpty()) {
            sql.append(" WHERE ");
            int i = 0;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (i > 0) sql.append(" AND ");
                sql.append("`").append(entry.getKey()).append("` = ?");
                values[i++] = entry.getValue();
            }
        }

        List<Map<String, Object>> result = db.queryForList(sql.toString(), values);

        if (result.isEmpty()) return new String[0]; // no rows found
        
        // Extract just the values from the result maps
        return result.stream()
                     .map(row -> String.valueOf(row.get(val)))
                     .toArray(String[]::new);
    }

    // Map routes to databases
    @GetMapping("/db1/GetSpecificData") public String[] getDb1(@RequestParam String val, @RequestParam String table, @RequestParam Map<String, String> vals) { return getData(db1, val, table, vals); }
    @GetMapping("/db2/GetSpecificData") public String[] getDb2(@RequestParam String val, @RequestParam String table, @RequestParam Map<String, String> vals) { return getData(db2, val, table, vals); }
    @GetMapping("/db3/GetSpecificData") public String[] getDb3(@RequestParam String val, @RequestParam String table, @RequestParam Map<String, String> vals) { return getData(db3, val, table, vals); }
    @GetMapping("/db4/GetSpecificData") public String[] getDb4(@RequestParam String val, @RequestParam String table, @RequestParam Map<String, String> vals) { return getData(db4, val, table, vals); }
    @GetMapping("/db5/GetSpecificData") public String[] getDb5(@RequestParam String val, @RequestParam String table, @RequestParam Map<String, String> vals) { return getData(db5, val, table, vals); }
    @GetMapping("/db6/GetSpecificData") public String[] getDb6(@RequestParam String val, @RequestParam String table, @RequestParam Map<String, String> vals) { return getData(db6, val, table, vals); }
    @GetMapping("/db7/GetSpecificData") public String[] getDb7(@RequestParam String val, @RequestParam String table, @RequestParam Map<String, String> vals) { return getData(db7, val, table, vals); }
    @GetMapping("/db8/GetSpecificData") public String[] getDb8(@RequestParam String val, @RequestParam String table, @RequestParam Map<String, String> vals) { return getData(db8, val, table, vals); }
}
