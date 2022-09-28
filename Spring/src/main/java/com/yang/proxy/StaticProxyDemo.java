package com.yang.proxy;


/**
 * 动态代理： 总体分为两种代理方法
 *          一、通过jdk中的动态创建实现相同接口的的类来加强这个方法
 */
public class StaticProxyDemo implements DemoInterface {

    @Override
    public String doSomething(String thing) {
        System.out.println("执行目标方法，获取参数" + thing);
        return "动态代理";
    }
}
