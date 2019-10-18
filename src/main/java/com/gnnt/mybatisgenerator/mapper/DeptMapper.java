package com.gnnt.mybatisgenerator.mapper;

import com.gnnt.mybatisgenerator.model.Dept;
import com.gnnt.mybatisgenerator.model.DeptCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeptMapper {
    long countByExample(DeptCriteria example);

    int deleteByExample(DeptCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Dept record);

    int insertSelective(Dept record);

    List<Dept> selectByExample(DeptCriteria example);

    Dept selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Dept record, @Param("example") DeptCriteria example);

    int updateByExample(@Param("record") Dept record, @Param("example") DeptCriteria example);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
}