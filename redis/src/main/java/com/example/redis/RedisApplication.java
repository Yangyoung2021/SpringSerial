package com.example.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Field;

@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) throws NoSuchFieldException {
        ApplicationContext context = SpringApplication.run(RedisApplication.class, args);
        boolean redis = context.containsBean("redis");
        Class<? extends ApplicationContext> aClass = context.getClass();
        Field singletonObjects = aClass.getDeclaredField("beanDefinitionMap");
        singletonObjects.setAccessible(true);
        System.out.println(singletonObjects);
    }

    @Bean
    public Jedis jedis() {
        return new Jedis("localhost", 6379);
    }

}
