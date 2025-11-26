package com.novacinema.reservation.model.dto;

import com.novacinema.schedule.model.dto.ScheduleDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 그룹화된 예약 정보 DTO
 * booking_group_id로 묶인 여러 좌석 예약을 하나로 표현
 */
public class GroupedReservationDTO {
    private String bookingGroupId; // 예약 그룹 ID
    private List<String> seatLabels; // 좌석 라벨 목록 (예: ["A1", "B1", "C1"])
    private List<Integer> seatCodes; // 좌석 코드 목록
    private int totalAmount; // 총 금액
    private LocalDateTime paymentTime; // 결제 시간
    private String state; // 예약 상태
    private String phoneNumber; // 전화번호
    private int scheduleNum; // 상영 일정 코드
    private ScheduleDTO scheduleDTO; // 상영 정보 (영화, 영화관 등)
    private String firstReservationNum; // 대표 예약 번호

    public GroupedReservationDTO() {
    }

    // Getters and Setters
    public String getBookingGroupId() {
        return bookingGroupId;
    }

    public void setBookingGroupId(String bookingGroupId) {
        this.bookingGroupId = bookingGroupId;
    }

    public List<String> getSeatLabels() {
        return seatLabels;
    }

    public void setSeatLabels(List<String> seatLabels) {
        this.seatLabels = seatLabels;
    }

    public List<Integer> getSeatCodes() {
        return seatCodes;
    }

    public void setSeatCodes(List<Integer> seatCodes) {
        this.seatCodes = seatCodes;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getScheduleNum() {
        return scheduleNum;
    }

    public void setScheduleNum(int scheduleNum) {
        this.scheduleNum = scheduleNum;
    }

    public ScheduleDTO getScheduleDTO() {
        return scheduleDTO;
    }

    public void setScheduleDTO(ScheduleDTO scheduleDTO) {
        this.scheduleDTO = scheduleDTO;
    }

    public String getFirstReservationNum() {
        return firstReservationNum;
    }

    public void setFirstReservationNum(String firstReservationNum) {
        this.firstReservationNum = firstReservationNum;
    }
}
