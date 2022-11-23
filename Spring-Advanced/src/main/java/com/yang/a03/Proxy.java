package com.yang.a03;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.UndeclaredThrowableException;

import java.lang.reflect.Method;

@Slf4j
public class Proxy extends Son1 {

    MethodInterceptor interceptor;

    public Proxy(MethodInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    static Method m0;
    static Method m1;
    static MethodProxy run;
    static MethodProxy walk;

    static {
        try {
            m0 = IInterface.class.getMethod("run");
            m1 = IInterface.class.getMethod("walk");
            run = MethodProxy.create(Son1.class, Proxy.class, "()V", "run", "runSuper");
            walk = MethodProxy.create(Son1.class, Proxy.class, "()I", "walk", "walkSuper");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void runSuper() {
        try {
            super.run();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public int walkSuper() {
        try {
            return super.walk();
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public void run() {
        try {
            Object intercept = interceptor.intercept(this, m0, new Object[]{}, run);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public int walk() {
        try {
            return (int) interceptor.intercept(this, m1, new Object[]{}, walk);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }


    public static void main(String[] args) {
        Son1 son = new Son1();
        Son1 proxy = new Proxy((target, method, objects, methodProxy) -> {
            log.info("before。。。");
//            Object invoke = method.invoke(son);
//            Object invoke = methodProxy.invoke(son, args); // 没有使用反射，结合目标对象使用
            Object invoke = methodProxy.invokeSuper(target, args); // 没有使用反射，结合代理对象使用
            log.info("after...");
            return invoke;
        });
        System.out.println(proxy);
        proxy.run();
        proxy.walk();
    }
}
