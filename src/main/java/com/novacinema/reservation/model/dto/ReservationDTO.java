package com.novacinema.reservation.model.dto;

import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.seat.model.dto.SeatDTO;
import com.novacinema.user.model.dto.UserDTO;

import java.time.LocalDateTime;

public class ReservationDTO {
    private String reservationNum;       // 예매 고유번호
    private long seatNumber;             // 좌석 코드
    private LocalDateTime paymentTime;   // 결제 시간
    private String state;                // 예약 상태
    private String memberCode;              // 회원 코드
    private int scheduleNum;             // 상영 일정 코드

    private SeatDTO seatDTO;
    private UserDTO userDTO;
    private ScheduleDTO scheduleDTO;

    public ReservationDTO() {
    }

    public ReservationDTO(String reservationNum, long seatNumber, LocalDateTime paymentTime, String state, String memberCode, int scheduleNum, SeatDTO seatDTO, UserDTO userDTO, ScheduleDTO scheduleDTO) {
        this.reservationNum = reservationNum;
        this.seatNumber = seatNumber;
        this.paymentTime = paymentTime;
        this.state = state;
        this.memberCode = memberCode;
        this.scheduleNum = scheduleNum;
        this.seatDTO = seatDTO;
        this.userDTO = userDTO;
        this.scheduleDTO = scheduleDTO;
    }

    public String getReservationNum() {
        return reservationNum;
    }

    public void setReservationNum(String reservationNum) {
        this.reservationNum = reservationNum;
    }

    public long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(long seatNumber) {
        this.seatNumber = seatNumber;
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

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public int getScheduleNum() {
        return scheduleNum;
    }

    public void setScheduleNum(int scheduleNum) {
        this.scheduleNum = scheduleNum;
    }

    public SeatDTO getSeatDTO() {
        return seatDTO;
    }

    public void setSeatDTO(SeatDTO seatDTO) {
        this.seatDTO = seatDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ScheduleDTO getScheduleDTO() {
        return scheduleDTO;
    }

    public void setScheduleDTO(ScheduleDTO scheduleDTO) {
        this.scheduleDTO = scheduleDTO;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "reservationNum='" + reservationNum + '\'' +
                ", seatNumber=" + seatNumber +
                ", paymentTime=" + paymentTime +
                ", state='" + state + '\'' +
                ", memberCode=" + memberCode +
                ", scheduleNum=" + scheduleNum +
                ", seatDTO=" + seatDTO +
                ", userDTO=" + userDTO +
                ", scheduleDTO=" + scheduleDTO +
                '}';
    }
}
