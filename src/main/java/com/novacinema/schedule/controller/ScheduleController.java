package com.novacinema.schedule.controller;

import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.schedule.model.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 데이터베이스에 등록된 전체 영화 상영 일정(스케줄) 데이터를 조회하여 클라이언트에 반환한다
    @GetMapping("/list")
    public ResponseEntity<List<ScheduleDTO>> getCinemaScheduleDTOList() {
        List<ScheduleDTO> scheduleDTOS = scheduleService.getAllSchedules();
        return ResponseEntity.ok(scheduleDTOS);
    }
}
