package com.yang.postProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Set;

public class AtBeanPostProcessor extends PostProcessorCommon implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            MetadataReader reader =
                    readerFactory.getMetadataReader(new ClassPathResource("com/yang/a01/Config.class"));
            Set<MethodMetadata> annotatedMethods =
                    reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());

            for (MethodMetadata method : annotatedMethods) {
                String initMethod = method.getAnnotationAttributes(Bean.class.getName())
                        .get("initMethod").toString();
                // 生成bean构造器，此处不能使用一个bean构造器，否则其中的参数会进行复用
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
                if (!StringUtils.isEmpty(initMethod)) {
                    builder.setInitMethodName(initMethod);
                }
                // 此处外层本还有一层类的循环中获取工厂bean
                builder.setFactoryMethodOnBean(method.getMethodName(), "config");
                // 使用@Bean注解的bean生成都是采用构造器方式的自动装配
                builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
                // 生成需要的bean
                AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
                // 注册bean
                registry.registerBeanDefinition(method.getMethodName(), beanDefinition);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
