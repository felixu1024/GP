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
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    public <T> T selectOne(String statement, String parameter) {
        return executor.query(statement, parameter);
    }
}
