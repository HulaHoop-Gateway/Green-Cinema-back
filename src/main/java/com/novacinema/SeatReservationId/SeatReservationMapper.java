package com.novacinema.SeatReservationId;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SeatReservationMapper {

    // 좌석 예약 등록
    int registerSeatReservation(SeatReservationDTO reservation);

    // 특정 상영 일정의 예약된 좌석 조회
    List<SeatReservationDTO> getReservedSeatsBySchedule(int scheduleNum);

    // 특정 좌석의 예약 정보 조회
    SeatReservationDTO getReservationByScheduleAndSeat(int scheduleNum, int seatCode);

    // 예약 상태 변경 (예매 취소 등)
    int changeReservationStatus(int reservationId, boolean reserved);
}
