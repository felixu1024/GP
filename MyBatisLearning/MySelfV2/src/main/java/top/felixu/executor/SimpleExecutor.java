package top.felixu.executor;

import top.felixu.handler.StatementHandler;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public class SimpleExecutor implements Executor {

    @Override
    public <T> T query(String sql, Class<T> returnType, Object parameter) {
        StatementHandler handler = new StatementHandler();
        return handler.query(sql, returnType, (Integer) parameter);
    }
}
