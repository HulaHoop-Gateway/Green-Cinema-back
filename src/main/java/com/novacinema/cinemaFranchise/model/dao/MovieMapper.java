package com.novacinema.cinemaFranchise.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MovieMapper {

    // 🎥 가까운 영화관 조회
    List<Map<String, Object>> findNearestCinemas(@Param("userId") String userId);

    // 🎬 기존: 모든 상영 영화 조회 (필요 시 유지 가능)
    List<Map<String, Object>> findNowPlaying(@Param("branchName") String branchName);


    // 🎟️ 좌석 상태 조회
    List<Map<String, Object>> findSeatStatus(@Param("branchName") String branchName,
                                             @Param("movieTitle") String movieTitle);

    // 🎫 좌석 예약
    void reserveSeat(@Param("memberName") String memberName,
                     @Param("branchName") String branchName,
                     @Param("movieTitle") String movieTitle,
                     @Param("seat") String seat);
}
