package com.yang;

import com.yang.beanDefinition.HelloClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloClass hello = context.getBean(HelloClass.class);
        System.out.println(hello);
        System.out.println(hello.getName());
        for (Map.Entry<String, String> entry : hello.getMap().entrySet()) {
            System.out.println("entry ----> key = " + entry.getKey() + ", value = " + entry.getValue());
        }
        hello.iterList(hello.getList());
    }
}
