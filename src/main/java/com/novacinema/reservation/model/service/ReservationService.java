package com.novacinema.reservation.model.service;

import com.novacinema.reservation.model.dao.ReservationMapper;
import com.novacinema.reservation.model.dto.ReservationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    /** ✅ 전체 예약 조회 */
    public List<ReservationDTO> getAllReservations() {
        return reservationMapper.selectAllReservations();
    }

    /** ✅ 핸드폰 번호 기준 예약 내역 조회 */
    public List<ReservationDTO> getReservationsByPhoneNumber(String phoneNumber) {
        return reservationMapper.selectReservationsByPhoneNumber(phoneNumber);
    }

    /** ✅ 예약 등록 */
    public boolean registerReservation(ReservationDTO dto) {
        int result = reservationMapper.insertReservation(dto);
        return result > 0;
    }
}
