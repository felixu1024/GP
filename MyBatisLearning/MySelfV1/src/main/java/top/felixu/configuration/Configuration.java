package top.felixu.configuration;

import top.felixu.proxy.MapperProxy;
import top.felixu.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public class Configuration {

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{type}, new MapperProxy(sqlSession));
    }

    public static class PersonMapperXml {
        public static final String NAME_SPACE = "top.felixu.mapper.PersonMapper";
        public static final Map<String, String> SQL_MAPPING = new HashMap<>();

        static {
            SQL_MAPPING.put("selectByPrimaryKey", "SELECT * FROM person WHERE id = ?");
        }
    }
}
