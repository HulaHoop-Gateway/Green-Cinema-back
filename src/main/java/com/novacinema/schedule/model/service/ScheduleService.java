package com.novacinema.schedule.model.service;

import com.novacinema.reservation.model.dao.ReservationMapper;
import com.novacinema.reservation.model.dto.ReservationDTO;
import com.novacinema.schedule.model.dao.ScheduleMapper;
import com.novacinema.schedule.model.dto.ScheduleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleMapper scheduleMapper;

    public ScheduleService(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleMapper.selectAllSchedules();
    }

}