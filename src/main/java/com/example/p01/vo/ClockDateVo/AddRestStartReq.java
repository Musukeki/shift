package com.example.p01.vo.ClockDateVo;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddRestStartReq {

    private String employeeId;

    private LocalDate workDate;

    private LocalTime restStart;

    public AddRestStartReq() {
    }

    public AddRestStartReq(String employeeId, LocalDate workDate, LocalTime restStart) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.restStart = restStart;
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

    public LocalTime getRestStart() {
        return restStart;
    }

    public void setRestStart(LocalTime restStart) {
        this.restStart = restStart;
    }

}
