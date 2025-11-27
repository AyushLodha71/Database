package com.Splitwise.Database.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * Controller that exposes endpoints to delete rows from a table using
 * URL query parameters as filters. Each endpoint targets a specific
 * configured database (db1..db8).
 *
 * Warning: builds SQL dynamically; ensure parameters are validated in
 * production environments.
 */
public class DeleteRowData {

    @Autowired
    @Qualifier("db1JdbcTemplate")
    private JdbcTemplate db1;

    @Autowired
    @Qualifier("db2JdbcTemplate")
    private JdbcTemplate db2;

    @Autowired
    @Qualifier("db3JdbcTemplate")
    private JdbcTemplate db3;

    @Autowired
    @Qualifier("db4JdbcTemplate")
    private JdbcTemplate db4;

    @Autowired
    @Qualifier("db5JdbcTemplate")
    private JdbcTemplate db5;

    @Autowired
    @Qualifier("db6JdbcTemplate")
    private JdbcTemplate db6;

    @Autowired
    @Qualifier("db7JdbcTemplate")
    private JdbcTemplate db7;

    @Autowired
    @Qualifier("db8JdbcTemplate")
    private JdbcTemplate db8;

    // Delete a row from a table based on parameters
    /**
     * Build and execute a DELETE statement using provided filters.
     *
     * @param db     target JdbcTemplate
     * @param table  table name (identifier used as-is)
     * @param params column->value filters (table param will be removed)
     */
    private void deleteData(JdbcTemplate db, String table, @RequestParam Map<String, String> params) {

        params.remove("table");

        StringBuilder sql = new StringBuilder("DELETE FROM " + table + " WHERE 1=1");
        Object[] values = new Object[params.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sql.append(" AND ").append(entry.getKey()).append("=?");
            values[i++] = entry.getValue();
        }

        db.update(sql.toString(), values);
    }

    @GetMapping("/db1/DeleteRowData")
    public void getDb1(@RequestParam String table, @RequestParam Map<String, String> params) {
        deleteData(db1, table, params);
    }

    @GetMapping("/db2/DeleteRowData")
    public void getDb2(@RequestParam String table, @RequestParam Map<String, String> params) {
        deleteData(db2, table, params);
    }

    @GetMapping("/db3/DeleteRowData")
    public void getDb3(@RequestParam String table, @RequestParam Map<String, String> params) {
        deleteData(db3, table, params);
    }

    @GetMapping("/db4/DeleteRowData")
    public void getDb4(@RequestParam String table, @RequestParam Map<String, String> params) {
        deleteData(db4, table, params);
    }

    @GetMapping("/db5/DeleteRowData")
    public void getDb5(@RequestParam String table, @RequestParam Map<String, String> params) {
        deleteData(db5, table, params);
    }

    @GetMapping("/db6/DeleteRowData")
    public void getDb6(@RequestParam String table, @RequestParam Map<String, String> params) {
        deleteData(db6, table, params);
    }

    @GetMapping("/db7/DeleteRowData")
    public void getDb7(@RequestParam String table, @RequestParam Map<String, String> params) {
        deleteData(db7, table, params);
    }

    @GetMapping("/db8/DeleteRowData")
    public void getDb8(@RequestParam String table, @RequestParam Map<String, String> params) {
        deleteData(db8, table, params);
    }
}