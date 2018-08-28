package top.felixu.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public class ParameterHandler {
    public void setParameters(PreparedStatement statement, Integer parameter) throws SQLException {
        statement.setInt(1, parameter);
    }
}
