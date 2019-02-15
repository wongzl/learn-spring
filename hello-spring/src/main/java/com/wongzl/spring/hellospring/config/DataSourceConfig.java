// package com.wongzl.spring.hellospring.config;
//
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.datasource.DataSourceTransactionManager;
// import org.springframework.transaction.PlatformTransactionManager;
//
// import javax.annotation.Resource;
// import javax.sql.DataSource;
//
// /**
//  * 数据源配置。
//  *
//  * @author mac/zl
//  * @version v1.0
//  * @since 2019-02-15 21:27
//  */
// @Slf4j
// @Configuration
// public class DataSourceConfig {
//
//
//     @Bean
//     @ConfigurationProperties("foo.datasource")
//     public DataSourceProperties fooDataSourceProperties() {
//         return new DataSourceProperties();
//     }
//
//     @Bean
//     public DataSource fooDataSource() {
//         DataSourceProperties dataSourceProperties = fooDataSourceProperties();
//         log.info("foo datasource: {}", dataSourceProperties.getUrl());
//         return dataSourceProperties.initializeDataSourceBuilder().build();
//     }
//
//     @Bean
//     @Resource
//     public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
//         return new DataSourceTransactionManager(fooDataSource);
//     }
//
//     @Bean
//     @Primary
//     public JdbcTemplate fooJdbcTemplate(DataSource fooDataSource){
//         return new JdbcTemplate(fooDataSource);
//     }
//
//
//     @Bean
//     @ConfigurationProperties("bar.datasource")
//     public DataSourceProperties barDataSourceProperties() {
//         return new DataSourceProperties();
//     }
//
//     @Bean
//     public DataSource barDataSource() {
//         DataSourceProperties dataSourceProperties = barDataSourceProperties();
//         log.info("bar datasource: {}", dataSourceProperties.getUrl());
//         return dataSourceProperties.initializeDataSourceBuilder().build();
//     }
//
//     @Bean
//     @Resource
//     public PlatformTransactionManager barTxManager(DataSource barDataSource) {
//         return new DataSourceTransactionManager(barDataSource);
//     }
//
//     @Bean
//     public JdbcTemplate barJdbcTemplate(DataSource barDataSource){
//         return new JdbcTemplate(barDataSource);
//     }
//
// }
