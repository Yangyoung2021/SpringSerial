package com.yang.a02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class MyBean2 implements DisposableBean {

    @PreDestroy
    public void init() {
        log.info("PreDestroy指定的销毁方法。。。。。。。。。。。。。");
    }

    @Override
    public void destroy() throws Exception {
        log.info("实现DisposableBean接口属性填充之后，初始化之前的方法调用");
    }
}
