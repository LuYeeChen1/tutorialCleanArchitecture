package com.java.tutorial.clean_structure.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor // JPA 必须需要一个无参构造函数. Example public Student() {}
@AllArgsConstructor //mean public Student (xxx){this.xxx = xxx}
@Entity // 告诉 JPA：这个类对应数据库里的一张表
@Table(name = "students") // 数据库里的表名叫做 students
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 让数据库自动生成 ID (1, 2, 3...)
    private Long id; //必须是大写的Long，而不是long
    private String name;
    private int score;
    // 假设这是一个秘密字段，绝对不能让前端看到
    private String internalNote;
}
