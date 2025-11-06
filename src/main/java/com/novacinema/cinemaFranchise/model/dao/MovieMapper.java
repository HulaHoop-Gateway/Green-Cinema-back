package com.novacinema.cinemaFranchise.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface MovieMapper {

    List<Map<String, Object>> findNearestCinemas();

    List<Map<String, Object>> findNowPlaying(@Param("branchName") String branchName);

    List<Map<String, Object>> findSeatStatus(@Param("scheduleNum") Integer scheduleNum);

    // ✨ 수정: HOLD 상태 예약을 위해 memberName 파라미터를 제거했습니다.
    // T_SeatReservation 테이블은 scheduleNum과 seatCode만 필요합니다.
    void reserveSeat(@Param("scheduleNum") Integer scheduleNum,
                     @Param("seatCode") Integer seatCode);
}