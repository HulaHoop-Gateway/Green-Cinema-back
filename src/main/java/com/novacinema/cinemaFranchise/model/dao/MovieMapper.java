package com.novacinema.cinemaFranchise.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

// 영화 목록, 주변 영화관, 좌석 상태 등 영화 상영과 관련된 전반적인 데이터를 조작하는 매퍼 인터페이스
@Mapper
public interface MovieMapper {

    // 사용자의 현재 위치 또는 기본 설정에 기반하여 가장 가까운 영화관 지점 목록을 조회한다
    List<Map<String, Object>> findNearestCinemas();

    // 특정 지점(branchName)에서 현재 상영 중인 영화 목록과 해당 상영 일정을 조회한다
    List<Map<String, Object>> findNowPlaying(@Param("branchName") String branchName);

    // 주어진 상영 일정(scheduleNum)에 대한 전체 좌석의 실시간 예약 상태 현황을 조회한다
    List<Map<String, Object>> findSeatStatus(@Param("scheduleNum") Integer scheduleNum);

    // 특정 상영 일정과 좌석 코드를 기준으로 좌석 예약 상태를 반영하는 쿼리 메서드
    // T_SeatReservation 테이블을 조작하며, 트랜잭션 내에서 처리되어야 한다
    void reserveSeat(@Param("scheduleNum") Integer scheduleNum,
                     @Param("seatCode") Integer seatCode);
}