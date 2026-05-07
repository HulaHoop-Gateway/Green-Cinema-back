package com.novacinema.user.model.dao;

import com.novacinema.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

// 데이터베이스의 사용자(T_Member) 테이블에 접근하여 데이터를 조작하는 매퍼 인터페이스
@Mapper
public interface UserMapper {
    // 주어진 아이디와 일치하는 사용자 정보를 데이터베이스에서 조회한다
    UserDTO findById(String id);

    // 시스템에 등록된 전체 사용자 목록을 조회하여 리스트로 반환한다
    List<UserDTO> selectAllUsers();

    // 입력받은 핸드폰 번호를 기준으로 해당 사용자의 정보를 조회한다 (예약 내역 연동 등에 활용)
    UserDTO findByPhoneNumber(String phoneNumber);
}
