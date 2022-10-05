package com.yang.publisher;

import com.yang.component.ComponentDemo2;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


/**
 * 事件发布器，当完成某项功能以后将消息发送，功能类似消息队列
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PublisherDemo01 extends ApplicationEvent {


    public PublisherDemo01(ComponentDemo2 source) {
        super(source);
    }
}
