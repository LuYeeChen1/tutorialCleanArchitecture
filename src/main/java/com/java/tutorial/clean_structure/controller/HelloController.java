package com.java.tutorial.clean_structure.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.java.tutorial.clean_structure.service.HelloService;
import com.java.tutorial.clean_structure.model.Greeting;

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
     * @PathVariable: 告诉 Spring 从 URL 路径中提取 {name} 的值。
     * URL 样例: http://localhost:8080/hello/Sakura
     */
    @GetMapping("/hello/{name}")
    //就是这里注意看String变成了Greeting
    public Greeting sayhello(@PathVariable(value = "name") String name){
        return helloService.getGreetingWithParam(name);
    }
}
