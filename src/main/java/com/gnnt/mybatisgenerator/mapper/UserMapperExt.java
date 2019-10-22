package com.gnnt.mybatisgenerator.mapper;

import com.gnnt.mybatisgenerator.model.UserCriteria;
import com.gnnt.mybatisgenerator.model.UserExt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapperExt {
    UserExt selectById(Integer id);
}
