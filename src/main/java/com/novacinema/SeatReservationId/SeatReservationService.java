package com.novacinema.SeatReservationId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatReservationService {

    private final SeatReservationMapper seatReservationMapper;

    public SeatReservationService(SeatReservationMapper seatReservationMapper) {
        this.seatReservationMapper = seatReservationMapper;
    }

    // ✅ 좌석 예약 등록
    public int registerSeatReservation(SeatReservationDTO reservation) {
        return seatReservationMapper.registerSeatReservation(reservation);
    }

    // ✅ 특정 상영 일정의 예약된 좌석 조회
    public List<SeatReservationDTO> getReservedSeatsBySchedule(int scheduleNum) {
        return seatReservationMapper.getReservedSeatsBySchedule(scheduleNum);
    }

    // ✅ 특정 좌석의 예약 정보 조회
    public SeatReservationDTO getReservationByScheduleAndSeat(int scheduleNum, int seatCode) {
        return seatReservationMapper.getReservationByScheduleAndSeat(scheduleNum, seatCode);
    }

    // ✅ 예약 상태 변경 (예매 취소 등)
    public int changeReservationStatus(int reservationId, boolean reserved) {
        return seatReservationMapper.changeReservationStatus(reservationId, reserved);
    }
}


