package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PutMapping("/regroup")
    public ResponseEntity<List<Team>> regroupAllTeams() {
        return ResponseEntity.ok().body(teamService.regroupTeams());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Team> renameTeam(@PathVariable Integer id, @RequestBody Team teamName) {
        return ResponseEntity.ok().body(teamService.renameTeam(id, teamName.getName()));
    }

    @GetMapping
    public ResponseEntity<List<Team>> getTeamList() {
        return ResponseEntity.ok().body(teamService.getTeamList());
    }

}
