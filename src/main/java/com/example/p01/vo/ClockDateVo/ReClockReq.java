package com.example.p01.vo.ClockDateVo;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReClockReq {

    private String employeeId;

    private LocalDate workDate;

    private LocalTime clockOn;

    private LocalTime clockOff;

    private LocalTime restStart;

    private LocalTime restEnd;

    private Double totalHour;

    private Integer score;

    public ReClockReq() {
    }

    public ReClockReq(String employeeId, LocalDate workDate, LocalTime clockOn, LocalTime clockOff, LocalTime restStart,
            LocalTime restEnd, Double totalHour, Integer score) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.clockOn = clockOn;
        this.clockOff = clockOff;
        this.restStart = restStart;
        this.restEnd = restEnd;
        this.totalHour = totalHour;
        this.score = score;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public LocalTime getClockOn() {
        return clockOn;
    }

    public void setClockOn(LocalTime clockOn) {
        this.clockOn = clockOn;
    }

    public LocalTime getClockOff() {
        return clockOff;
    }

    public void setClockOff(LocalTime clockOff) {
        this.clockOff = clockOff;
    }

    public LocalTime getRestStart() {
        return restStart;
    }

    public void setRestStart(LocalTime restStart) {
        this.restStart = restStart;
    }

    public LocalTime getRestEnd() {
        return restEnd;
    }

    public void setRestEnd(LocalTime restEnd) {
        this.restEnd = restEnd;
    }

    public Double getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Double totalHour) {
        this.totalHour = totalHour;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}
