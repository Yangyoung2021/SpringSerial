package com.yang.a03;


import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class $Proxy0 extends Proxy implements IInterface {

    static Method m0;
    static Method m1;

    static {
        try {
            m0 = IInterface.class.getMethod("run");
            m1 = IInterface.class.getMethod("walk");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getLocalizedMessage());
        }
    }

    protected $Proxy0(InvocationHandler h) {
        super(h);
    }

    @Override
    public void run() {
        try {
            this.h.invoke(this, m0, new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int walk() {
        try {
            return (int) this.h.invoke(this, m1, new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Son1 son1 = new Son1();
        IInterface instance = (IInterface) Proxy.newProxyInstance($Proxy0.class.getClassLoader(), Son1.class.getInterfaces(),
                (proxy, method, args1) -> {
                    log.info("before.....");
                    Object rs = method.invoke(son1, args1);
                    log.info("after.....");
                    return rs;
                });

        instance.run();

        Son1 s = (Son1) Enhancer.create(Son1.class, (MethodInterceptor) (o1, method, arg, methodProxy) -> {
            log.info("before");
//            Object invoke = method.invoke(son1, arg); // 使用反射调用方法
//            Object invoke = methodProxy.invoke(son1, arg); // 不使用反射，但是需要依靠原始对象
            Object invoke = methodProxy.invokeSuper(o1, arg); // 不使用反射，不需要依靠原始对象，但是需要依靠代理
            log.info("after");
            return invoke;
        });
        s.run();

        IInterface proxy0 = new $Proxy0((proxy, method, args12) -> {
            log.info("...before");
            Object invoke = method.invoke(son1, args12);
            log.info("...after");
            return invoke;
        });

        proxy0.walk();

    }
}
