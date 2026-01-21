package com.java.tutorial.clean_structure.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.tutorial.clean_structure.service.HelloService;

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

    /**
     * @GetMapping("/hello"): 绑定 URL 路径
     * 当你在浏览器输入 localhost:8080/hello 时，会触发这个方法
     */
    @GetMapping("/hello")
    public String sayhello(){
        return helloService.getGreetingMessage();
    }
}
