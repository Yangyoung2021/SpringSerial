package com.yang.config;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

//@Component
public class MybatisConfig {

    @Bean
    public DataSource dataSource(MyDateSource myDateSource) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(myDateSource.getDriver());
        dataSource.setUrl(myDateSource.getUrl());
        dataSource.setPassword(myDateSource.getPassword());
        dataSource.setUsername(myDateSource.getUsername());
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setConfigLocation();
        factoryBean.setDataSource(dataSource);
        Configuration configuration = new Configuration();
        configuration.setLogImpl(StdOutImpl.class);
        factoryBean.setConfiguration(configuration);
        return factoryBean;
    }

    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
        // 此处设置的扫描包只能设置为resources目录下的文件夹，即使将配置文件放在源码包下也无效
        mapperScannerConfigurer.setBasePackage("com.yang.mapper");
        return mapperScannerConfigurer;
    }

    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }


}
