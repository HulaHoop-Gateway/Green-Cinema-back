package com.novacinema.info.model.dao;


import com.novacinema.info.model.dto.InfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 데이터베이스의 영화 기본 정보(T_Info) 테이블에 접근하여 데이터를 조작하는 매퍼 인터페이스
@Mapper
public interface InfoMapper {
    // 시스템에 등록된 전체 영화 메타데이터 목록을 조회하여 반환한다
    List<InfoDTO> selectAllInfo();
}
