package com.gnnt.mybatisgenerator.mapper;

import com.gnnt.mybatisgenerator.model.Role;
import com.gnnt.mybatisgenerator.model.RoleExt;
import com.gnnt.mybatisgenerator.model.UserExt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapperExt {
    RoleExt selectById(Integer id);
}
