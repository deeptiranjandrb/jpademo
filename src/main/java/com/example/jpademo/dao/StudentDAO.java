package com.example.jpademo.dao;

import com.example.jpademo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);

    Student findById(Integer id);

    List<Student> findAll();
    List<Student> findByFirstname(String theFirstName);


    void update(Student theStudent);

    void  delete(Integer id);

    int deleteAll();
}
