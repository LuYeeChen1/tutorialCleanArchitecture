package com.java.tutorial.clean_structure.dto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder  // <--- 核心！加上这个注解
public class StudentResponseDTO {
    // 我们把 id 和 name 给前端，但不给 internalNote
    private Long id;
    private String studentDisplayName; // 故意改个名字，让前端更好理解
    private String result; // 根据分数生成的“及格/不及格”状态
}
