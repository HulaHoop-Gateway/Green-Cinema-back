package com.novacinema.schedule.model.controller;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.cinemaFranchise.model.service.CinemaFranchiseService;
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

    /*db에서 얻어온 값  전달*/
    @GetMapping("/list")
    public ResponseEntity<List<ScheduleDTO>> getCinemaScheduleDTOList() {
        List<ScheduleDTO> scheduleDTOS = scheduleService.getAllSchedules();
        return ResponseEntity.ok(scheduleDTOS);
    }
}
