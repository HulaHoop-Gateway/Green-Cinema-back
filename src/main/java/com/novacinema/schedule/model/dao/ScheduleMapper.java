package com.novacinema.schedule.model.dao;


import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.seat.model.dto.SeatDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<ScheduleDTO> selectAllSchedules();
    List<ScheduleDTO> findSchedulesByBranchNum(@Param("branchNum") String branchNum);


}
