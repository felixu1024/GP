package top.felixu.executor;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public interface Executor {
    <T> T query(String sql, Class<T> returnType, Object parameter);
}
