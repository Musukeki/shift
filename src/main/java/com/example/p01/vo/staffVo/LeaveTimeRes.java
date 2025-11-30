package com.example.p01.vo.staffVo;

import com.example.p01.vo.headVo.BasicRes;

public class LeaveTimeRes extends BasicRes {

	private int leaveTime;

	public LeaveTimeRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveTimeRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public LeaveTimeRes(int code, String message, int leaveTime) {
		super(code, message);
		this.leaveTime = leaveTime;
	}

	public int getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(int leaveTime) {
		this.leaveTime = leaveTime;
	}

}
