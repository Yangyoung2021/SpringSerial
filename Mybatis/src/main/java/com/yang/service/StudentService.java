package com.yang.service;

import com.yang.pojo.Student;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void insertOne(Student student);

    @Transactional(rollbackFor = Exception.class)
    void insertByNameAndGrade(List<Student> students);
}
