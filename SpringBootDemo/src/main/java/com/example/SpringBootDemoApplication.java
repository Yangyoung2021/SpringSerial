package com.example;

import com.custom.pojo.CustomHotel;
import com.example.pojo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootDemoApplication.class, args);
        User user = context.getBean(User.class);
        System.out.println(user);
        System.out.println("-------------");
        CustomHotel bean = context.getBean(CustomHotel.class);
        bean.test();

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            if (name.contains("test") || name.contains("custom")) {
                System.out.println(name);
            }
        }
    }

}
