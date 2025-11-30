package com.example.p01.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class GetThisDayScheduleDto {

	private String employeeId;

	private String name;

	private String title;

	private String department;

	private LocalDate applyDate;

	private int shiftWorkId;

	private LocalTime startTime;

	private LocalTime endTime;

	public GetThisDayScheduleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetThisDayScheduleDto(String employeeId, String name, String title, String department, LocalDate applyDate,
			int shiftWorkId, LocalTime startTime, LocalTime endTime) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.title = title;
		this.department = department;
		this.applyDate = applyDate;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public LocalDate getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDate applyDate) {
		this.applyDate = applyDate;
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
