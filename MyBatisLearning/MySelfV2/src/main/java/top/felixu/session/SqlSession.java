package top.felixu.session;

import top.felixu.configuration.Configuration;
import top.felixu.executor.Executor;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public class SqlSession {

    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = configuration.newExecutor(executor);
    }

    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public <T> T selectOne(String sql, Class<T> returnType, Object parameter) {
        return executor.query(sql, returnType, parameter);
    }
}
