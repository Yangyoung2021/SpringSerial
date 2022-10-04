package com.yang.component;


import com.yang.publisher.PublisherDemo01;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


/**
 * 事件源对象，当事件源做了某些事情之后需要发布，就注入一个事件发布器来发布，然后将自己设置为发布源
 */
@Component
@Slf4j
public class ComponentDemo2 {

    @Autowired
    private ApplicationEventPublisher context;

    public void register() {
        System.out.println("用户注册");
        context.publishEvent(new PublisherDemo01(this));
    }
}
