
package com.novacinema.theater.model.dao;


import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.theater.model.dto.TheaterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TheaterMapper {
    List<TheaterDTO> selectAllTheaters();

}
