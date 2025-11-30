package com.example.p01.vo.ClockDateVo;

import java.time.LocalDate;

public class FixCheckReq {
    private String employeeId;
    
    private LocalDate workDate;

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
}
