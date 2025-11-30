package com.example.p01.vo.staffVo;

public class LeaveApprovedReq {
	private int leaveId;

	private boolean approved;

	public LeaveApprovedReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveApprovedReq(int leaveId, boolean approved) {
		super();
		this.leaveId = leaveId;
		this.approved = approved;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

}
