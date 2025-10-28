package com.novacinema.cinemaFranchise.model.dto;

public class ReservationDTO {
    private int reservationNum;     // 예매 고유번호
    private int userCode;           // 회원 코드
    private int movieNum;           // 영화 고유번호
    private int scheduleNum;        // 일정 고유번호
    private int seatCode;           // 좌석 고유번호
    private String paymentTime;     // 결제 시간 (DATETIME → String 또는 LocalDateTime)
    private String status;          // 예매 상태 (예: 예약완료, 취소 등)

    // 기본 생성자
    public ReservationDTO() {}

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

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
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
                ", paymentTime='" + paymentTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
