package com.novacinema.SeatReservationId.model.dao;

import com.novacinema.SeatReservationId.model.dto.SeatReservationDTO;
import com.novacinema.seat.model.dto.SeatDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
// 데이터베이스의 좌석 예약 현황(T_SeatReservation) 테이블에 접근하여 좌석 선점 데이터를 조작하는 매퍼 인터페이스
@Mapper
public interface SeatReservationMapper {

    // 시스템 내 등록된 전체 좌석 예약 데이터를 조회하여 반환한다
    List<SeatReservationDTO> getAllReservations();

    // 신규 좌석 예약(선점) 정보를 데이터베이스에 삽입한다
    void insertSeatReservation(SeatReservationDTO seatReservationDTO);

    // 특정 예매 ID와 연결된 좌석의 예약 플래그(reserved) 상태를 갱신한다 (예: 취소 시 false로 변경)
    int updateSeatReservedFlag(@Param("reservationId") String reservationId,
                               @Param("reserved") boolean reserved);

    // 특정 상영 일정 번호(scheduleNum)에 해당하는 모든 좌석 목록과 그 예약 상태를 조인하여 조회한다
    List<SeatDTO> getAllSeatsByScheduleNum(@Param("scheduleNum") int scheduleNum);
    
    // 특정 상영 일정 번호(scheduleNum)에서 아직 예약되지 않은(예약 가능한) 좌석 목록만 필터링하여 조회한다
    List<SeatDTO> getAvailableSeatsBySchedule(@Param("scheduleNum") int scheduleNum);
}


