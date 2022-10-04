package com.yang.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {


    @Pointcut("execution(* com.yang.test.Demo01.*(..))")
    public void pointcut(){}

    @Before(value = "pointcut()")
    public void before() {
        System.out.println("================前置增强===============");
    }

    @After(value = "pointcut()")
    public void after() {
        System.out.println("================后置增强===============");
    }

    @Around(value = "pointcut()")
    public void Around(ProceedingJoinPoint pjp) {
        System.out.println("================环绕前增强===============");
        try {
            Object proceed = pjp.proceed();
            System.out.println(proceed);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("===================环绕后增强====================");
    }
}
