package com.novacinema.reservation.model.service;

import com.novacinema.reservation.model.dao.ReservationMapper;
import com.novacinema.reservation.model.dto.GroupedReservationDTO;
import com.novacinema.reservation.model.dto.ReservationDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    /** ✅ 핸드폰 번호 기준 예약 내역 조회 (booking_group_id로 그룹화) */
    public List<GroupedReservationDTO> getGroupedReservationsByPhoneNumber(String phoneNumber) {
        List<ReservationDTO> reservations = reservationMapper.selectReservationsByPhoneNumber(phoneNumber);

        // booking_group_id로 그룹화
        Map<String, List<ReservationDTO>> groupedMap = reservations.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getBookingGroupId() != null ? r.getBookingGroupId() : r.getReservationNum()));

        // GroupedReservationDTO로 변환
        List<GroupedReservationDTO> groupedList = new ArrayList<>();

        for (Map.Entry<String, List<ReservationDTO>> entry : groupedMap.entrySet()) {
            List<ReservationDTO> group = entry.getValue();
            ReservationDTO first = group.get(0);

            GroupedReservationDTO grouped = new GroupedReservationDTO();
            grouped.setBookingGroupId(first.getBookingGroupId());
            grouped.setFirstReservationNum(first.getReservationNum());
            grouped.setPaymentTime(first.getPaymentTime());
            grouped.setState(first.getState());
            grouped.setPhoneNumber(first.getPhoneNumber());
            grouped.setScheduleNum(first.getScheduleNum());
            grouped.setScheduleDTO(first.getScheduleDTO());

            // 좌석 정보 수집
            List<String> seatLabels = new ArrayList<>();
            List<Integer> seatCodes = new ArrayList<>();
            int totalAmount = 0;

            for (ReservationDTO r : group) {
                if (r.getSeatDTO() != null) {
                    String seatLabel = r.getSeatDTO().getRowLabel() + r.getSeatDTO().getColNum();
                    seatLabels.add(seatLabel);
                    seatCodes.add(r.getSeatDTO().getSeatCode());

                    Object price = r.getSeatDTO().getPrice();
                    if (price instanceof Number) {
                        totalAmount += ((Number) price).intValue();
                    }
                }
            }

            grouped.setSeatLabels(seatLabels);
            grouped.setSeatCodes(seatCodes);
            grouped.setTotalAmount(totalAmount);

            groupedList.add(grouped);
        }

        // 결제 시간 최신순 정렬
        groupedList.sort((a, b) -> {
            if (a.getPaymentTime() == null)
                return 1;
            if (b.getPaymentTime() == null)
                return -1;
            return b.getPaymentTime().compareTo(a.getPaymentTime());
        });

        return groupedList;
    }

    /** ✅ 예약 등록 */
    public boolean registerReservation(ReservationDTO dto) {
        int result = reservationMapper.insertReservation(dto);
        return result > 0;
    }
}
