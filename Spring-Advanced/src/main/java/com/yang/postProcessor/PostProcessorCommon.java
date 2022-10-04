package com.yang.postProcessor;

import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;

public class PostProcessorCommon {
    // 创建缓冲元数据读取器工厂
    CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
    // 构建注解bean命名生成器
    AnnotationBeanNameGenerator nameGenerator = new AnnotationBeanNameGenerator();
    // 创建资源路径解析器
    PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
}
