package com.yang.test;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapInterTest {

    @Test
    public void testMap() {
        Map<String, String> map1 = new HashMap<>(1000000);
        Map<String, String> map2 = new HashMap<>(1000000);
        Map<String, String> same = new HashMap<>();

        Random random = new Random();

        for (long i = 0; i < 5000000L; i++) {
            String str1 = String.valueOf(random.nextLong());
            String str2 = String.valueOf(random.nextLong());
            map1.put(str1, str1);
            map2.put(str2, str2);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("aa22dd");
        }
        map1.put("1", "1");
        map2.put("1", "1");

        map2.forEach((k, v) -> {
            if (map1.containsKey(k)) same.put(k, k);
        });

        for (Map.Entry<String, String> entry : same.entrySet()) {
            System.out.println(entry.getKey() + "----->" + entry.getValue());
        }
    }
}
