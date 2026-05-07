package com.novacinema.schedule.model.dao;

import com.novacinema.schedule.model.dto.ScheduleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 데이터베이스의 상영 일정(T_Schedule) 테이블에 접근하여 데이터를 조작하는 매퍼 인터페이스
@Mapper
public interface ScheduleMapper {

    // 시스템에 등록된 전체 영화 상영 일정을 조회하여 반환한다
    List<ScheduleDTO> selectAllSchedules();

    // 특정 영화관 지점(branchNum)에 편성된 모든 상영 일정을 조회한다
    List<ScheduleDTO> findSchedulesByBranchNum(@Param("branchNum") String branchNum);

    // 특정 영화관 지점(branchNum)과 날짜(screeningDate) 조건을 모두 만족하는 상영 일정을 조회한다
    // (오늘, 내일, 특정 날짜 등 동적 필터링을 지원)
    List<ScheduleDTO> findSchedulesByBranchNumAndDate(
            @Param("branchNum") String branchNum,
            @Param("screeningDate") String screeningDate);

    // 관리자 서버 결제 연동을 위해 특정 상영 일정 번호로부터 해당 상영관의 가맹점 코드(merchantCode)를 역추적하여 조회한다
    String findMerchantCodeByScheduleNum(@Param("scheduleNum") int scheduleNum);

    // 특정 상영 일정 번호(scheduleNum)를 기준으로 해당 상영 일정의 상세 정보를 단건 조회한다
    ScheduleDTO selectScheduleByNum(@Param("scheduleNum") int scheduleNum);
}
