package com.java.tutorial.clean_structure.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @RestController: 告诉 Spring 这是一个 Web 接口类
 */
@RestController
public class HelloController {

    /**
     * @GetMapping("/hello"): 绑定 URL 路径
     * 当你在浏览器输入 localhost:8080/hello 时，会触发这个方法
     */
    @GetMapping("/hello")
    public String sayhello(){
        return "Hello, Spring Boot 4.0!";
    }
}
