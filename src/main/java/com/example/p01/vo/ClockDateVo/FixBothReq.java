// src/main/java/com/example/p01/vo/ClockDateVo/FixBothReq.java
package com.example.p01.vo.ClockDateVo;

import java.time.LocalDate;
import java.time.LocalTime;

public class FixBothReq {
    private String employeeId; // 必填：員工編號
    private LocalDate workDate; // 必填：上班日期（要等於 pre_schedule.apply_date）
    private LocalTime clockOn; // 必填：補的上班時間
    private LocalTime clockOff; // 必填：補的下班時間
    private Integer score; // 可選：分數，預設 0

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
