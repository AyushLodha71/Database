package com.Splitwise.Database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * Controller to create new tables in the configured databases. Each endpoint
 * delegates to a helper that executes a DDL CREATE TABLE statement. This is
 * intended for administrative use only; be cautious running DDL from HTTP.
 */
public class CreateTable {

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

    // Create a table in the database
    /**
     * Execute a CREATE TABLE statement for the provided table and columns
     * specification.
     *
     * @param db      target JdbcTemplate
     * @param table   table name (will be wrapped in backticks)
     * @param columns column definition fragment, e.g. "id INT PRIMARY KEY, name VARCHAR(255)"
     */
    private String createTable(JdbcTemplate db, String table, String columns) {
        // Use backticks to escape table names (handles reserved keywords like "Groups")
        String sql = String.format("CREATE TABLE `%s` (%s)", table, columns);
        System.out.println("sql -> " + sql);
        try {
            db.execute(sql);
            return "OK: created table `" + table + "`";
        } catch (Exception e) {
            // print stacktrace to server console for debugging
            e.printStackTrace();
            // return helpful error to HTTP client for diagnosis
            return "ERROR executing SQL: " + sql + " -- " + e.getMessage();
        }
    }

    @GetMapping("/db1/CreateTable")
    public String getDb1(@RequestParam String table, @RequestParam String columns) {
        return createTable(db1, table, columns);
    }

    @GetMapping("/db2/CreateTable")
    public String getDb2(@RequestParam String table, @RequestParam String columns) {
        return createTable(db2, table, columns);
    }

    @GetMapping("/db3/CreateTable")
    public String getDb3(@RequestParam String table, @RequestParam String columns) {
        return createTable(db3, table, columns);
    }

    @GetMapping("/db4/CreateTable")
    public String getDb4(@RequestParam String table, @RequestParam String columns) {
        return createTable(db4, table, columns);
    }

    @GetMapping("/db5/CreateTable")
    public String getDb5(@RequestParam String table, @RequestParam String columns) {
        return createTable(db5, table, columns);
    }

    @GetMapping("/db6/CreateTable")
    public String getDb6(@RequestParam String table, @RequestParam String columns) {
        return createTable(db6, table, columns);
    }

    @GetMapping("/db7/CreateTable")
    public String getDb7(@RequestParam String table, @RequestParam String columns) {
        return createTable(db7, table, columns);
    }

    @GetMapping("/db8/CreateTable")
    public String getDb8(@RequestParam String table, @RequestParam String columns) {
        return createTable(db8, table, columns);
    }
}