package com.novacinema.SeatReservationId.model.service;
import com.novacinema.SeatReservationId.model.dao.SeatReservationMapper;
import com.novacinema.SeatReservationId.model.dto.SeatReservationDTO;
import com.novacinema.seat.model.dto.SeatDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatReservationService {

    private final SeatReservationMapper seatReservationMapper;

    public SeatReservationService(SeatReservationMapper seatReservationMapper) {
        this.seatReservationMapper = seatReservationMapper;
    }

    public List<SeatReservationDTO> getAllSeatReservations() {
        return seatReservationMapper.getAllReservations();
    }
    public List<SeatDTO> getAvailableSeats(int scheduleNum) {
        return seatReservationMapper.getAvailableSeatsBySchedule(scheduleNum);
    }




}


