package com.gnnt.mybatisgenerator.mapper;

import com.gnnt.mybatisgenerator.model.Company;
import com.gnnt.mybatisgenerator.model.CompanyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyMapper {
    long countByExample(CompanyCriteria example);

    int deleteByExample(CompanyCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    List<Company> selectByExample(CompanyCriteria example);

    Company selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Company record, @Param("example") CompanyCriteria example);

    int updateByExample(@Param("record") Company record, @Param("example") CompanyCriteria example);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
}