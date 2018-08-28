package top.felixu.handler;

import top.felixu.utils.BeanInfoUtils;

import java.beans.IntrospectionException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public class ResultSetHandler {

    public <T> T handler(ResultSet rs, Class<T> returnType) {
        try {
            Constructor<?> constructor = returnType.getDeclaredConstructor();
            constructor.setAccessible(true);
            T instance = (T) constructor.newInstance();
            if (rs.next()) {
                Arrays.stream(instance.getClass().getDeclaredFields())
                        .forEach(field -> fillField(instance, field, rs));
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> void fillField(T instance, Field field, ResultSet rs) {
        try {
            BeanInfoUtils.setProperty(instance, field.getName(), getValue(rs, field));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object getValue(ResultSet rs, Field field) throws SQLException {
        Class<?> type = field.getType();
        String name = field.getName();
        if (Integer.class == type) {
            return rs.getInt(name);
        } else if (String.class == type) {
            return rs.getString(name);
        }
        return null;
    }
}
