package com.Splitwise.Database.controller;

import java.util.ArrayList;
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
 * Controller exposing endpoints to retrieve rows from database tables.
 *
 * Each endpoint maps to a specific database (db1..db8). Methods accept a
 * {@code table} parameter and optional query parameters which are treated as
 * WHERE filters. Responses are converted to a 2-dimensional String array
 * where the first row contains column names and subsequent rows contain
 * values.
 *
 * Security note: This controller builds SQL dynamically and only uses simple
 * backtick escaping for identifiers. Consider validating {@code table} and
 * column names against a whitelist in production to avoid injection risks.
 */
public class GetRowData {

    @Autowired @Qualifier("db1JdbcTemplate") private JdbcTemplate db1;
    @Autowired @Qualifier("db2JdbcTemplate") private JdbcTemplate db2;
    @Autowired @Qualifier("db3JdbcTemplate") private JdbcTemplate db3;
    @Autowired @Qualifier("db4JdbcTemplate") private JdbcTemplate db4;
    @Autowired @Qualifier("db5JdbcTemplate") private JdbcTemplate db5;
    @Autowired @Qualifier("db6JdbcTemplate") private JdbcTemplate db6;
    @Autowired @Qualifier("db7JdbcTemplate") private JdbcTemplate db7;
    @Autowired @Qualifier("db8JdbcTemplate") private JdbcTemplate db8;

    // Core logic: Get data as List<Map<String, Object>>
    /**
     * Build and execute a SELECT query against the supplied JdbcTemplate.
     *
     * @param db     the JdbcTemplate for the target database
     * @param table  table name (identifier will be wrapped in backticks)
     * @param params map of column -> value filters (will be used in WHERE)
     * @return list of rows as maps where each map is column->value
     */
    private List<Map<String, Object>> getData(JdbcTemplate db, String table, Map<String, String> params) {
        params.remove("table");

        if (params.isEmpty()) {
            return db.queryForList("SELECT * FROM `" + table + "`");
        }

        StringBuilder sql = new StringBuilder("SELECT * FROM `" + table + "` WHERE ");
        Object[] values = new Object[params.size()];
        int i = 0;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (i > 0) sql.append(" AND ");
            sql.append("`").append(entry.getKey()).append("` = ?");
            values[i++] = entry.getValue();
        }

        return db.queryForList(sql.toString(), values);
    }

    // Convert List<Map<String,Object>> → String[][]
    /**
     * Convert the result list into a 2D String array with a header row.
     *
     * The first row contains column names; subsequent rows contain values
     * converted to strings (null becomes the literal "null").
     */
    private String[][] convertTo2DArray(List<Map<String, Object>> data) {
        if (data.isEmpty()) return new String[0][0];

        // Extract column names from first row
        List<String> columns = new ArrayList<>(data.get(0).keySet());
        int rows = data.size();
        int cols = columns.size();

        String[][] result = new String[rows + 1][cols];

        // Header row (column names)
        for (int j = 0; j < cols; j++) {
            result[0][j] = columns.get(j);
        }

        // Data rows
        for (int i = 0; i < rows; i++) {
            Map<String, Object> row = data.get(i);
            for (int j = 0; j < cols; j++) {
                Object val = row.get(columns.get(j));
                result[i + 1][j] = (val == null) ? "null" : val.toString();
            }
        }

        return result;
    }

    // ---------- Endpoints ----------

    @GetMapping("/db1/GetRowData")
    public String[][] getDb1(@RequestParam String table, @RequestParam Map<String, String> vals) {
        return convertTo2DArray(getData(db1, table, vals));
    }

    @GetMapping("/db2/GetRowData")
    public String[][] getDb2(@RequestParam String table, @RequestParam Map<String, String> vals) {
        return convertTo2DArray(getData(db2, table, vals));
    }

    @GetMapping("/db3/GetRowData")
    public String[][] getDb3(@RequestParam String table, @RequestParam Map<String, String> vals) {
        return convertTo2DArray(getData(db3, table, vals));
    }

    @GetMapping("/db4/GetRowData")
    public String[][] getDb4(@RequestParam String table, @RequestParam Map<String, String> vals) {
        return convertTo2DArray(getData(db4, table, vals));
    }

    @GetMapping("/db5/GetRowData")
    public String[][] getDb5(@RequestParam String table, @RequestParam Map<String, String> vals) {
        return convertTo2DArray(getData(db5, table, vals));
    }

    @GetMapping("/db6/GetRowData")
    public String[][] getDb6(@RequestParam String table, @RequestParam Map<String, String> vals) {
        return convertTo2DArray(getData(db6, table, vals));
    }

    @GetMapping("/db7/GetRowData")
    public String[][] getDb7(@RequestParam String table, @RequestParam Map<String, String> vals) {
        return convertTo2DArray(getData(db7, table, vals));
    }

    @GetMapping("/db8/GetRowData")
    public String[][] getDb8(@RequestParam String table, @RequestParam Map<String, String> vals) {
        return convertTo2DArray(getData(db8, table, vals));
    }
}
