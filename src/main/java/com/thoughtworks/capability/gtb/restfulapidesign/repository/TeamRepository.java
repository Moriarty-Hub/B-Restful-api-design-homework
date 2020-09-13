package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamRepository {

    private static final int NUMBER_OF_GROUP = 6;
    private static final String PREFIX_OF_DEFAULT_TEAM_NAME = "Team ";
    private final List<Team> teamList = new ArrayList<>(NUMBER_OF_GROUP);

    public TeamRepository() {
        for (int i = 0; i < NUMBER_OF_GROUP; i++) {
            teamList.add(Team.builder().id(i + 1).name(PREFIX_OF_DEFAULT_TEAM_NAME + (i + 1)).build());
        }
    }

    public List<Team> findAll() {
        return teamList;
    }

    public void updateStudentListById(Integer id, List<Student> studentList) {
        teamList.get(id - 1).setStudentList(studentList);
    }

    public int getNumberOfGroup() {
        return NUMBER_OF_GROUP;
    }

    public void updateTeamNameById(Integer id, String teamName) {
        teamList.get(id - 1).setName(teamName);
    }

    public Team findTeamById(Integer id) {
        return teamList.get(id - 1);
    }

}
