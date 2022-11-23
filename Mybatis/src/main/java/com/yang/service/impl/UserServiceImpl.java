package com.yang.service.impl;

import com.yang.fo.UserFo;
import com.yang.mapper.UserMapper;
import com.yang.pojo.User;
import com.yang.service.StudentService;
import com.yang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentService studentService;


    @Override
//    @OpenAsync
    public void insertOne(User user) {
        log.info("开始插入用户方法执行。。。。");
        long startTime = System.currentTimeMillis();
//        studentService.insertOne(new Student(new BigDecimal(1000), "dd", 10));
        try {
            Thread.sleep(15000L);
            userMapper.insertOne(user);
            log.info("执行插入用户对象耗时：" + (System.currentTimeMillis() - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertList(List<UserFo> userFos) {
        ArrayList<User> users = new ArrayList<>();
        log.info("当前的fo集合中数据是:");
        for (UserFo userFo : userFos) {
            log.info(userFo.toString());
            User user = new User();
            user.setId(UUID.randomUUID().toString().replace("-", ""));
            BeanUtils.copyProperties(userFo, user);
            users.add(user);
        }
        log.info("拷贝后当前的集合中数据是{}", users);
//        userMapper.insertUserBatch(users);
    }


}
