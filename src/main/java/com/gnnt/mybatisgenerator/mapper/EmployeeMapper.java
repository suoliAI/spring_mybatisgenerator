package com.gnnt.mybatisgenerator.mapper;

import com.gnnt.mybatisgenerator.model.Employee;
import com.gnnt.mybatisgenerator.model.EmployeeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeMapper {
    long countByExample(EmployeeCriteria example);

    int deleteByExample(EmployeeCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeCriteria example);

    Employee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeCriteria example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeCriteria example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}