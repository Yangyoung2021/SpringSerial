package com.yang.postProcessor;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

public class MapperBeanPostProcessor extends PostProcessorCommon
        implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            Resource[] resources =
                    resourcePatternResolver.getResources("classpath:com/yang/a01/mapper/**/*.class");
            for (Resource resource : resources) {
                MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
                ClassMetadata classMetadata = metadataReader.getClassMetadata();
                if (classMetadata.isInterface()) {
                    // 获取的对象是接口
                    AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                            .genericBeanDefinition(MapperFactoryBean.class)
                            .addConstructorArgValue(classMetadata.getClassName())
                            .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                            .getBeanDefinition();
                    AbstractBeanDefinition bd = BeanDefinitionBuilder
                            .genericBeanDefinition(classMetadata.getClassName())
                            .getBeanDefinition();
                    String name = nameGenerator.generateBeanName(bd, null);
                    registry.registerBeanDefinition(name, beanDefinition);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
