
package com.novacinema.theater.model.dao;


import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.theater.model.dto.TheaterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 데이터베이스의 상영관(T_Theater) 테이블에 접근하여 데이터를 조작하는 매퍼 인터페이스
@Mapper
public interface TheaterMapper {
    // 데이터베이스에 저장된 모든 상영관의 목록을 조회하여 반환한다
    List<TheaterDTO> selectAllTheaters();
}
