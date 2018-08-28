package top.felixu.configuration;

import top.felixu.annotation.Select;
import top.felixu.executor.Executor;
import top.felixu.mapper.MapperRegistry;
import top.felixu.plugin.Interceptor;
import top.felixu.plugin.InterceptorChain;
import top.felixu.proxy.MapperProxy;
import top.felixu.session.SqlSession;
import top.felixu.utils.PackageUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public class Configuration {

    private String mapperScan;
    private MapperRegistry mapperRegistry = new MapperRegistry();
    private final InterceptorChain interceptorChain = new InterceptorChain();

    public Configuration mapperScan(String basePackage) {
        this.mapperScan = basePackage;
        return this;
    }

    public Configuration addPlugins(Interceptor[] plugins) {
        Arrays.stream(plugins)
                .forEach(interceptorChain::addInterceptor);
        return this;
    }

    public Configuration build() {
        try {
            List<String> classNames = PackageUtils.getClassName(mapperScan, true);
            Optional.ofNullable(classNames)
                    .ifPresent(names -> names.forEach(this::registryMapper));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    private void registryMapper(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();
            Map<String, String> methodSql = new HashMap<>(16);
            Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(Select.class))
                    .forEach(method -> {
                        Select annotation = method.getAnnotation(Select.class);
                        methodSql.put(method.getName(),annotation.value());
                    });
            mapperRegistry.putObject(className, methodSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{type}, new MapperProxy(sqlSession));
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public Executor newExecutor(Executor executor) {
        return (Executor) interceptorChain.pluginAll(executor);
    }
}
