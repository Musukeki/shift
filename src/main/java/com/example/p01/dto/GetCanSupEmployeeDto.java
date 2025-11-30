package com.example.p01.dto;

import java.time.LocalTime;

public class GetCanSupEmployeeDto {

	private String employeeId;

	private String name;

	private String canSupdate;

	private int shiftWorkId;

	private LocalTime startTime;

	private LocalTime endTime;

	public GetCanSupEmployeeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetCanSupEmployeeDto(String employeeId, String name, String canSupdate, int shiftWorkId, LocalTime startTime,
			LocalTime endTime) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.canSupdate = canSupdate;
		this.shiftWorkId = shiftWorkId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCanSupdate() {
		return canSupdate;
	}

	public void setCanSupdate(String canSupdate) {
		this.canSupdate = canSupdate;
	}

	public int getShiftWorkId() {
		return shiftWorkId;
	}

	public void setShiftWorkId(int shiftWorkId) {
		this.shiftWorkId = shiftWorkId;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

}
