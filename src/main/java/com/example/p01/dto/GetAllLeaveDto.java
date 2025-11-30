package com.example.p01.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class GetAllLeaveDto {

	// 員工ID
	private String employeeId;
	// 員工姓名
	private String name;
	// 請假流水ID
	private int leaveId;
	// 申請日期
	private LocalDate applyDate;
	// 申請類別
	private String leaveType;
	// 開始時間
	private LocalTime startTime;
	// 結束時間
	private LocalTime endTime;
	// 請假時數
	private double leaveHours;
	// 是否同意
	private boolean approved;

	public GetAllLeaveDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetAllLeaveDto(String employeeId, String name, int leaveId, LocalDate applyDate, String leaveType,
			LocalTime startTime, LocalTime endTime, double leaveHours, boolean approved) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.leaveId = leaveId;
		this.applyDate = applyDate;
		this.leaveType = leaveType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.leaveHours = leaveHours;
		this.approved = approved;
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

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public LocalDate getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDate applyDate) {
		this.applyDate = applyDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
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

	public double getLeaveHours() {
		return leaveHours;
	}

	public void setLeaveHours(double leaveHours) {
		this.leaveHours = leaveHours;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

}
