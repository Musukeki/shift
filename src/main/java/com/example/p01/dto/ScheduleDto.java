package com.example.p01.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDto {
	
		private String employeeId;
	
		private String employeeName;
	
		private String employeeTitle;
	
		private String employeeStatus;
	
		private LocalDate applyDate;
	
		private int shiftWorkId;
	
		private boolean accept;
	
		private LocalTime startTime;
	
		private LocalTime endTime;

	public ScheduleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ScheduleDto(String employeeId, String employeeName, String employeeTitle, String employeeStatus,
			LocalDate applyDate, int shiftWorkId, boolean accept, LocalTime startTime, LocalTime endTime) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeTitle = employeeTitle;
		this.employeeStatus = employeeStatus;
		this.applyDate = applyDate;
		this.shiftWorkId = shiftWorkId;
		this.accept = accept;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeTitle() {
		return employeeTitle;
	}

	public void setEmployeeTitle(String employeeTitle) {
		this.employeeTitle = employeeTitle;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
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

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
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
