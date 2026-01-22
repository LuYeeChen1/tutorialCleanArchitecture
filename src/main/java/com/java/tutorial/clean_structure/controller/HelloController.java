package com.java.tutorial.clean_structure.controller;
import com.java.tutorial.clean_structure.model.Student;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    //localhost:8080/add?name=Sakura&score=60
    @GetMapping("/add")
    public Student addStudent(@RequestParam String name, @RequestParam int score){
        return helloService.saveStudent(name, score);
    }

    //localhost:8080/list
    @GetMapping("/list")
    public List<Student> listStudents(){
        return helloService.getAllStudents();
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
