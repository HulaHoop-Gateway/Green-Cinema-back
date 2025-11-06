package com.novacinema.reservationCRUD.service;

import com.novacinema.SeatReservationId.model.dao.SeatReservationMapper;
import com.novacinema.SeatReservationId.model.dto.SeatReservationDTO;
import com.novacinema.reservation.model.dao.ReservationMapper;
import com.novacinema.reservation.model.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ReservationCRUDService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private SeatReservationMapper seatReservationMapper;

    /**
     * 예매 및 좌석 예약 처리 (예매 ID 자동 생성 포함)
     */
    @Transactional
    public void reserveSeatAndInsertReservation(ReservationDTO reservationDTO, SeatReservationDTO seatReservationDTO) {
        // 1. 고유 예매 ID 생성
        String reservationId = generateReservationId();

        // 2. DTO에 예매 ID 주입
        reservationDTO.setReservationNum(reservationId);
        seatReservationDTO.setReservationId(reservationId);

        // 3. 예매 정보 저장
        reservationMapper.insertReservation(reservationDTO);

        // 4. 좌석 예약 정보 저장
        seatReservationMapper.insertSeatReservation(seatReservationDTO);
    }

    /**
     * 예매 상태 및 좌석 예약 상태 변경 (예매 취소)
     */
    @Transactional
    public boolean updateReservationState(String reservationNum) {
        String newState = "취소됨";

        int updatedReservation = reservationMapper.updateReservationState(reservationNum, newState); // 🔁 String → int
        int updatedSeat = seatReservationMapper.updateSeatReservedFlag(reservationNum, false);       // 🔁 String → int

        System.out.println("예약번호: " + reservationNum);
        System.out.println("예매 상태 수정 결과: " + updatedReservation);
        System.out.println("좌석 예약 상태 수정 결과: " + updatedSeat);

        return updatedReservation > 0 && updatedSeat > 0;
    }


    /**
     * 오늘 날짜 기준 고유 예매 ID 생성 (형식: yyMMdd0001)
     */
    private String generateReservationId() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")); // 예: 251106
        String prefix = today;

        String maxId = reservationMapper.findMaxReservationIdForToday(prefix + "%");
        int nextSeq = 1;

        if (maxId != null) {
            String lastSeq = maxId.substring(6); // "0001"
            nextSeq = Integer.parseInt(lastSeq) + 1;
        }

        return prefix + String.format("%04d", nextSeq); // 예: 2511060002
    }
}
