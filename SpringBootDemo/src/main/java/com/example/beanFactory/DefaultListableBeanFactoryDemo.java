//package com.example.beanFactory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.beans.factory.support.AbstractBeanDefinition;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.context.annotation.AnnotationConfigUtils;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//
//public class DefaultListableBeanFactoryDemo {
//
//
//    public static void main(String[] args) {
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//
//        // 想要将需要的对象放到容器中，需要将对象先通过工具类注册成BeanDefinition对象
//        AbstractBeanDefinition demo01 = BeanDefinitionBuilder.genericBeanDefinition(Demo01.class).setScope("singleton").getBeanDefinition();
//        // 将BeanDefinition对象注册到容器中，但此时不会解析注解，而且此时还只是给你注册了名字，没有真正成为一个Bean对象
//        beanFactory.registerBeanDefinition("demo01", demo01);
//
//        // 使用工具类给beanFactory注册后处理器
//        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
//
//        // 拿到BeanFactory后处理器对beanFactory进行处理，是对使用@Configuration和@Component注解中的@Bean注解定义的要添加的bean进行解析
//        // 并不能直接解析@Configuration和@Component注解将其转换成对象
//        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor -> {
//            System.out.println(beanFactoryPostProcessor.getClass().getName());
//            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
//        });
//
//        // 拿到Bean后处理器对注解进行解析，包含两个后处理器对象，
//        // AutowiredAnnotationBeanPostProcessor(解析@Autowired)和CommonAnnotationBeanPostProcessor(解析@Resource)
//        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanPostProcessor -> {
//            System.out.println(beanPostProcessor.getClass().getName());
//            beanFactory.addBeanPostProcessor(beanPostProcessor);
//        });
//
//
//        String[] names = beanFactory.getBeanDefinitionNames();
//
//        for (String name : names) {
//            System.out.println(name);
//        }
//
//
//        System.out.println("-------------------");
//
//        Demo01 bean01 = beanFactory.getBean(Demo01.class);
//
//        bean01.testDemo02();
//
//        beanFactory.getBean(Demo02.class).printInner();
//
//
//    }
//
//
//    @Configuration
//    static class Demo01 {
//
//        @Bean
//        public Demo03 demo03() {
//            return new Demo03();
//        }
//
//        @Bean
//        public Demo02 demo02() {
//            return new Demo02();
//        }
//
//
//        @Bean
//        public Bean1 bean1() {
//            return new Bean1();
//        }
//
//        @Bean
//        public Bean2 bean2() {
//            return new Bean2();
//        }
//
//        @Autowired
//        private Demo02 demo02;
//
//        public void testDemo02() {
//            System.out.println(demo02);
//        }
//
//
//    }
//
//    @Component
//    static class Demo02 {
//
//        public Demo02() {
//            System.out.println("构造Demo02.。。");
//        }
//
//        @Autowired
//        private Demo03 demo03;
//
//        public Demo03 getDemo03() {
//            return demo03;
//        }
//
//        @Resource
//        private Inner bean1;
//
//        public void printInner() {
//            System.out.println(bean1);
//        }
//
//
//    }
//
//    interface Inner {
//
//    }
//
//    @Component
//    static class Bean1 implements Inner {}
//    @Component
//    static class Bean2 implements Inner {}
//
//
//    static class Demo03 {
//
//    }
//}
