package com.novacinema.schedule.model.dao;


import com.novacinema.schedule.model.dto.ScheduleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<ScheduleDTO> selectAllSchedules();

}
