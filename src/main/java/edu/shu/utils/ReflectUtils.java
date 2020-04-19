package edu.shu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * @author liang
 * @create 2020/4/19 11:56 上午
 */
public class ReflectUtils {
    private static final Logger log = LoggerFactory.getLogger(ReflectUtils.class);

    public static Optional<Object> getValueByField(Field field, Object obj) {
        try {
            field.setAccessible(true);
            return Optional.of(field.get(obj));
        } catch (IllegalAccessException e) {
            log.info("[ReflectUtils]::[getValueByField]::field = [{}], obj = [{}], e:{}", field, obj, e);
            return Optional.empty();
        }
    }

    public static Optional<Class<?>> getListGenericType(Field o) {
        o.setAccessible(true);
        Type genericType = o.getGenericType();
        if (genericType != null && genericType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericType;
            //得到泛型里的class类型对象
            return Optional.of((Class<?>) pt.getActualTypeArguments()[0]);
        }
        return Optional.empty();
    }
}
