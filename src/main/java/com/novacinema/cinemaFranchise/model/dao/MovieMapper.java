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

    void reserveSeat(@Param("scheduleNum") Integer scheduleNum,
                     @Param("seatCode") Integer seatCode,
                     @Param("memberName") String memberName);
}
