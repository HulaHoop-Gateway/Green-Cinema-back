package com.novacinema.schedule.model.dao;

import com.novacinema.schedule.model.dto.ScheduleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    List<ScheduleDTO> selectAllSchedules();

    List<ScheduleDTO> findSchedulesByBranchNum(@Param("branchNum") String branchNum);

    // 🔥 날짜 필터링 적용된 스케줄 조회 (오늘/내일/특정 날짜)
    List<ScheduleDTO> findSchedulesByBranchNumAndDate(
            @Param("branchNum") String branchNum,
            @Param("screeningDate") String screeningDate);

    // ⭐ scheduleNum 으로 merchant_code 조회 추가
    String findMerchantCodeByScheduleNum(@Param("scheduleNum") int scheduleNum);

    // ⭐ scheduleNum 으로 상세 스케줄 조회
    ScheduleDTO selectScheduleByNum(@Param("scheduleNum") int scheduleNum);
}
