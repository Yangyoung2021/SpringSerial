//package com.example.beanFactory;
//
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
//import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.Controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class ApplicationContextImpls {
//
//
//    public static void main(String[] args) {
////        XmlApplicationContext();
////        AnnotationApplicationContext();
////        WebAnnotationApplicationContext();
//
//    }
//
//    private static void WebAnnotationApplicationContext() {
//        AnnotationConfigServletWebServerApplicationContext webServerApplicationContext =
//                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
//
//        String[] names = webServerApplicationContext.getBeanDefinitionNames();
//
//        for (String name : names) {
//            System.out.println(name);
//        }
//    }
//
//
//    @Configuration
//    static class WebConfig {
//        /**
//         * 配置Tomcat服务器
//         * @return Tomcat网络服务器工厂
//         */
//        @Bean
//        public ServletWebServerFactory servletWebServerFactory() {
//            return new TomcatServletWebServerFactory();
//        }
//
//        /**
//         * 配置前端控制器
//         * @return 前端控制器实现
//         */
//        @Bean
//        public DispatcherServlet dispatcherServlet() {
//            return new DispatcherServlet();
//        }
//
//        /**
//         * 注册前端控制器并设置服务器要拦截的请求
//         * @param dispatcherServlet 前端控制器
//         * @return 注册前端控制器的Bean
//         */
//        @Bean
//        public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet) {
//            return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
//        }
//
//        /**
//         * 测试接口
//         * @return null
//         */
//        @Bean("/hello")
//        public Controller controller1() {
//            return (request, response) -> {
//                response.getWriter().write("Hello ....");
//                return null;
//            };
//        }
//    }
//
//    private static void AnnotationApplicationContext() {
//
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
//    }
//
//    private static void XmlApplicationContext() {
//        // 创建IOC容器
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        // 创建能够直接将Xml文件中定义好的Bean对象解析成BeanDefinition对象的解析器，将IOC容器作为接收的对象
//        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
//        // 指定Xml配置文件的位置让解析器去读取
//        xmlBeanDefinitionReader.loadBeanDefinitions("applicationContext.xml");
//        // 获取IOC容器中的Bean
//        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
//        // 打印
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
//    }
//
//
//
//    static class Config {
//
//        @Bean
//        public DefaultListableBeanFactoryDemo.Bean1 Bean1() {
//            return new DefaultListableBeanFactoryDemo.Bean1();
//        }
//    }
//}
