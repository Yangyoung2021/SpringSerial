package com.yang.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(value = "com.yang")
@PropertySource(value = "classpath:jdbc.properties")
@MapperScan(value = "com.yang.mapper")
public class SpringConfig {


    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(4, 6, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
    }
}
