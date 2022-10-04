package com.yang;


import com.yang.component.ComponentDemo2;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.Map;


@SpringBootApplication
@SuppressWarnings(value = "unchecked")
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);


        ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();


        Class<?> aClass = Class.forName("org.springframework.beans.factory.support.DefaultSingletonBeanRegistry");

        Field singletonObjects = aClass.getDeclaredField("singletonObjects");

        singletonObjects.setAccessible(true);

        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);

//        map.forEach((k, v) -> System.out.println(k + "---->" + v));


        System.out.println("----------------------------------");

        map.entrySet().stream().filter(e -> e.getKey().startsWith("org.springframework.context.")).filter(e -> e.getKey().contains("internal")).forEach(
                e -> {
                    System.out.println(e.getKey() + "------>" + e.getValue());
                }
        );

        System.out.println("----------------------------------");


        Resource[] resources = ctx.getResources("classpath*:META-INF/spring.factories");

        for (Resource resource : resources) {
            System.out.println(resource);
        }



        System.out.println(ctx.getEnvironment().getProperty("java_home"));
        System.out.println(ctx.getEnvironment().getProperty("server.port"));


//        ctx.publishEvent(new PublisherDemo01(ctx));
        ctx.getBean(ComponentDemo2.class).register();
    }
}
