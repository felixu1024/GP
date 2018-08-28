package top.felixu.executor;

import top.felixu.entity.Person;

import java.sql.*;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public class SimpleExecutor implements Executor {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8" +
            "&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "felix19920320";

    @Override
    public <T> T query(String statement, String parameter) {
        PreparedStatement ps = getStatement(getConnection(), statement);
        Person person = null;
        try {
            ps.setInt(1, Integer.parseInt(parameter));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                person = new Person();
                person.setId(rs.getInt(1));
                person.setAddress(rs.getString(2));
                person.setAge(rs.getInt(3));
                person.setName(rs.getString(4));
            }
            rs.close();
        } catch (Exception e) {
            close(getConnection(), ps);
        }
        return (T) person;
    }

    private void close(Connection conn, PreparedStatement ps) {
        try {
            if (null != conn) {
                conn.close();
            }
            if (null != ps) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    private PreparedStatement getStatement(Connection conn, String statement) {
        if (null == conn) {
            return null;
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}
