// ClockDateInfoRes.java（型別同步）
package com.example.p01.vo.ClockDateVo;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClockDateInfoRes {
	 private String employeeId;
	    private LocalDate workDate;
	    private LocalTime clockOn;
	    private LocalTime clockOff;
	    private Boolean hasDouble;
	    private Double totalHour;
	    private Integer score;

	    public ClockDateInfoRes() {
	    }

	    public ClockDateInfoRes(String employeeId, LocalDate workDate,
	            LocalTime clockOn, LocalTime clockOff,
	            Boolean hasDouble, Double totalHour, Integer score) {
	        this.employeeId = employeeId;
	        this.workDate = workDate;
	        this.clockOn = clockOn;
	        this.clockOff = clockOff;
	        this.hasDouble = hasDouble;
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

	    public Boolean getHasDouble() {
	        return hasDouble;
	    }

	    public void setHasDouble(Boolean hasDouble) {
	        this.hasDouble = hasDouble;
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
