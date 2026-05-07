package com.novacinema.schedule.model.service;

import com.novacinema.reservation.model.dao.ReservationMapper;
import com.novacinema.reservation.model.dto.ReservationDTO;
import com.novacinema.schedule.model.dao.ScheduleMapper;
import com.novacinema.schedule.model.dto.ScheduleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

// 영화 상영 일정(스케줄) 관련 비즈니스 로직을 처리하는 서비스 계층
@Service
public class ScheduleService {
    private final ScheduleMapper scheduleMapper;

    public ScheduleService(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    // 데이터베이스에 저장된 모든 상영 일정 목록을 조회한다
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleMapper.selectAllSchedules();
    }

}