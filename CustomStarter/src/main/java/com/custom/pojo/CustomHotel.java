package com.custom.pojo;


import com.custom.config.TestProperties;

public class CustomHotel {

    private TestProperties properties;

    public CustomHotel(TestProperties properties) {
        this.properties = properties;
    }

    public CustomHotel() {
    }

    public void test() {
        System.out.println("properties: " + properties);
    }
}
