package top.felixu.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public class BeanInfoUtils {

    public static void setProperty(Object instance, String propertyName, Object value) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, instance.getClass());
        Method method = descriptor.getWriteMethod();
        method.invoke(instance, value);
    }
}
