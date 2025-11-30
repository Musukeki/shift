package com.example.p01.vo.ClockDateVo;

import java.time.LocalDateTime;

public class AddClockReq {

//	private int id;

	private String employeeId;

	private LocalDateTime clockOn;

	private LocalDateTime clockOff;

	public AddClockReq() {
		super();
	}

	public AddClockReq(String employeeId, LocalDateTime clockOn, LocalDateTime clockOff) {
		super();
		this.employeeId = employeeId;
		this.clockOn = clockOn;
		this.clockOff = clockOff;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDateTime getClockOn() {
		return clockOn;
	}

	public void setClockOn(LocalDateTime clockOn) {
		this.clockOn = clockOn;
	}

	public LocalDateTime getClockOff() {
		return clockOff;
	}

	public void setClockOff(LocalDateTime clockOff) {
		this.clockOff = clockOff;
	}
	
	
}
