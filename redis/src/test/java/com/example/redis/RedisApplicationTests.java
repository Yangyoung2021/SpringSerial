package com.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest(classes = RedisApplication.class)
class RedisApplicationTests {

    @Autowired
    private Jedis jedis;

    @Test
    void contextLoads() {
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            log.info(key);
        }
    }

    @Test
    void stringTest() throws InterruptedException {
        // set方法的返回值是操作结果
        String set = jedis.set("hao", "好");
        log.info(set);
        String hao = jedis.get("hao");
        log.info(hao);
        // setnx方法返回值是影响的数量
        Long setResult = jedis.setnx("hao", "坏");
        log.info("{}", setResult);
        jedis.setex("time-10", 1L, "10");
        TimeUnit.SECONDS.sleep(1L);

        String timeRs = jedis.get("time-10");
        log.info(timeRs);
    }

    @Test
    void stringAdvanced() {
        Long setrange = jedis.setrange("hao", 3, "haoDe");
        String hao = jedis.get("hao");
        log.info(hao);
        jedis.setrange("22", 1, "12345");
        log.info(jedis.get("22"));
        String one = jedis.get("one");
        log.info(one);
        jedis.brpop(10, "list1");
        jedis.brpop("list1");
    }
}
