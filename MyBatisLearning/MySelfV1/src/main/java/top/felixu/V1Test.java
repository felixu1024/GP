package top.felixu;

import top.felixu.configuration.Configuration;
import top.felixu.entity.Person;
import top.felixu.executor.SimpleExecutor;
import top.felixu.mapper.PersonMapper;
import top.felixu.session.SqlSession;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public class V1Test {
    public static void main(String[] args) {
        SqlSession session = new SqlSession(new Configuration(), new SimpleExecutor());
        PersonMapper mapper = session.getMapper(PersonMapper.class);
        Person person = mapper.selectByPrimaryKey(1);
        System.out.println(person);
    }
}
