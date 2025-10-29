package com.novacinema.reservation.model.service;

import com.novacinema.reservation.model.dao.ReservationMapper;
import com.novacinema.reservation.model.dto.ReservationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationService {
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationDTO> getAllReservations() {
        return reservationMapper.selectAllReservation();
    }
    public ReservationDTO getReservationById(int reservationNum){
        return reservationMapper.selectReservationById(reservationNum);
    }

    public List<ReservationDTO> getReservationByUserCode(int userCode) {
        return reservationMapper.findByUserCode(userCode);
    }
}