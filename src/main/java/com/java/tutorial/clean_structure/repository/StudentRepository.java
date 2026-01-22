package com.java.tutorial.clean_structure.repository;

import com.java.tutorial.clean_structure.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 继承 JpaRepository <你要操作的类, 主键类型>
 * 它自带了 save(), findAll(), findById(), delete() 等方法
 */

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
