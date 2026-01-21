package com.java.tutorial.clean_structure.service;
import org.springframework.stereotype.Service;

/**
 * @Service: 告诉 Spring Boot 这是一个业务逻辑组件。
 * 在 2026 年的 Spring Boot 4.0 中，这些组件会自动被纳入模块化管理，
 * 从而实现极速启动。
 */
@Service
public class HelloService {
    public String getGreetingMessage()
    {
        return "Hello from the Service Layer! This is Clean Architecture.";
    }
}
