package com.yang.postProcessor;

import com.yang.a01.Config;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
@SuppressWarnings(value = "all")
public class ComponentScanPostProcessor extends PostProcessorCommon
        implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            ComponentScan scan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
            if (scan != null) {
                String[] basePackages = scan.basePackages();
                for (String basePackage : basePackages) {
                    String path = "classpath*:" + basePackage.replace(".", "/") +
                            "**/*.class";
                    // 使用解析器解析Resource类型资源
                    Resource[] resources = resourcePatternResolver.getResources(path);
                    for (Resource resource : resources) {
                        // 使用缓冲元数据读取器工厂进行resource资源文件的解析
                        MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
                        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                        if (annotationMetadata.hasMetaAnnotation(Component.class.getName())
                                || annotationMetadata.hasAnnotation(Component.class.getName())) {
                            // 生成beanDefination
                            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                                    .genericBeanDefinition(metadataReader.getClassMetadata().getClassName())
                                    .getBeanDefinition();
                            // 生成bean名称
                            String name = nameGenerator.generateBeanName(beanDefinition, null);
                            registry.registerBeanDefinition(name, beanDefinition);
                        }
                    }
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
