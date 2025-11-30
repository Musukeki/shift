// FixClockReq.java（改成用複合主鍵）
package com.example.p01.vo.ClockDateVo;

import java.time.LocalDate;
import java.time.LocalTime;

public class FixClockReq {
	   private String employeeId; // PK1（必填）
	    private LocalDate workDate; // PK2（必填）
	    private LocalTime clockOn; // 可選
	    private LocalTime clockOff; // 可選
	    private Integer score; // 可選

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
