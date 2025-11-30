package com.example.p01.vo.staffVo;

import java.time.LocalDate;
import java.time.LocalTime;

public class GetCanSupEmployeeReq {

	private LocalDate LeaveDate;

	private LocalTime startTime;

	private LocalTime endTime;

	public GetCanSupEmployeeReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetCanSupEmployeeReq(LocalDate leaveDate, LocalTime startTime, LocalTime endTime) {
		super();
		LeaveDate = leaveDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public LocalDate getLeaveDate() {
		return LeaveDate;
	}

	public void setLeaveDate(LocalDate leaveDate) {
		LeaveDate = leaveDate;
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
