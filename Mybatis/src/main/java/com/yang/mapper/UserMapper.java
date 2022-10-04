package com.yang.mapper;

import com.yang.fo.QueryParam;
import com.yang.pojo.User;

import java.util.HashSet;
import java.util.List;



public interface UserMapper {

    List<User> selectByCondition(QueryParam queryParam);

    int insertUserBatch(HashSet<User> users);

    void insertOne(User user);

    void insertUser(User user);
}
