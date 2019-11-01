package com.gnnt.mybatisgenerator.service;

import com.gnnt.mybatisgenerator.mapper.UserMapper;
import com.gnnt.mybatisgenerator.model.User;
import com.gnnt.mybatisgenerator.model.UserCriteria;
import com.gnnt.mybatisgenerator.untils.ShiroEncryption;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByName(String name) {
        UserCriteria criteria = new UserCriteria();
        UserCriteria.Criteria criteria1 = criteria.createCriteria();
        criteria1.andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(criteria);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }
        return users.stream().findFirst().get();
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void add(User user) {
        user.setSalt(UUID.randomUUID().toString());
        user.setPassword(ShiroEncryption.shiroEncryption(user.getPassword(),user.getSalt()));
        userMapper.insert(user);
    }

    @Transactional()
    @Override
    public int update(User user){
        int result = userMapper.updateByPrimaryKey(user);
        throw new RuntimeException("用户ID为空");
    }
}
