package com.custom.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;


@ConfigurationProperties(prefix = "spring.test")
public class TestProperties {

    private String name;

    private Date time;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestProperties{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", age=" + age +
                '}';
    }
}
