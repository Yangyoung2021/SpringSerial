package com.yang.service;

import com.yang.fo.UserFo;
import com.yang.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    @Transactional
    void insertOne(User user);

    void insertList(@Valid List<UserFo> userFos);
}
