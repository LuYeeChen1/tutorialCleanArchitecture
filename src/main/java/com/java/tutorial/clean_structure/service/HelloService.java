package com.java.tutorial.clean_structure.service;

import org.springframework.stereotype.Service;
import com.java.tutorial.clean_structure.model.Student;
import com.java.tutorial.clean_structure.repository.StudentRepository;
import java.util.List;

/**
 * @Service: 告诉 Spring Boot 这是一个业务逻辑组件。
 * 在 2026 年的 Spring Boot 4.0 中，这些组件会自动被纳入模块化管理，
 * 从而实现极速启动。
 */
@Service
public class HelloService {

    private final StudentRepository studentRepository;

    // 构造函数注入：Spring Boot 4.0 会自动把仓库找来给你
    public HelloService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 保存学生的方法
    public Student saveStudent(String name, int score) {
        Student newStudent = new Student(null,name, score);
        // .save() 是 JpaRepository 自带的，它会自动生成 INSERT INTO 语句
        return studentRepository.save(newStudent);
    }

    // 获取所有学生的方法
    public List<Student> getAllStudents() {
        // .findAll() 也是自带的，它会自动生成 SELECT * FROM 语句
        return studentRepository.findAll();
    }
}
