package com.yang;

import com.custom.pojo.CustomHotel;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;
import java.util.Map;


@SpringBootApplication
@SuppressWarnings("unchecked")
public class Application {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomHotel customHotel = context.getBean(CustomHotel.class);
        customHotel.test();

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();


        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);

        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
        map.forEach((k, v) -> System.out.println(k + " = " + v));
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            if (beanDefinitionName.contains("test") || beanDefinitionName.contains("custom")) {
                System.out.println(beanDefinitionName);
            }
        }
    }
}
