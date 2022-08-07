package com.octopus.controller;

import com.octopus.domain.dto.*;
import com.octopus.domain.dto.response.CalenderRes;
import com.octopus.domain.dto.response.CalenderUserInfoRes;
import com.octopus.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 비활성화 미션 생성
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createMission(@Valid @RequestBody MissionCreateDto missionCreateDto) {
        missionService.createMission(missionCreateDto);
        return ResponseEntity.ok().build();
    }

    //new 미션 5개 가져오기
    @GetMapping("/new")
    public ResponseEntity<List> newMissions() {
        return ResponseEntity.ok(missionService.getNewMissions());
    }

    //hot 미션 5개 가져오기
    @GetMapping("/hot")
    public ResponseEntity<List> hotMissions() {
        return new ResponseEntity<>(missionService.getHotMissions(), HttpStatus.OK);
    }
}
