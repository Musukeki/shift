package com.example.p01.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class GetAllLeaveForSalaryDto {

	private int leaveId;

	private String employeeId;

	private String name;

	private String leaveType;

	private String leaveDescription;

	private String leaveProve;

	private Boolean approved;

	private LocalDate leaveDate;

	private LocalTime startTime;

	private LocalTime endTime;

	private int leaveHours;

	public GetAllLeaveForSalaryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetAllLeaveForSalaryDto(int leaveId, String employeeId, String name, String leaveType,
			String leaveDescription, String leaveProve, Boolean approved, LocalDate leaveDate, LocalTime startTime,
			LocalTime endTime, int leaveHours) {
		super();
		this.leaveId = leaveId;
		this.employeeId = employeeId;
		this.name = name;
		this.leaveType = leaveType;
		this.leaveDescription = leaveDescription;
		this.leaveProve = leaveProve;
		this.approved = approved;
		this.leaveDate = leaveDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.leaveHours = leaveHours;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
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

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveDescription() {
		return leaveDescription;
	}

	public void setLeaveDescription(String leaveDescription) {
		this.leaveDescription = leaveDescription;
	}

	public String getLeaveProve() {
		return leaveProve;
	}

	public void setLeaveProve(String leaveProve) {
		this.leaveProve = leaveProve;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public LocalDate getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(LocalDate leaveDate) {
		this.leaveDate = leaveDate;
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

	public int getLeaveHours() {
		return leaveHours;
	}

	public void setLeaveHours(int leaveHours) {
		this.leaveHours = leaveHours;
	}

}
