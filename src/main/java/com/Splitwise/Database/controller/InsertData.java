package com.Splitwise.Database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * Controller handling simple INSERT operations against configured databases.
 *
 * Endpoints are provided per-database (db1..db8) and delegate to a helper
 * method which composes an INSERT statement. Note: SQL is constructed
 * dynamically from request parameters — validate inputs in production.
 */
public class InsertData {

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

    // Helper: perform an INSERT using provided fragments
    /**
     * Build and execute an INSERT statement.
     *
     * @param db     target JdbcTemplate
     * @param table  table name (will be wrapped in backticks)
     * @param params column list fragment, e.g. "(col1, col2)"
     * @param info   values fragment, e.g. "('a','b')"
     */
    private String getData(JdbcTemplate db, String table, String params, String info) {
        // Use backticks to escape table names (handles reserved keywords like "Groups")
        String sql = "INSERT INTO `" + table + "` " + params + " VALUES " + info;
        System.out.println("sql -> " + sql);
        try {
            int rows = db.update(sql);
            return "OK: inserted (rows affected) = " + rows;
        } catch (Exception e) {
            // print stacktrace to server console for debugging
            e.printStackTrace();
            // Return a helpful message to the HTTP client for diagnosis
            return "ERROR executing SQL: " + sql + " -- " + e.getMessage();
        }
    }

    @GetMapping("/db1/InsertData")
    public String getDb1(@RequestParam String table,@RequestParam String params,@RequestParam String info) {
        return getData(db1, table, params, info);
    }

    @GetMapping("/db2/InsertData")
    public String getDb2(@RequestParam String table,@RequestParam String params,@RequestParam String info) {
        return getData(db2, table, params, info);
    }

    @GetMapping("/db3/InsertData")
    public String getDb3(@RequestParam String table,@RequestParam String params,@RequestParam String info) {
        return getData(db3, table, params, info);
    }
    
    @GetMapping("/db4/InsertData")
    public String getDb4(@RequestParam String table,@RequestParam String params,@RequestParam String info) {
        return getData(db4, table, params, info);
    }

    @GetMapping("/db5/InsertData")
    public String getDb5(@RequestParam String table, @RequestParam String params,@RequestParam String info) {
        return getData(db5, table, params, info);
    }

    @GetMapping("/db6/InsertData")
    public String getDb6(@RequestParam String table,@RequestParam String params,@RequestParam String info) {
        return getData(db6, table, params, info);
    }

    @GetMapping("/db7/InsertData")
    public String getDb7(@RequestParam String table,@RequestParam String params,@RequestParam String info) {
        return getData(db7, table, params, info);
    }

    @GetMapping("/db8/InsertData")
    public String getDb8(@RequestParam String table,@RequestParam String params,@RequestParam String info) {
        return getData(db8, table, params, info);
    }

}