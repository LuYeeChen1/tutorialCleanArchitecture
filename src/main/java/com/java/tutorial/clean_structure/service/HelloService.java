package com.java.tutorial.clean_structure.service;

import com.java.tutorial.clean_structure.dto.StudentRequestDTO;
import com.java.tutorial.clean_structure.dto.StudentResponseDTO;
import com.java.tutorial.clean_structure.dto.StudentUpdateRequestDTO;
import org.springframework.stereotype.Service;
import com.java.tutorial.clean_structure.model.Student;
import com.java.tutorial.clean_structure.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service: 告诉 Spring Boot 这是一个业务逻辑组件。
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
        return Optional.ofNullable(studentRequestDTO)
                // 1. 转换：由专用的方法负责，主流程看不见脏代码
                .map(this::toStudentEntity)
                // 2. 保存：清晰明了
                .map(studentRepository::save)
                // 3. 转换：同样由专用方法负责
                .map(this::toStudentResponseDTO)
                // 4. 收尾
                .orElseThrow(() -> new RuntimeException("Save failed"));
    }

    private Student toStudentEntity(StudentRequestDTO studentRequestDTO) {
        return Student.builder()
                .name(studentRequestDTO.getName())
                .score(studentRequestDTO.getScore())
                .internalNote("Secret")
                .build();
    }

    private StudentResponseDTO toStudentResponseDTO(Student saved) {
        return StudentResponseDTO.builder()
                .id(saved.getId())
                .studentDisplayName(saved.getName())
                .result(saved.getScore() >= 60 ? "Pass" : "Fail")
                .build();
    }

    public StudentResponseDTO updateStudent(Long id, StudentUpdateRequestDTO studentUpdateRequestDTO) {

        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        return Optional.of(existingStudent)
                .map(existing -> {
                    existing.setScore(studentUpdateRequestDTO.getScore());
                    return existing;
                })
                .map(studentRepository::save)
                .map(this::toStudentResponseDTO)
                .orElseThrow(() -> new RuntimeException("Update failed"));
    }

    //注意，这里是String不是Student
    public String deleteStudent(Long id) {
        studentRepository.deleteById(id);
        // 直接调用自带的删除方法
        return "Student with ID " + id + " has been deleted.";
    }


/**    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
 */

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
