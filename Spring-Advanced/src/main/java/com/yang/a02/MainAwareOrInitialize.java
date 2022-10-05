package com.yang.a02;

import org.springframework.context.support.GenericApplicationContext;

public class MainAwareOrInitialize {

    public static void main1(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("myBean1", MyBean1.class);
        context.refresh();
        context.close();
    }
}
