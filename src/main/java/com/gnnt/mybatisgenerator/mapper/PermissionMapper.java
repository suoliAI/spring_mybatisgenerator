package com.gnnt.mybatisgenerator.mapper;

import com.gnnt.mybatisgenerator.model.Permission;
import com.gnnt.mybatisgenerator.model.PermissionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PermissionMapper {
    long countByExample(PermissionCriteria example);

    int deleteByExample(PermissionCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionCriteria example);

    Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionCriteria example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionCriteria example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}