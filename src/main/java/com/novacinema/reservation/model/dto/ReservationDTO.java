package com.novacinema.reservation.model.dto;

import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.seat.model.dto.SeatDTO;
import com.novacinema.user.model.dto.UserDTO;

import java.time.LocalDateTime;

// 고객의 단일 좌석 예매 정보를 담는 데이터 전송 객체
// 데이터베이스의 T_Reservation 테이블과 매핑되며, 예약 관리에 필요한 식별자와 상태 값을 포함한다
public class ReservationDTO {
    // 예매를 식별하기 위해 채번된 고유 예매 번호 (예: 2511060001)
    private String reservationNum; 
    
    // 고객이 선택한 좌석의 내부 식별 코드
    private long seatNumber; 
    
    // 예매(결제)가 정상적으로 완료된 시간
    private LocalDateTime paymentTime; 
    
    // 현재 예매의 진행 상태 (예: '예매완료', '취소됨' 등)
    private String state; 
    
    // 예매를 진행한 고객의 핸드폰 번호 (비회원 조회 및 알림 등에 활용)
    private String phoneNumber; 
    
    // 예매한 영화의 구체적인 상영 일정 고유 번호 (외래키)
    private int scheduleNum; 
    
    // 여러 좌석을 동시에 결제했을 때, 이들을 묶어주는 논리적인 결제 단위 그룹 ID
    private String bookingGroupId; 

    // 예약된 좌석의 상세 위치(행, 열 등) 정보를 포함하는 연관 DTO
    private SeatDTO seatDTO;
    
    // 예약자(회원)의 상세 정보를 포함하는 연관 DTO
    private UserDTO userDTO;
    
    // 예약된 영화의 상영 시간, 상영관, 지점 정보를 모두 포함하는 연관 DTO
    private ScheduleDTO scheduleDTO;

    public ReservationDTO() {
    }

    public ReservationDTO(String reservationNum, long seatNumber, LocalDateTime paymentTime, String state,
            String phoneNumber, int scheduleNum, String bookingGroupId, SeatDTO seatDTO, UserDTO userDTO,
            ScheduleDTO scheduleDTO) {
        this.reservationNum = reservationNum;
        this.seatNumber = seatNumber;
        this.paymentTime = paymentTime;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.scheduleNum = scheduleNum;
        this.bookingGroupId = bookingGroupId;
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

    // 연관된 여러 좌석 예약을 하나로 묶기 위한 bookingGroupId의 getter/setter
    public String getBookingGroupId() {
        return bookingGroupId;
    }

    public void setBookingGroupId(String bookingGroupId) {
        this.bookingGroupId = bookingGroupId;
    }

    // 관리자 서버(외부 시스템)와의 통신에서 발급받은 결제 트랜잭션 번호의 getter/setter
    private Long transactionNum;

    public Long getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(Long transactionNum) {
        this.transactionNum = transactionNum;
    }
}
