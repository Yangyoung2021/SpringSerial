package com.example;

import com.custom.pojo.CustomHotel;
import com.example.pojo.Single;
import com.example.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(SpringBootDemoApplication.class, args);
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
        Single single = context.getBean("single", Single.class);
        log.info("{}", single.getP1());
        log.info("{}", single.getP1());
        log.info("{}", single.getP1());
    }

}
