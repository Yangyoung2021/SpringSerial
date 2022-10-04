package com.yang.component;


import com.yang.publisher.PublisherDemo01;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * 事件监听器，一种监听方法对应一种发布，发布的是什么对象就要使用什么对象来接收信息
 */
@Component
@Slf4j
public class ComponentDemo01 {

    @EventListener
    public void listenerRegister(PublisherDemo01 event) {
        System.out.println(event);
        System.out.println("发送短信");
    }
}
