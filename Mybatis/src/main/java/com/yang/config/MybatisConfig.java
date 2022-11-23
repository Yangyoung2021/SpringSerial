package com.yang.config;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Component
public class MybatisConfig {

//    @Bean
    public DataSource dataSource(MyDateSource myDateSource) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(myDateSource.getDriver());
        dataSource.setUrl(myDateSource.getUrl());
        dataSource.setPassword(myDateSource.getPassword());
        dataSource.setUsername(myDateSource.getUsername());
        return dataSource;
    }



//    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }


}
