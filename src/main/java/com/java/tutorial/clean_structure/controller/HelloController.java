package com.java.tutorial.clean_structure.controller;
import com.java.tutorial.clean_structure.dto.StudentRequestDTO;
import com.java.tutorial.clean_structure.dto.StudentResponseDTO;
import com.java.tutorial.clean_structure.model.Student;
import org.springframework.web.bind.annotation.*;

import com.java.tutorial.clean_structure.service.HelloService;

import java.util.List;

/**
 * @RestController: 告诉 Spring 这是一个 Web 接口类
 */
@RestController
public class HelloController {
    // 1. 定义依赖（插座）
    // 使用 final 确保这个依赖在初始化后不会被改变
    private final HelloService helloService;


    // 2. 构造函数注入（插头）
    // Spring Boot 4.0 会自动找到 HelloService 的实例并塞进来
    public HelloController(HelloService helloService){
        this.helloService = helloService;
    }

    //@RequesParam = ?xxx

    //localhost:8080/add?name=Sakura&score=60
    /**
     * @PostMapping: 告诉 Spring 这个接口是用来“提交/新增”数据的。
     * @RequestBody: 告诉 Spring 去请求体里找 JSON 数据，并自动填入 StudentRequest 对象。
     */
    @PostMapping("/add")
    public StudentResponseDTO addStudent(@RequestBody StudentRequestDTO request) {
        // 调用已经 DTO 化后的 Service 方法
        return helloService.saveStudent(request);
    }

    //localhost:8080/list
    @GetMapping("/list")
    public List<StudentResponseDTO> listStudents(){
        return helloService.getAllStudentsInfoForFrontend();
    }

    //localhost:8080/update/1?score=xxx
    @GetMapping("/update/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestParam int score){
        return helloService.updateStudent(id, score);
    }

    //localhost:8080/update/1
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        return helloService.deleteStudent(id);
    }
}
