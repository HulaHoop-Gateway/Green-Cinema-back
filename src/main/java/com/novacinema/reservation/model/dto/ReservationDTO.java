package com.novacinema.reservation.model.dto;

import java.time.LocalDateTime;

public class ReservationDTO {
    private int reservationNum;     // 예매 고유번호
    private int userCode;           // 회원 코드 (FK)
    private int movieNum;           // 영화 고유번호 (FK)
    private int scheduleNum;        // 일정 고유번호 (FK)
    private int seatCode;           // 좌석 고유번호 (FK)
    private LocalDateTime paymentTime; // 결제 시간
    private String status;          // 예매 상태 (예약완료, 취소 등)

    // 기본 생성자
    public ReservationDTO() {}

    public ReservationDTO(int reservationNum, int userCode, int movieNum, int scheduleNum, int seatCode, LocalDateTime paymentTime, String status) {
        this.reservationNum = reservationNum;
        this.userCode = userCode;
        this.movieNum = movieNum;
        this.scheduleNum = scheduleNum;
        this.seatCode = seatCode;
        this.paymentTime = paymentTime;
        this.status = status;
    }

    public int getReservationNum() {
        return reservationNum;
    }

    public void setReservationNum(int reservationNum) {
        this.reservationNum = reservationNum;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public int getMovieNum() {
        return movieNum;
    }

    public void setMovieNum(int movieNum) {
        this.movieNum = movieNum;
    }

    public int getScheduleNum() {
        return scheduleNum;
    }

    public void setScheduleNum(int scheduleNum) {
        this.scheduleNum = scheduleNum;
    }

    public int getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(int seatCode) {
        this.seatCode = seatCode;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "reservationNum=" + reservationNum +
                ", userCode=" + userCode +
                ", movieNum=" + movieNum +
                ", scheduleNum=" + scheduleNum +
                ", seatCode=" + seatCode +
                ", paymentTime=" + paymentTime +
                ", status='" + status + '\'' +
                '}';
    }
}
