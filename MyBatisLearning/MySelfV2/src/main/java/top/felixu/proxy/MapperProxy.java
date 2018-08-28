package top.felixu.proxy;

import top.felixu.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, String> methodSqlMapping = sqlSession.getConfiguration().getMapperRegistry().getObject(method.getDeclaringClass().getName());
        if (null != methodSqlMapping) {
            return sqlSession.selectOne(methodSqlMapping.get(method.getName()), method.getReturnType(), args[0]);
        }
        return method.invoke(this, args);
    }
}
