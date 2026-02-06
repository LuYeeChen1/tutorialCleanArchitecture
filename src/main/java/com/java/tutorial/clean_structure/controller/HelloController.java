package com.java.tutorial.clean_structure.controller;
import com.java.tutorial.clean_structure.dto.StudentRequestDTO;
import com.java.tutorial.clean_structure.dto.StudentResponseDTO;
import com.java.tutorial.clean_structure.dto.StudentUpdateDTO;
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

    // 新增學生：接收 JSON 數據並建立一筆新的學生資料
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

    // 查詢列表：獲取所有學生的資訊，通常用於前端表格顯示
    //localhost:8080/list
    @GetMapping("/list")
    public List<StudentResponseDTO> listStudents(){
        return helloService.getAllStudentsInfoForFrontend();
    }

    // 修改資料：根據 ID 找到特定學生並更新其內容（如分數、姓名等）
    //localhost:8080/update/1?score=xxx
    @PutMapping("/update/{id}")
    public StudentResponseDTO updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDTO updateRequest) {
        return helloService.updateStudent(id, updateRequest);
    }

    // 刪除學生：根據 ID 移除特定的學生資料
    //localhost:8080/update/1
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        return helloService.deleteStudent(id);
    }
}
