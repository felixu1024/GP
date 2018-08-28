package top.felixu.mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public class MapperRegistry {

    private Map<String, Map<String, String>> sqlMapping = new HashMap<>();

    public void putObject(String className, Map<String, String> methodSqlMapping) {
        sqlMapping.put(className, methodSqlMapping);
    }

    public Map<String, String> getObject(String className) {
        return sqlMapping.get(className);
    }
}
