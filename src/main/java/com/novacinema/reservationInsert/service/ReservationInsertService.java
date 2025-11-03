package com.novacinema.reservationInsert.service;

import com.novacinema.SeatReservationId.model.dao.SeatReservationMapper;
import com.novacinema.SeatReservationId.model.dto.SeatReservationDTO;
import com.novacinema.reservation.model.dao.ReservationMapper;
import com.novacinema.reservation.model.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ReservationInsertService {
    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private SeatReservationMapper seatReservationMapper;

    @Transactional
    public void reserveSeatAndInsertReservation(ReservationDTO reservationDTO, SeatReservationDTO seatReservationDTO) {
        // 1. 예매 정보 저장
        reservationMapper.insertReservation(reservationDTO);

        // 2. 좌석 예약 정보 저장
        seatReservationMapper.insertSeatReservation(seatReservationDTO);

    }

}
