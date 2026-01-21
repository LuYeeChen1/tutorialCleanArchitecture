package com.java.tutorial.clean_structure.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @RequestParam(value = "name", defaultValue = "Guest"):
     * 1. 去 URL 找名字叫 "name" 的参数。
     * 2. 如果用户没传这个参数，默认值就是 "Guest"。
     */
    @GetMapping("/hello")
    //就是这里注意看String变成了Greeting
    public Greeting sayhello(@RequestParam(value = "name", defaultValue = "Guest") String name){
        return helloService.getGreetingWithParam(name);
    }
}
