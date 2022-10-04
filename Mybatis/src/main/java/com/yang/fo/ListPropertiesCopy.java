package com.yang.fo;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


/**
 * 将集合中的数据类型转换成目标数据类型并设置日期属性
 */
public class ListPropertiesCopy {

    public static <S, T> HashSet<T> copyProperties(HashSet<S> source, Class<T> targetType) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HashSet<T> list = new HashSet<>();

        for (S o : source) {
            T target = targetType.newInstance();
            BeanUtils.copyProperties(o, target);
            Method setCreateTimeMethod = targetType.getDeclaredMethod("setCreateTime", Date.class);
            setCreateTimeMethod.invoke(target, new Date());
            Method setIdMethod = targetType.getDeclaredMethod("setId", String.class);
            setIdMethod.invoke(target, UUID.randomUUID().toString().replace("-", ""));
            list.add(target);
        }

        return list;
    }

}
