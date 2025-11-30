package com.example.p01.vo.staffVo;

import java.time.LocalDate;
import java.util.List;

import com.example.p01.dto.LeaveDetailDto;

public class LeaveApplyByDateReq {
	private String employeeId;

	private String leaveType;

	private String leaveDescription;

	private String leaveProve;

	private List<LocalDate> leaveDate;

	public LeaveApplyByDateReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveApplyByDateReq(String employeeId, String leaveType, String leaveDescription, String leaveProve,
			List<LocalDate> leaveDate) {
		super();
		this.employeeId = employeeId;
		this.leaveType = leaveType;
		this.leaveDescription = leaveDescription;
		this.leaveProve = leaveProve;
		this.leaveDate = leaveDate;
	}

	public LeaveApplyByDateReq(String employeeId, String leaveType, String leaveDescription,
			List<LocalDate> leaveDate) {
		super();
		this.employeeId = employeeId;
		this.leaveType = leaveType;
		this.leaveDescription = leaveDescription;
		this.leaveDate = leaveDate;
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

	public List<LocalDate> getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(List<LocalDate> leaveDate) {
		this.leaveDate = leaveDate;
	}

}
