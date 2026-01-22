package com.java.tutorial.clean_structure.service;

import com.java.tutorial.clean_structure.dto.StudentResponseDTO;
import org.springframework.stereotype.Service;
import com.java.tutorial.clean_structure.model.Student;
import com.java.tutorial.clean_structure.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

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
        Student newStudent = new Student(null,name, score, "Secret");
        // .save() 是 JpaRepository 自带的，它会自动生成 INSERT INTO 语句
        return studentRepository.save(newStudent);
    }

    public Student updateStudent(Long id, int newScore) {
        // 1. 先通过 ID 找到学生。如果找不到，这里简单的返回 null (实际开发会报错)
        Student existingstudent = studentRepository.findById(id).orElse(null);

        if (existingstudent != null) {
            // 2. 修改分数
            existingstudent.setScore(newScore);

            // 3. 重新保存。JPA 看到有 ID，就会执行更新操作
            return studentRepository.save(existingstudent);
        }
        return null;
    }

    //注意，这里是String不是Student
    public String deleteStudent(Long id) {
        studentRepository.deleteById(id);
        // 直接调用自带的删除方法
        return "Student with ID " + id + " has been deleted.";
    }

    // 获取所有学生的方法
    public List<Student> getAllStudents() {
        // .findAll() 也是自带的，它会自动生成 SELECT * FROM 语句
        return studentRepository.findAll();
    }

    public List<StudentResponseDTO> getAllStudentsInfoForFrontend() {
        return studentRepository.findAll().stream()
                //s is come from Student model. Student Model come from JpaRepository<Student,Long>
                .map(s -> StudentResponseDTO.builder()
                        .id(s.getId())
                        .studentDisplayName(s.getName())
                        .status(s.getScore() >= 60 ? "Passed" : "Failed")
                        .build())
                .collect(Collectors.toList());
    }
}
