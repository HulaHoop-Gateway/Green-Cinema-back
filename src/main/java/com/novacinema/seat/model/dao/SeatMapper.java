package com.novacinema.seat.model.dao;


import com.novacinema.seat.model.dto.SeatDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 데이터베이스의 물리적 좌석(T_Seat) 테이블에 접근하여 데이터를 조작하는 매퍼 인터페이스
@Mapper
public interface SeatMapper {
    // 데이터베이스에 등록된 전체 상영관의 모든 좌석 기준 정보를 조회한다
    List<SeatDTO> selectAllSeats();
    
    // 특정 상영 일정 번호(scheduleNum)를 기반으로 해당 상영관에 배치된 전체 좌석 목록을 조회한다
    List<SeatDTO> selectAllSeatsBySchedule(@Param("scheduleNum") int scheduleNum);

}
