package com.Splitwise.Database.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * Simple controller that exposes endpoints to list tables present in each
 * configured database. Each endpoint corresponds to one of the configured
 * JdbcTemplate beans (db1..db8) and executes a SHOW TABLES query.
 *
 * Note: Responses are returned as List<Map<String,Object>> exactly as
 * produced by JdbcTemplate.queryForList().
 */
public class GetTables {

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

    @GetMapping("/db1/groups")
    public List<Map<String, Object>> getGroupsdb1() {
        return db1.queryForList("Show tables");
    }

    @GetMapping("/db2/groups")
    public List<Map<String, Object>> getGroupsdb2() {
        return db2.queryForList("Show tables");
    }

    @GetMapping("/db3/groups")
    public List<Map<String, Object>> getGroupsdb3() {
        return db3.queryForList("Show tables");
    }

    @GetMapping("/db4/groups")
    public List<Map<String, Object>> getGroupsdb4() {
        return db4.queryForList("Show tables");
    }

    @GetMapping("/db5/groups")
    public List<Map<String, Object>> getGroupsdb5() {
        return db5.queryForList("Show tables");
    }
    @GetMapping("/db6/groups")
    public List<Map<String, Object>> getGroupsdb6() {
        return db6.queryForList("Show tables");
    }
    
    @GetMapping("/db7/groups")
    public List<Map<String, Object>> getGroupsdb7() {
        return db7.queryForList("Show tables");
    }

    @GetMapping("/db8/groups")
    public List<Map<String, Object>> getGroupsdb8() {
        return db8.queryForList("Show tables");
    }

}