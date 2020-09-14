package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok().body(studentService.addStudent(student));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudentList(@RequestParam (required = false) String gender) {
        return ResponseEntity.ok().body(studentService.getStudentList(gender));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(studentService.getStudentById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable Integer id, @RequestBody Student student) {
        return ResponseEntity.ok().body(studentService.updateStudentById(id, student));
    }

}
