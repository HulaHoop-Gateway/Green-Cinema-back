package com.novacinema.reservation.model.dao;

import com.novacinema.reservation.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    List<ReservationDTO> selectAllReservation();
    ReservationDTO selectReservationById(int reservationNum);

    // ✅ 회원별 예약 조회 추가
    List<ReservationDTO> findByUserCode(int userCode);
}