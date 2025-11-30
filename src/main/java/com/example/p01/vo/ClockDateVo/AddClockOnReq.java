package com.example.p01.vo.ClockDateVo;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddClockOnReq {

    private String employeeId;

    private LocalDate workDate;

    private LocalTime clockOn;

    public AddClockOnReq() {
    }

    public AddClockOnReq(String employeeId, LocalDate workDate, LocalTime clockOn) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.clockOn = clockOn;
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

}
