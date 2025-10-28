package com.novacinema.schedule.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDTO {
    private int scheduleNum;           // 일정 고유 번호
    private LocalDate screeningDate;   // 상영 날짜
    private LocalTime startTime;       // 시작 시간

    // 기본 생성자
    public ScheduleDTO() {}

    public ScheduleDTO(int scheduleNum, LocalDate screeningDate, LocalTime startTime) {
        this.scheduleNum = scheduleNum;
        this.screeningDate = screeningDate;
        this.startTime = startTime;
    }

    public int getScheduleNum() {
        return scheduleNum;
    }

    public void setScheduleNum(int scheduleNum) {
        this.scheduleNum = scheduleNum;
    }

    public LocalDate getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(LocalDate screeningDate) {
        this.screeningDate = screeningDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "scheduleNum=" + scheduleNum +
                ", screeningDate=" + screeningDate +
                ", startTime=" + startTime +
                '}';
    }
}
