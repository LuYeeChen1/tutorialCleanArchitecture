package com.java.tutorial.clean_structure.service;

import com.java.tutorial.clean_structure.dto.StudentRequestDTO;
import com.java.tutorial.clean_structure.dto.StudentResponseDTO;
import com.java.tutorial.clean_structure.dto.StudentUpdateDTO;
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
    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO) {
        // 【搬运 1】：把 DTO 的数据搬进 Entity (准备存入仓库)
        // 注意：这里我们手动把前端传来的数据塞进数据库实体
        Student newStudent = new Student();
        newStudent.setName(studentRequestDTO.getName());
        newStudent.setScore(studentRequestDTO.getScore());
        newStudent.setInternalNote("Secret"); // 秘密数据直接在这里写死，前端不需要传

        // 【执行保存】：调用仓库保存，拿到带 ID 的结果
        Student savedStudent = studentRepository.save(newStudent);

        // 【搬运 2】：把保存后的 Entity 搬进 Response DTO (准备端给前端)
        StudentResponseDTO response = new StudentResponseDTO();
        response.setId(savedStudent.getId());
        response.setStudentDisplayName(savedStudent.getName());

        // 增加一点逻辑转换
        String gradeResult = savedStudent.getScore() >= 60 ? "PASS" : "FAIL";
        response.setResult(gradeResult);

        return response;
    }

    public StudentResponseDTO updateStudent(Long id, StudentUpdateDTO updateData) {
        // 1. 【查询】使用 findById 找到数据库里的原始实体
        // 这里使用 Optional 盒子，如果没找到就返回 null (或抛出异常)
        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent != null) {
            // 2. 【修改】将 DTO 里的新分数搬运到实体中
            existingStudent.setScore(updateData.getNewScore());

            // 3. 【保存】调用 save。JPA 识别到已有 ID，会自动执行 UPDATE
            Student updatedStudent = studentRepository.save(existingStudent);

            // 4. 【转换】将更新后的实体包装成 Response DTO 返回
            StudentResponseDTO response = new StudentResponseDTO();
            response.setId(updatedStudent.getId());
            response.setStudentDisplayName(updatedStudent.getName());
            response.setResult(updatedStudent.getScore() >= 60 ? "PASS" : "FAIL");

            return response;
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
                        .result(s.getScore() >= 60 ? "Passed" : "Failed")
                        .build())
                .collect(Collectors.toList());
    }
}
