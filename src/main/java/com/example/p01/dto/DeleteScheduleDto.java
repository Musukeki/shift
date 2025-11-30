package com.example.p01.dto;

import java.time.LocalDate;

public class DeleteScheduleDto {

	private String employeeId;

	private LocalDate applyDate;

	private int shiftWorkId;

	public DeleteScheduleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeleteScheduleDto(String employeeId, LocalDate applyDate, int shiftWorkId) {
		super();
		this.employeeId = employeeId;
		this.applyDate = applyDate;
		this.shiftWorkId = shiftWorkId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

}
