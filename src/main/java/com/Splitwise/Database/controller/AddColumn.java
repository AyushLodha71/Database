package com.Splitwise.Database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * Controller that provides endpoints to add an integer (DECIMAL) column to a
 * table. The helper method sanitizes the column name to letters/digits/underscore
 * to reduce risk of injection.
 */
public class AddColumn {

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

    // Helper method to safely add a column
    /**
     * Add a DECIMAL(10,2) column to the given table. The column name is
     * validated to contain only letters, digits, or underscores.
     *
     * @param db    target JdbcTemplate
     * @param table table name
     * @param uname column name to add
     * @return success/failure message
     */
    private String addIntegerColumn(JdbcTemplate db, String table, String uname) {
        // Sanitize username (only allow letters, numbers, and underscores)
        if (!uname.matches("[A-Za-z0-9_]+")) {
            return "❌ Invalid column name. Use only letters, digits, or underscores.";
        }

        String sql = String.format("ALTER TABLE `%s` ADD COLUMN `%s` DECIMAL(10,2) DEFAULT 0;", table, uname);

        try {
            db.execute(sql);
            return "✅ Column '" + uname + "' added successfully to table '" + table + "'.";
        } catch (Exception e) {
            return "⚠️ Failed to add column: " + e.getMessage();
        }
    }

    // One endpoint per database
    @GetMapping("/db1/AddColumn")
    public String addColumnDb1(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db1, table, uname);
    }

    @GetMapping("/db2/AddColumn")
    public String addColumnDb2(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db2, table, uname);
    }

    @GetMapping("/db3/AddColumn")
    public String addColumnDb3(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db3, table, uname);
    }

    @GetMapping("/db4/AddColumn")
    public String addColumnDb4(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db4, table, uname);
    }

    @GetMapping("/db5/AddColumn")
    public String addColumnDb5(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db5, table, uname);
    }

    @GetMapping("/db6/AddColumn")
    public String addColumnDb6(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db6, table, uname);
    }

    @GetMapping("/db7/AddColumn")
    public String addColumnDb7(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db7, table, uname);
    }

    @GetMapping("/db8/AddColumn")
    public String addColumnDb8(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db8, table, uname);
    }
}
