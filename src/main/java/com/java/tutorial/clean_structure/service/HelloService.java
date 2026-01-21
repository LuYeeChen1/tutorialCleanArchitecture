package com.java.tutorial.clean_structure.service;
import com.java.tutorial.clean_structure.model.Greeting;
import org.springframework.stereotype.Service;

/**
 * @Service: 告诉 Spring Boot 这是一个业务逻辑组件。
 * 在 2026 年的 Spring Boot 4.0 中，这些组件会自动被纳入模块化管理，
 * 从而实现极速启动。
 */
@Service
public class HelloService {
    //注意，这里已经是Greeting而不是String
    public Greeting getGreetingObject()
    {
        // 创建一个 Greeting 对象并填入数据
        // 这里的 new 是允许的，因为它是数据载体 (Model)
        return new Greeting(
                "Welcome to Spring Boot 4.0!",
                "Success",
                200
        );
    }
}
