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
 * Controller that exposes endpoints to update rows in tables. Endpoints take
 * a {@code table} parameter, a {@code where} clause fragment and a map of
 * new values. The update SQL is constructed dynamically from these inputs.
 *
 * Security note: Building SQL fragments from user input can be dangerous —
 * validate and/or parameterize identifiers in production.
 */
public class UpdateData {

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

    // Update rows in a table
    /**
     * Build and execute an UPDATE statement using provided newValues and the
     * supplied WHERE fragment.
     *
     * @param db        target JdbcTemplate
     * @param table     table name (used directly in SQL)
     * @param where     WHERE clause fragment (e.g. "id = 5")
     * @param newValues map of column->newValue for SET clause
     * @return human-readable result message
     */
    private String updateData(JdbcTemplate db, String table, String where, Map<String, String> newValues) {
        // Remove control parameters that are not actual columns
        newValues.remove("table");
        newValues.remove("where");

        if (newValues.isEmpty()) return "No values provided for update.";

        StringBuilder sql = new StringBuilder("UPDATE " + table + " SET ");
        Object[] params = new Object[newValues.size()];
        int i = 0;

        for (Map.Entry<String, String> entry : newValues.entrySet()) {
            sql.append(entry.getKey()).append(" = ?");
            if (i < newValues.size() - 1) sql.append(", ");
            params[i++] = entry.getValue();
        }

        sql.append(" WHERE ").append(where);

        System.out.println("Executing SQL: " + sql); // ✅ debug line

        int rows = db.update(sql.toString(), params);
        return rows > 0 ? "✅ " + rows + " row(s) updated successfully!" : "⚠️ No rows matched the criteria.";
    }


    @GetMapping("/db1/UpdateData")
    public String updateDb1(@RequestParam String table,@RequestParam String where,@RequestParam Map<String, String> newValues) {
        return updateData(db1, table, where, newValues);
    }

    @GetMapping("/db2/UpdateData")
    public String updateDb2(@RequestParam String table,@RequestParam String where,@RequestParam Map<String, String> newValues) {
        return updateData(db2, table, where, newValues);
    }

    @GetMapping("/db3/UpdateData")
    public String updateDb3(@RequestParam String table,@RequestParam String where,@RequestParam Map<String, String> newValues) {
        return updateData(db3, table, where, newValues);
    }
    
    @GetMapping("/db4/UpdateData")
    public String updateDb4(@RequestParam String table,@RequestParam String where,@RequestParam Map<String, String> newValues) {
        return updateData(db4, table, where, newValues);
    }

    @GetMapping("/db5/UpdateData")
    public String updateDb5(@RequestParam String table, @RequestParam String where, @RequestParam Map<String, String> newValues) {
        return updateData(db5, table, where, newValues);
    }

    @GetMapping("/db6/UpdateData")
    public String updateDb6(@RequestParam String table, @RequestParam String where, @RequestParam Map<String, String> newValues) {
        return updateData(db6, table, where, newValues);
    }

    @GetMapping("/db7/UpdateData")
    public String updateDb7(@RequestParam String table, @RequestParam String where, @RequestParam Map<String, String> newValues) {
        return updateData(db7, table, where, newValues);
    }

    @GetMapping("/db8/UpdateData")
    public String updateDb8(@RequestParam String table, @RequestParam String where, @RequestParam Map<String, String> newValues) {
        return updateData(db8, table, where, newValues);
    }
    
}
