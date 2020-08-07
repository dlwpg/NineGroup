package com.group.dao;

import com.group.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User queryPersonByPersonName(String username);

    int totaldown(Integer userid);

    int totaldowns(Integer count, Integer userid);

    User tongjinan();
    User tongjizong();
}