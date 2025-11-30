package com.example.p01.vo.ClockDateVo;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddRestEndReq {

    private String employeeId;

    private LocalDate workDate;

    private LocalTime restEnd;

    public AddRestEndReq() {
    }

    public AddRestEndReq(String employeeId, LocalDate workDate, LocalTime restEnd) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.restEnd = restEnd;
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

    public LocalTime getRestEnd() {
        return restEnd;
    }

    public void setRestEnd(LocalTime restEnd) {
        this.restEnd = restEnd;
    }

}
