package top.felixu;

import top.felixu.configuration.Configuration;
import top.felixu.entity.Person;
import top.felixu.executor.SimpleExecutor;
import top.felixu.interceptor.PluginOne;
import top.felixu.interceptor.PluginTwo;
import top.felixu.mapper.PersonMapper;
import top.felixu.plugin.Interceptor;
import top.felixu.session.SqlSession;

/**
 * @Author felixu
 * @Date 2018.08.26
 */
public class V2Test {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .mapperScan("top.felixu.mapper")
                .addPlugins(new Interceptor[]{new PluginOne(), new PluginTwo()})
                .build();
        SqlSession session = new SqlSession(configuration, new SimpleExecutor());
        PersonMapper mapper = session.getMapper(PersonMapper.class);
        Person person = mapper.selectByPrimaryKey(1);
        System.out.println(person);
    }
}
