package com.example.pojo;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Single {

    @Lazy
    @Autowired
    private ObjectFactory<PrototypeDemo> p1;

    public PrototypeDemo getP1() {
        return p1.getObject();
    }

}
