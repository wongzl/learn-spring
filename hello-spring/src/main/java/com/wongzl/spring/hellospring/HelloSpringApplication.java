package com.wongzl.spring.hellospring;

import com.wongzl.spring.hellospring.transactiondemo.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wongzl
 */
@Slf4j
@RestController
@EnableTransactionManagement
// @SpringBootApplication(exclude = {
//     DataSourceAutoConfiguration.class,
//     DataSourceTransactionManagerAutoConfiguration.class,
//     JdbcTemplateAutoConfiguration.class
// })
@SpringBootApplication
public class HelloSpringApplication implements CommandLineRunner {

    // @Resource(name = "fooDataSource")
    // private DataSource dataSource;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FooService fooService;

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringApplication.class, args);
    }


    // @Override
    // public void run(String... args) throws Exception {
    //     showConnection();
    //     showData();
    //
    //     log.info("COUNT BEFORE TRANSACTION: {}", getCount());
    //     transactionTemplate.execute(new TransactionCallbackWithoutResult() {
    //         @Override
    //         protected void doInTransactionWithoutResult(TransactionStatus status) {
    //             jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (3, 'aaa')");
    //             log.info("COUNT IN TRANSACTION: {}", getCount());
    //             status.setRollbackOnly();
    //         }
    //     });
    //     log.info("COUNT AFTER TRANSACTION: {}", getCount());
    //
    // }

    @Override
    public void run(String... args) throws Exception {
        fooService.insertRecord();
        log.info("AAA {}",
            jdbcTemplate
                .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='AAA'", Long.class));
        try {
            fooService.insertThenRollback();
        } catch (Exception e) {
            log.info("BBB {}",
                jdbcTemplate
                    .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
        }

        try {
            fooService.invokeInsertThenRollback();
        } catch (Exception e) {
            log.info("BBB {}",
                jdbcTemplate
                    .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
        }
    }

    private long getCount() {
        return (long) jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM FOO")
            .get(0).get("CNT");
    }

    private void showConnection() throws SQLException {
        log.info(dataSource.toString());
        Connection connection = dataSource.getConnection();
        log.info(connection.toString());
        connection.close();

    }

    private void showData() {
        jdbcTemplate.queryForList("SELECT * FROM FOO").forEach(row -> log.info(row.toString()));
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}

