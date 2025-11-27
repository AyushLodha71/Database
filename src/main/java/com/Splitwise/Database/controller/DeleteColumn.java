package com.Splitwise.Database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * Controller to drop a column from a table. The helper validates the column
 * name and executes an ALTER TABLE DROP COLUMN statement. Use with care.
 */
public class DeleteColumn {

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

    // Helper method to safely delete a column
    /**
     * Drop the specified column from the table after validating the column name.
     *
     * @param db    target JdbcTemplate
     * @param table table name
     * @param uname column to drop
     * @return status message
     */
    private String addIntegerColumn(JdbcTemplate db, String table, String uname) {
        // Sanitize username (only allow letters, numbers, and underscores)
        if (!uname.matches("[A-Za-z0-9_]+")) {
            return "❌ Invalid column name. Use only letters, digits, or underscores.";
        }

        String sql = String.format("ALTER TABLE `%s` DROP COLUMN `%s`;", table, uname);

        try {
            db.execute(sql);
            return "✅ Column '" + uname + "' added successfully to table '" + table + "'.";
        } catch (Exception e) {
            return "⚠️ Failed to add column: " + e.getMessage();
        }
    }

    // One endpoint per database
    @GetMapping("/db1/DeleteColumn")
    public String DeleteColumnDb1(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db1, table, uname);
    }

    @GetMapping("/db2/DeleteColumn")
    public String DeleteColumnDb2(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db2, table, uname);
    }

    @GetMapping("/db3/DeleteColumn")
    public String DeleteColumnDb3(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db3, table, uname);
    }

    @GetMapping("/db4/DeleteColumn")
    public String DeleteColumnDb4(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db4, table, uname);
    }

    @GetMapping("/db5/DeleteColumn")
    public String DeleteColumnDb5(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db5, table, uname);
    }

    @GetMapping("/db6/DeleteColumn")
    public String DeleteColumnDb6(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db6, table, uname);
    }

    @GetMapping("/db7/DeleteColumn")
    public String DeleteColumnDb7(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db7, table, uname);
    }

    @GetMapping("/db8/DeleteColumn")
    public String DeleteColumnDb8(@RequestParam String table, @RequestParam String uname) {
        return addIntegerColumn(db8, table, uname);
    }
}
