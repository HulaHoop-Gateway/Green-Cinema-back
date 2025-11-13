package com.novacinema.reservation.model.dao;

import com.novacinema.reservation.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {

    /** ✅ 전체 예약 조회 */
    List<ReservationDTO> selectAllReservations();

    /** ✅ 핸드폰 번호로 예약 조회 */
    List<ReservationDTO> selectReservationsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    /** ✅ 예약 등록 */
    int insertReservation(ReservationDTO reservationDTO);

    /** ✅ 예약 상태 변경 */
    int updateReservationState(@Param("reservationNum") String reservationNum,
                               @Param("state") String newState);

    /** ✅ 오늘 날짜의 마지막 예약 번호 찾기 */
    String findMaxReservationIdForToday(String s);
}
