package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IntegrationController {

    private final StudentService studentService;
    private final TeamService teamService;

    public IntegrationController(StudentService studentService, TeamService teamService) {
        this.studentService = studentService;
        this.teamService = teamService;
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok().body(studentService.addStudent(student));
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudentList(@RequestParam (required = false) String gender) {
        return ResponseEntity.ok().body(studentService.getStudentList(gender));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(studentService.getStudentById(id));
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable Integer id, @RequestBody Student student) {
        return ResponseEntity.ok().body(studentService.updateStudentById(id, student));
    }

    @PutMapping("/teams/regroup")
    public ResponseEntity<List<Team>> regroupAllTeams() {
        return ResponseEntity.ok().body(teamService.regroupTeams());
    }
}
