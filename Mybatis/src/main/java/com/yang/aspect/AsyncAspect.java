package com.yang.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Component
@Aspect
public class AsyncAspect {

//    private static final ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    Object rs;

    @Pointcut("@annotation(com.yang.aspect.OpenAsync)")
    public void pointcut(){}


    @Around(value = "pointcut()")
    public Object Around(ProceedingJoinPoint joinPoint) {
        threadPoolExecutor.execute(() -> {
            System.out.println("创建新线程处理任务，线程名称是：" + Thread.currentThread().getName());
            System.out.println("执行的方法是：" + joinPoint.getTarget().getClass().getName() + "的方法" + joinPoint.getSignature().getName());
            try {
                rs = joinPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });

        return rs;

    }

//    static {
//        threadPoolExecutor =  new ThreadPoolExecutor(4, 6, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
//    }

}
