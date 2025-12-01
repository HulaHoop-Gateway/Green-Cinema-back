package com.novacinema.reservation.model.dao;

import com.novacinema.reservation.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {

        // 전체 예매 조회
        List<ReservationDTO> selectAllReservations();

        // 전화번호 기반 예매 조회
        List<ReservationDTO> selectReservationsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

        // 예매 번호로 단일 조회 (⭐ 추가)
        ReservationDTO selectReservationByNum(@Param("reservationNum") String reservationNum);

        // 예매 등록
        int insertReservation(ReservationDTO reservationDTO);

        // 예매 상태 업데이트
        int updateReservationState(@Param("reservationNum") String reservationNum,
                        @Param("state") String newState);

        // 오늘 날짜 기준 최대 예매번호 조회
        String findMaxReservationIdForToday(@Param("prefix") String prefix);

        // 트랜잭션 번호로 예매 조회 (⭐ 추가)
        ReservationDTO findByTransactionNum(@Param("transactionNum") Long transactionNum);

        // 트랜잭션 번호 업데이트 (⭐ 추가)
        int updateTransactionNum(@Param("bookingGroupId") String bookingGroupId,
                        @Param("transactionNum") Long transactionNum);

        // 트랜잭션 번호 업데이트 (PhoneNumber + ScheduleNum) (⭐ 추가 - Fallback용)
        int updateTransactionNumByScheduleAndPhone(@Param("phoneNumber") String phoneNumber,
                        @Param("scheduleNum") int scheduleNum,
                        @Param("transactionNum") Long transactionNum);
}
