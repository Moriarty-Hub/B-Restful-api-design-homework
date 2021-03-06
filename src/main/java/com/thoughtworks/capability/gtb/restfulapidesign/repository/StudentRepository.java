package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class StudentRepository {

    private static Integer NEXT_ID = 1;

    private final Map<Integer, Student> studentMap = new HashMap<>();

    public Student addStudent(Student student) {
        int id = NEXT_ID++;
        student.setId(id);
        studentMap.put(id, student);
        return student;
    }

    public void deleteStudent(Integer id) {
        studentMap.remove(id);
    }

    public Collection<Student> findAll() {
        return studentMap.values();
    }

    public Student findById(Integer id) {
        return studentMap.get(id);
    }

    public void updateStudent(Integer id, Student student) {
        studentMap.replace(id, student);
    }

}
