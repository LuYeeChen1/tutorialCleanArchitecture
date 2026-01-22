package com.java.tutorial.clean_structure.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor // JPA 必须需要一个无参构造函数
@AllArgsConstructor
@Entity // 告诉 JPA：这个类对应数据库里的一张表
@Table(name = "students") // 数据库里的表名叫做 students
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 让数据库自动生成 ID (1, 2, 3...)
    private long id;

    private String name;
    private int score;
}
