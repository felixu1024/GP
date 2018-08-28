package top.felixu.proxy;

import top.felixu.configuration.Configuration;
import top.felixu.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
        if (Configuration.PersonMapperXml.NAME_SPACE.equals(method.getDeclaringClass().getName())) {
            if (Configuration.PersonMapperXml.SQL_MAPPING.containsKey(method.getName())) {
                String sql = Configuration.PersonMapperXml.SQL_MAPPING.get(method.getName());
                return sqlSession.selectOne(sql, String.valueOf(args[0]));
            }
        }
        return method.invoke(this, args);
    }
}
