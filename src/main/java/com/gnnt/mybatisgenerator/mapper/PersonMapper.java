package com.gnnt.mybatisgenerator.mapper;

import com.gnnt.mybatisgenerator.model.Person;
import com.gnnt.mybatisgenerator.model.PersonCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonMapper {
    long countByExample(PersonCriteria example);

    int deleteByExample(PersonCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    List<Person> selectByExample(PersonCriteria example);

    Person selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Person record, @Param("example") PersonCriteria example);

    int updateByExample(@Param("record") Person record, @Param("example") PersonCriteria example);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}