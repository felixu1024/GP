package top.felixu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.felixu.config.DataSourceConfig;
import top.felixu.config.MyBatisConfig;
import top.felixu.dao.PersonMapper;
import top.felixu.entity.Person;

/**
 * @Author felixu
 * @Date 2018.08.20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, MyBatisConfig.class})
public class MyBatisTest {

    @Autowired
    PersonMapper personMapper;

    @Test
    public void select() {
        Person person = personMapper.selectByPrimaryKey(1);
        System.out.println(person.toString());
    }

    @Test
    public void insertPerson() {
        Person person = new Person();
        person.setName("felixu");
        person.setAge(26);
        person.setAddress("Mars");
        personMapper.insert(person);
    }
}
