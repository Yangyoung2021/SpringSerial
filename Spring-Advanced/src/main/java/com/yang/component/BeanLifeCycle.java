package com.yang.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanLifeCycle {

    public BeanLifeCycle() {
        System.out.println("执行生命实例化方法。。。");
    }

//    @Autowired
    public void populateField(@Value("${java_home}") String java_home) {
        System.out.println("执行属性填充的方法" + java_home);
    }

    @PostConstruct
    public void init() {
        System.out.println("执行初始化方法");
    }

    @PreDestroy
    public void destroy() {
//        System.out.println("执行销毁方法");
    }
}
