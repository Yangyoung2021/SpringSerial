package com.example.springbootdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class SpringBootDemoApplicationTests {

    @Test
    void contextLoads() {
        String s = "aaa";
        Integer i = 1111;
//        t1(s);
        t2(i);
        System.out.println(i);
    }

    private void t2(Integer i) {
        i = 1;
    }

    private void t1(String s) {
        s = "eee";
    }

}
