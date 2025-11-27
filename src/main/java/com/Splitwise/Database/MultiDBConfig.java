package com.Splitwise.Database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
/**
 * Configuration class that defines multiple DataSource and JdbcTemplate
 * beans — one logical pair per backend database. All beans are annotated with
 * {@code @Lazy} so the application can start even when some databases are
 * unavailable; each Datasource will be initialized on first use.
 *
 * The application expects properties under names like:
 * spring.datasource.sa-checkamountspentfolder, spring.datasource.sa-credentials, etc.
 *
 * Note: Beans are named explicitly (e.g. "db1DataSource", "db1JdbcTemplate")
 * and controllers inject them with {@code @Qualifier}.
 */
public class MultiDBConfig {

    // Database 1 Configuration
    @Bean
    @Lazy
    @ConfigurationProperties("spring.datasource.sa-checkamountspentfolder")
    public DataSourceProperties db1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "db1DataSource")
    @Lazy
    public DataSource db1DataSource() {
        return db1DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "db1JdbcTemplate")
    @Lazy
    public JdbcTemplate db1JdbcTemplate(@Qualifier("db1DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // 🔵 Database 2 Configuration
    @Bean
    @Lazy
    @ConfigurationProperties("spring.datasource.sa-credentials")
    public DataSourceProperties db2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "db2DataSource")
    @Lazy
    public DataSource db2DataSource() {
        return db2DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "db2JdbcTemplate")
    @Lazy
    public JdbcTemplate db2JdbcTemplate(@Qualifier("db2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // Database 3 Configuration
    @Bean
    @Lazy
    @ConfigurationProperties("spring.datasource.sa-groupnames")
    public DataSourceProperties db3DataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean(name = "db3DataSource")
    @Lazy
    public DataSource db3DataSource() {
        return db3DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "db3JdbcTemplate")
    @Lazy
    public JdbcTemplate db3JdbcTemplate(@Qualifier("db3DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    // Database 4 Configuration
    @Bean
    @Lazy
    @ConfigurationProperties("spring.datasource.sa-groups")
    public DataSourceProperties db4DataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean(name = "db4DataSource")
    @Lazy
    public DataSource db4DataSource() {
        return db4DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "db4JdbcTemplate")
    @Lazy
    public JdbcTemplate db4JdbcTemplate(@Qualifier("db4DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    // Database 5 Configuration
    @Bean
    @Lazy
    @ConfigurationProperties("spring.datasource.sa-paymenthistory")
    public DataSourceProperties db5DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "db5DataSource")
    @Lazy
    public DataSource db5DataSource() {
        return db5DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "db5JdbcTemplate")
    @Lazy
    public JdbcTemplate db5JdbcTemplate(@Qualifier("db5DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // Database 6 Configuration
    @Bean
    @Lazy
    @ConfigurationProperties("spring.datasource.sa-pendingamount")
    public DataSourceProperties db6DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "db6DataSource")
    @Lazy
    public DataSource db6DataSource() {
        return db6DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "db6JdbcTemplate")
    @Lazy
    public JdbcTemplate db6JdbcTemplate(@Qualifier("db6DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    // Database 7 Configuration
    @Bean
    @Lazy
    @ConfigurationProperties("spring.datasource.sa-personalfolders")
    public DataSourceProperties db7DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "db7DataSource")
    @Lazy
    public DataSource db7DataSource() {
        return db7DataSourceProperties().initializeDataSourceBuilder().build();
    }
    @Bean(name = "db7JdbcTemplate")
    @Lazy
    public JdbcTemplate db7JdbcTemplate(@Qualifier("db7DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    // Database 8 Configuration
    @Bean
    @Lazy
    @ConfigurationProperties("spring.datasource.sa-transactiondetails")
    public DataSourceProperties db8DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "db8DataSource")
    @Lazy
    public DataSource db8DataSource() {
        return db8DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "db8JdbcTemplate")
    @Lazy
    public JdbcTemplate db8JdbcTemplate(@Qualifier("db8DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
