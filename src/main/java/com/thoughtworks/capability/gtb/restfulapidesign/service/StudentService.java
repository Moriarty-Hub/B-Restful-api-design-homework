package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.addStudent(student);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteStudent(id);
    }

    public List<Student> getStudentList(String gender) {
        List<Student> studentList = new LinkedList<>(studentRepository.findAll());
        if (gender == null) {
            return studentList;
        } else {
            return studentList.stream()
                    .filter(student -> student.getGender().getName().equals(gender))
                    .collect(Collectors.toList());
        }
    }
}
