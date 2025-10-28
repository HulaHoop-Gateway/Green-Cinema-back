package com.novacinema.cinemaFranchise.model.dto;

public class ScheduleDTO {
    private int scheduleNum;         // 일정 고유 번호
    private String screeningDate;    // 상영 날짜 (DATE → String 또는 LocalDate)
    private String startTime;        // 시작 시간 (TIME → String 또는 LocalTime)
    private String endTime;          // 종료 시간

    public ScheduleDTO() {}

    public ScheduleDTO(int scheduleNum, String screeningDate, String startTime, String endTime) {
        this.scheduleNum = scheduleNum;
        this.screeningDate = screeningDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getScheduleNum() {
        return scheduleNum;
    }

    public void setScheduleNum(int scheduleNum) {
        this.scheduleNum = scheduleNum;
    }

    public String getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(String screeningDate) {
        this.screeningDate = screeningDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "scheduleNum=" + scheduleNum +
                ", screeningDate='" + screeningDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
