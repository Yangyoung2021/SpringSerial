package com.yang.mapper;

import com.yang.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {


    void insertOne(Student student);

    int selectCountByParams(Student stu);

    void insertBatchByNameAndGrade(@Param("list") List<Student> students);
}
