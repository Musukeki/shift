package com.example.p01.dto;

public class LeaveApplicationDto {

	private int leaveId;

	private String employeeId;

	private String leaveType;

	private String leaveDescription;

	private String leaveProve;

	private Boolean approved;

	public LeaveApplicationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveApplicationDto(String employeeId, String leaveType, String leaveDescription, String leaveProve,
			Boolean approved) {
		super();
		this.employeeId = employeeId;
		this.leaveType = leaveType;
		this.leaveDescription = leaveDescription;
		this.leaveProve = leaveProve;
		this.approved = approved;
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

}
