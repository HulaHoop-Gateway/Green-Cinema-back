package com.novacinema.schedule.model.dto;

import com.novacinema.info.model.dto.InfoDTO;
import com.novacinema.theater.model.dto.TheaterDTO;

import java.time.LocalDateTime;

// 특정 상영관에서 방영되는 영화의 구체적인 시간표(스케줄) 정보를 담는 데이터 전송 객체
// 데이터베이스의 T_Schedule 테이블과 매핑되며, 예약 시스템의 핵심 기준 데이터로 활용된다
public class ScheduleDTO {
    // 시스템에서 상영 일정을 식별하는 고유 키 번호
    private int scheduleNum;
    
    // 영화가 실제 상영되는 날짜 및 시간
    private LocalDateTime screeningDate;
    
    // 해당 영화가 상영되는 상영관의 고유 식별 번호 (외래키)
    private int screeningNum;
    
    // 상영되는 영화의 고유 식별 번호 (외래키)
    private int movieNum;

    // 상영되는 영화의 상세 정보(제목, 포스터 등)를 포함하는 연관 DTO
    private InfoDTO movieInfo;
    
    // 상영이 이루어지는 상영관 및 소속 지점 정보를 포함하는 연관 DTO
    private TheaterDTO theaterInfo;

    public ScheduleDTO() {
    }

    public ScheduleDTO(int scheduleNum, LocalDateTime screeningDate, int screeningNum, int movieNum, InfoDTO movieInfo, TheaterDTO theaterInfo) {
        this.scheduleNum = scheduleNum;
        this.screeningDate = screeningDate;
        this.screeningNum = screeningNum;
        this.movieNum = movieNum;
        this.movieInfo = movieInfo;
        this.theaterInfo = theaterInfo;
    }

    // 현재 시간을 기준으로 해당 상영 일정의 예매 취소 가능 여부를 검증한다
    // 상영 시작 1분 전까지만 취소를 허용하는 비즈니스 규칙이 적용됨
    public boolean isCancelable() {
        return screeningDate.isAfter(LocalDateTime.now().minusMinutes(1));
    }


    public int getScheduleNum() {
        return scheduleNum;
    }

    public void setScheduleNum(int scheduleNum) {
        this.scheduleNum = scheduleNum;
    }

    public LocalDateTime getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(LocalDateTime screeningDate) {
        this.screeningDate = screeningDate;
    }

    public int getScreeningNum() {
        return screeningNum;
    }

    public void setScreeningNum(int screeningNum) {
        this.screeningNum = screeningNum;
    }

    public int getMovieNum() {
        return movieNum;
    }

    public void setMovieNum(int movieNum) {
        this.movieNum = movieNum;
    }

    public InfoDTO getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(InfoDTO movieInfo) {
        this.movieInfo = movieInfo;
    }

    public TheaterDTO getTheaterInfo() {
        return theaterInfo;
    }

    public void setTheaterInfo(TheaterDTO theaterInfo) {
        this.theaterInfo = theaterInfo;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "scheduleNum=" + scheduleNum +
                ", screeningDate=" + screeningDate +
                ", screeningNum=" + screeningNum +
                ", movieNum=" + movieNum +
                ", movieInfo=" + movieInfo +
                ", theaterInfo=" + theaterInfo +
                '}';
    }
}
