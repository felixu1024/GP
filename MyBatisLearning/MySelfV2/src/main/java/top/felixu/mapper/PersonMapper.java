package top.felixu.mapper;

import top.felixu.annotation.Select;
import top.felixu.entity.Person;

public interface PersonMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    @Select("select * from person where id = ?")
    Person selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}