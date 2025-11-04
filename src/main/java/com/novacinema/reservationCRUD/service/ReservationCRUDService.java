package com.novacinema.reservationCRUD.service;

import com.novacinema.SeatReservationId.model.dao.SeatReservationMapper;
import com.novacinema.SeatReservationId.model.dto.SeatReservationDTO;
import com.novacinema.reservation.model.dao.ReservationMapper;
import com.novacinema.reservation.model.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ReservationCRUDService {
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



    @Transactional
    public boolean updateReservationState(int reservationNum) {
        String newState = "취소됨"; // 상태는 내부에서 고정하거나 외부에서 받도록 선택 가능

        int updatedReservation = reservationMapper.updateReservationState(reservationNum, newState);
        int updatedSeat = seatReservationMapper.updateSeatReservedFlag(reservationNum, false);

        System.out.println("예약번호: " + reservationNum);
        System.out.println("예매 상태 수정 결과: " + updatedReservation);
        System.out.println("좌석 예약 상태 수정 결과: " + updatedSeat);

        return updatedReservation > 0 && updatedSeat > 0;
    }



}
