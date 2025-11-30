package com.example.p01.vo.staffVo;

import java.util.List;

import com.example.p01.dto.LeaveDetailDto;

public class leaveApplyReq {

	private String employeeId;

	private String leaveType;

	private String leaveDescription;

	private String leaveProve;

	private List<LeaveDetailDto> leaveDetails;

	public leaveApplyReq() {
		super();
		// TODO Auto-generated constructor stub
	}


	public leaveApplyReq(String leaveType, String leaveDescription, String leaveProve,
			List<LeaveDetailDto> leaveDetails) {
		super();
		this.leaveType = leaveType;
		this.leaveDescription = leaveDescription;
		this.leaveProve = leaveProve;
		this.leaveDetails = leaveDetails;
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

	public List<LeaveDetailDto> getLeaveDetails() {
		return leaveDetails;
	}

	public void setLeaveDetails(List<LeaveDetailDto> leaveDetails) {
		this.leaveDetails = leaveDetails;
	}

}
