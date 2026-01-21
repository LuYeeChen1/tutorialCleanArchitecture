package com.java.tutorial.clean_structure.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

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
     * 注意：这里的返回值类型改成了 Greeting 对象。
     * Spring Boot 会自动使用内置的 Jackson 库，
     * 将 Java 对象转换为 JSON 格式发送给浏览器。
     */
    @GetMapping("/hello")
    //就是这里注意看String变成了Greeting
    public Greeting sayhello(){
        return helloService.getGreetingObject();
    }
}
