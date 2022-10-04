package com.yang.service.impl;

import com.yang.mapper.StudentMapper;
import com.yang.pojo.Student;
import com.yang.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;



    @Override
    public void insertOne(Student student) {
        studentMapper.insertOne(student);
    }

    @Override
    public void insertByNameAndGrade(List<Student> students) {
        studentMapper.insertBatchByNameAndGrade(students);
    }

    public void selectCountByParams(Student i) {
        studentMapper.selectCountByParams(i);
    }
}
