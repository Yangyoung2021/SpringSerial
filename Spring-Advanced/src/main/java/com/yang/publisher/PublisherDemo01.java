package com.yang.publisher;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;


/**
 * 事件发布器，当完成某项功能以后将消息发送，功能类似消息队列
 */
@Component
public class PublisherDemo01 extends ApplicationEvent {


    public PublisherDemo01(Object source) {
        super(source);
    }
}
