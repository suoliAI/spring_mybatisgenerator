package com.gnnt.mybatisgenerator.service;

import com.gnnt.mybatisgenerator.model.User;

public interface IUserService {
    abstract User selectUserByName(String name);
    abstract User selectUserById(Integer id);
    abstract void add(User user);
    int update(User user);
}
