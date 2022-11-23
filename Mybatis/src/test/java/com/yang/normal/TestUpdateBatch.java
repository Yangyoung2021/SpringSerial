package com.yang.normal;


import com.yang.MybatisApplication;
import com.yang.pojo.Student;
import com.yang.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = MybatisApplication.class)
public class TestUpdateBatch {

    @Autowired
    private StudentService studentService;

    @Test
    void insetBatchWithUpdate() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(6, "3", 100, "1", "18492849385", "4"));
        studentService.insertByNameAndGrade(list);
    }
}
