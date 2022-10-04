package com.custom.config;


import com.custom.pojo.CustomHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Date;

@Configuration(proxyBeanMethods = true)
@EnableConfigurationProperties(value = TestProperties.class)
@ConditionalOnClass(value = CustomHotel.class)
public class TestConfiguration {

    private final String name = "张三";

    private final Date time = new Date(2022, Calendar.JANUARY, 18, 18, 23, 18);

    private final Integer age = 18;

    @Autowired
    private TestProperties properties;


    @Bean
    @ConditionalOnMissingBean(CustomHotel.class)
    public CustomHotel customHotel() {
        TestProperties testProperties = new TestProperties();
        System.out.println(properties);
        testProperties.setName(properties.getName() != null ? properties.getName() : name);
        testProperties.setTime(properties.getTime() != null ? properties.getTime() : time);
        testProperties.setAge(properties.getAge() != null ? properties.getAge() : age);
        return new CustomHotel(testProperties);
    }


}
