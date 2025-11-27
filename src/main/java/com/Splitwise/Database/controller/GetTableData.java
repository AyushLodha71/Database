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
 * Controller exposing endpoints to retrieve all rows from a given table for
 * each configured database. Returns a List<Map<String,Object>> exactly as
 * produced by JdbcTemplate.queryForList().
 */
public class GetTableData {

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

    // Get all rows from a table
    /**
     * Execute a SELECT * FROM `table` and return raw rows.
     *
     * @param db    target JdbcTemplate
     * @param table table name (wrapped in backticks)
     * @return list of rows as maps
     */
    private List<Map<String, Object>> getData(JdbcTemplate db, String table) {
        // Use backticks to escape table names (handles reserved keywords like "Groups")
        String sql = "SELECT * FROM `" + table + "`";
        return db.queryForList(sql);
    }

    @GetMapping("/db1/GetTableData")
    public List<Map<String, Object>> getDb1(@RequestParam String table) {
        return getData(db1, table);
    }

    @GetMapping("/db2/GetTableData")
    public List<Map<String, Object>> getDb2(@RequestParam String table) {
        return getData(db2, table);
    }

    @GetMapping("/db3/GetTableData")
    public List<Map<String, Object>> getDb3(@RequestParam String table) {
        return getData(db3, table);
    }

    @GetMapping("/db4/GetTableData")
    public List<Map<String, Object>> getDb4(@RequestParam String table) {
        return getData(db4, table);
    }

    @GetMapping("/db5/GetTableData")
    public List<Map<String, Object>> getDb5(@RequestParam String table) {
        return getData(db5, table);
    }

    @GetMapping("/db6/GetTableData")
    public List<Map<String, Object>> getDb6(@RequestParam String table) {
        return getData(db6, table);
    }

    @GetMapping("/db7/GetTableData")
    public List<Map<String, Object>> getDb7(@RequestParam String table) {
        return getData(db7, table);
    }

    @GetMapping("/db8/GetTableData")
    public List<Map<String, Object>> getDb8(@RequestParam String table) {
        return getData(db8, table);
    }
}