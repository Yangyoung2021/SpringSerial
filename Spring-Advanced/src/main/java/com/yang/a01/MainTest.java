package com.yang.a01;

import com.yang.postProcessor.AtBeanPostProcessor;
import com.yang.postProcessor.ComponentScanPostProcessor;
import com.yang.postProcessor.MapperBeanPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class MainTest {

    public static void main1(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
        // 添加后置处理器
        context.registerBean(ComponentScanPostProcessor.class);
        context.registerBean(AtBeanPostProcessor.class);
        context.registerBean(MapperBeanPostProcessor.class);

        context.refresh();

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
