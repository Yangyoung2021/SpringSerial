package com.yang.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Proxy;

public class ProxyDemo {

    public static void main(String[] args) {
            CG_LIB_METHOD();
//        JDK_METHOD();
    }

    private static void CG_LIB_METHOD() {
        PlainClass plainClass = new PlainClass();

        PlainClass target = (PlainClass) Enhancer.create(plainClass.getClass(), (MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("执行方法前的增强");

            String money = (String) objects[0];

            int enhancedMoney = Integer.parseInt(money) + 10000000;

            String invoke = (String) method.invoke(plainClass, new Object[]{enhancedMoney + ""});
            System.out.println("执行方法后的增强");


            return "增强的返回值 + 实际的返回值：" + invoke;
        });

        String rs = target.show("1000000");

        System.out.println(rs);

    }

    private static void JDK_METHOD() {
        StaticProxyDemo staticProxyDemo = new StaticProxyDemo();

        // 获取代理对象，该代理对象既不能为传入的对象的类型，因为生成的结果对象只是和他实现了相同的接口，但是并没有实际的联系
        // 也不能是实际生成的代理对象，因为生成的代理对象是没有提前声明的，所以根本不知道是什么类型，只能确定他是实际需要代理
        // 接口的实现类，所以只能使用代理对象的接口来进行接收
        DemoInterface target = (DemoInterface) Proxy.newProxyInstance(staticProxyDemo.getClass().getClassLoader(),
                staticProxyDemo.getClass().getInterfaces(), (proxy, method, args1) -> {

                    /**
                     * 参数解释： proxy --> 代理的对象
                     *          method --> 代理执行的方法
                     *          args1 --> 代理方法中传递进来的实际参数
                     */

                    System.out.println("调用方法前操作");

                    Object invoke = method.invoke(staticProxyDemo, args1);

                    System.out.println("调用方法后操作");

                    return "代理结果  -->  代理结果, 实际结果 -->  " + invoke;
                });


        String result = target.doSomething("好家伙");

        System.out.println("返回的结果是" + result);
    }
}
