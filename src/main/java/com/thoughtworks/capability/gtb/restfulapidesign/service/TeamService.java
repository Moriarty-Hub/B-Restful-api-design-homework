package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class TeamService {

    private final StudentRepository studentRepository;
    private final TeamRepository teamRepository;

    public TeamService(StudentRepository studentRepository, TeamRepository teamRepository) {
        this.studentRepository = studentRepository;
        this.teamRepository = teamRepository;
    }

    public List<Team> regroupTeams() {
        int numberOfGroup = teamRepository.getNumberOfGroup();

        List<List<Student>> listOfStudentList = new ArrayList<>();
        for (int i = 0; i < numberOfGroup; i++) {
            listOfStudentList.add(new LinkedList<>());
        }

        List<Student> allStudentList = new LinkedList<>(studentRepository.findAll());
        Random random = new Random();
        for (int i = 0; !allStudentList.isEmpty(); i++) {
            listOfStudentList.get(i % numberOfGroup).add(allStudentList.remove(random.nextInt(allStudentList.size())));
        }

        for (int i = 0; i < numberOfGroup; i++) {
            teamRepository.updateStudentListById(i + 1, listOfStudentList.get(i));
        }

        return teamRepository.findAll();
    }
}
