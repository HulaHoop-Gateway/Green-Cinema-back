package com.novacinema.reservation.model.dao;

import com.novacinema.reservation.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    List<ReservationDTO> selectAllReservations();

    int insertReservation(ReservationDTO reservationDTO);

    // ✅ 회원별 예매 내역 조회 메서드 추가
    List<ReservationDTO> selectByUserCode(int userCode); // 또는 memberCode

}





