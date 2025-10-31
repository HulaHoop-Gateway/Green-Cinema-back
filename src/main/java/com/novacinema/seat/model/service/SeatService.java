package com.novacinema.seat.model.service;

import com.novacinema.seat.model.dao.SeatMapper;
import com.novacinema.seat.model.dto.SeatDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatService {
    private final SeatMapper seatMapper;

    public SeatService( SeatMapper seatMapper) {
        this.seatMapper = seatMapper;
    }
    public List<SeatDTO> getAllSeats(){
        return seatMapper.selectAllSeats();
    }
    public List<SeatDTO> getAllSeatsBySchedule(int scheduleNum) {
        return seatMapper.selectAllSeatsBySchedule(scheduleNum);
    }
}

