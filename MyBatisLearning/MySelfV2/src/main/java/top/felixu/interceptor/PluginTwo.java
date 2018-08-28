package top.felixu.interceptor;

import top.felixu.annotation.Intercepts;
import top.felixu.annotation.Signature;
import top.felixu.executor.Executor;
import top.felixu.plugin.Interceptor;
import top.felixu.plugin.Invocation;
import top.felixu.plugin.Plugin;

import java.util.Properties;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
@Intercepts({@Signature(type = Executor.class,
        method = "query",
        args = {String.class, Class.class, Object.class})})
public class PluginTwo implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("Plugin Two");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
