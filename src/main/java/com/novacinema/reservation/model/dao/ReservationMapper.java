package com.novacinema.reservation.model.dao;

import com.novacinema.reservation.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 데이터베이스의 예매(T_Reservation) 테이블에 접근하여 예매 생성, 조회, 상태 변경 등을 수행하는 매퍼 인터페이스
@Mapper
public interface ReservationMapper {

        // 시스템 내 전체 예매 내역을 조회한다
        List<ReservationDTO> selectAllReservations();

        // 특정 핸드폰 번호를 기준으로 해당 사용자의 모든 예매 내역을 조회한다
        List<ReservationDTO> selectReservationsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

        // 예매 고유 번호를 활용하여 단건 예매 내역을 상세 조회한다
        ReservationDTO selectReservationByNum(@Param("reservationNum") String reservationNum);

        // 신규 예매 정보를 데이터베이스에 등록한다
        int insertReservation(ReservationDTO reservationDTO);

        // 특정 예매 번호의 진행 상태(예: '취소됨')를 갱신한다
        int updateReservationState(@Param("reservationNum") String reservationNum,
                        @Param("state") String newState);

        // 새로운 예매 번호 채번을 위해, 오늘 날짜(prefix) 기준으로 가장 마지막에 생성된 예약 번호를 조회한다
        String findMaxReservationIdForToday(@Param("prefix") String prefix);

        // 관리자 서버에서 발급한 결제 트랜잭션 번호(transactionNum)를 기반으로 관련 예매 내역들을 조회한다
        List<ReservationDTO> findByTransactionNum(@Param("transactionNum") Long transactionNum);

        // 일괄 예매(결제 그룹 단위) 처리 시, 해당 그룹의 모든 예약 건에 외부 트랜잭션 번호를 갱신한다
        int updateTransactionNum(@Param("bookingGroupId") String bookingGroupId,
                        @Param("transactionNum") Long transactionNum);

        // 결제 그룹 ID가 누락된 예외 상황에서, 핸드폰 번호와 상영 스케줄을 조합하여 외부 트랜잭션 번호를 갱신한다 (안전 장치용)
        int updateTransactionNumByScheduleAndPhone(@Param("phoneNumber") String phoneNumber,
                        @Param("scheduleNum") int scheduleNum,
                        @Param("transactionNum") Long transactionNum);
}
