package com.yang.a02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MyBean1 implements BeanNameAware, ApplicationContextAware, InitializingBean {

    @Override
    public void setBeanName(String name) {
        log.info("name = " + name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("实现InitializingBean接口属性填充之后，初始化之前的方法调用");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("applicationContext = " + applicationContext);
    }

    @PostConstruct
    public void init() {
        log.info("PostConstruct指定的初始化方法。。。。。。。。。。。。。");
    }
}
