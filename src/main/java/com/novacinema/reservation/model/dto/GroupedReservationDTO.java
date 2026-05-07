package com.novacinema.reservation.model.dto;

import com.novacinema.schedule.model.dto.ScheduleDTO;

import java.time.LocalDateTime;
import java.util.List;

// 동일한 결제 세션에서 묶음으로 처리된 여러 좌석의 예약 정보를 하나의 단위로 관리하는 DTO
// 다중 좌석 예매 시 프론트엔드 및 관리자 시스템에 집계된 예약 데이터를 제공하기 위해 사용된다
public class GroupedReservationDTO {
    // 다중 좌석 예매 시 생성되는 공통 결제 단위 그룹 ID
    private String bookingGroupId; 
    
    // 예매된 좌석들의 표면적 라벨 목록 (예: ["A1", "B1"])
    private List<String> seatLabels; 
    
    // 예매된 좌석들의 내부 식별 코드 목록
    private List<Integer> seatCodes; 
    
    // 해당 결제 그룹에 포함된 모든 좌석 예매 금액의 총합
    private int totalAmount; 
    
    // 결제가 정상적으로 승인된 시간
    private LocalDateTime paymentTime; 
    
    // 결제 그룹의 전반적인 상태 (예: '예매완료', '취소됨' 등)
    private String state; 
    
    // 예매를 진행한 회원의 식별용 전화번호
    private String phoneNumber; 
    
    // 예매 대상이 되는 상영 일정의 식별 번호
    private int scheduleNum; 
    
    // 대상 영화, 상영관, 시간 등을 포함하는 상영 일정 상세 연관 객체
    private ScheduleDTO scheduleDTO; 
    
    // 그룹 내 예약들 중 대표성을 띠는 첫 번째 예약 번호 (프론트 노출용)
    private String firstReservationNum; 

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
