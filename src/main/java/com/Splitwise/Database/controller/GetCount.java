package com.Splitwise.Database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * Controller exposing simple COUNT(*) queries for each configured database.
 * Use the `table` request parameter to specify the target table. Returns an
 * integer count of rows.
 */
public class GetCount {

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

    // Get the count of rows in a table
    /**
     * Execute SELECT COUNT(*) for the specified table.
     *
     * @param db    target JdbcTemplate
     * @param table table name (wrapped in backticks)
     * @return number of rows in the table
     */
    private int GetCount(JdbcTemplate db, String table) {
        String sql = String.format("SELECT COUNT(*) FROM `%s`", table);
        return db.queryForObject(sql, Integer.class);
    }

    @GetMapping("/db1/GetCount")
    public int getDb1(@RequestParam String table) {
        return GetCount(db1, table);
    }

    @GetMapping("/db2/GetCount")
    public int getDb2(@RequestParam String table) {
        return GetCount(db2, table);
    }

    @GetMapping("/db3/GetCount")
    public int getDb3(@RequestParam String table) {
        return GetCount(db3, table);
    }

    @GetMapping("/db4/GetCount")
    public int getDb4(@RequestParam String table) {
        return GetCount(db4, table);
    }

    @GetMapping("/db5/GetCount")
    public int getDb5(@RequestParam String table) {
        return GetCount(db5, table
        
        );
    }

    @GetMapping("/db6/GetCount")
    public int getDb6(@RequestParam String table) {
        return GetCount(db6, table);
    }

    @GetMapping("/db7/GetCount")
    public int getDb7(@RequestParam String table) {
        return GetCount(db7, table);
    }

    @GetMapping("/db8/GetCount")
    public int getDb8(@RequestParam String table) {
        return GetCount(db8, table);
    }
    
}