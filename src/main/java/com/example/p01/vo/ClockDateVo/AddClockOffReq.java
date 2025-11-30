package com.example.p01.vo.ClockDateVo;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddClockOffReq {

    private String employeeId;

    private LocalDate workDate;

    private LocalTime clockOff;

    private double score;

    public AddClockOffReq() {
    }

    public AddClockOffReq(String employeeId, LocalDate workDate, LocalTime clockOff, double score) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.clockOff = clockOff;
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

    public LocalTime getClockOff() {
        return clockOff;
    }

    public void setClockOff(LocalTime clockOff) {
        this.clockOff = clockOff;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
