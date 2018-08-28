package top.felixu;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.felixu.dao.PersonMapper;
import top.felixu.entity.Person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author felixu
 * @Date 2018.08.20
 */
public class MyBatisTest {

    public static void main(String[] args) {
        System.out.println(queryPersonById(getSqlSession(), 1));
    }

//    public static void main(String[] args) {
//        Person p = new Person();
//        p.setAge(26);
//        p.setAddress("深圳");
//        p.setName("top");
//        System.out.println(insertPerson(getSqlSession(), p));
//    }

    private static SqlSession getSqlSession() {
        String resource = "/Users/felixu/github/felixu1024/GP/MyBatisLearning/MyBatis/src/main/java/top/felixu/config/mybatis-config.xml";
        InputStream is = null;
        try {
            is = new FileInputStream(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        return build.openSession(true);
    }

    private static int insertPerson(SqlSession sqlSession, Person person) {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        return mapper.insert(person);
    }

    private static Person queryPersonById(SqlSession sqlSession, int id) {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        return mapper.selectByPrimaryKey(id);
    }
}
