package top.felixu.plugin;

import java.util.Properties;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public interface Interceptor {
    Object intercept(Invocation invocation) throws Throwable;

    Object plugin(Object target);

    void setProperties(Properties properties);
}
