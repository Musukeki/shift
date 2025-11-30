package com.example.p01.vo.headVo;

import java.time.LocalDate;
import java.time.LocalTime;

public class LeaveClassReq {

	private LocalDate leaveDate;

	private LocalTime startTime;

	private LocalTime endTime;

	private double leaveHours;

	public LeaveClassReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveClassReq(LocalDate leaveDate, LocalTime startTime, LocalTime endTime, double leaveHours) {
		super();
		this.leaveDate = leaveDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.leaveHours = leaveHours;
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

	public double getLeaveHours() {
		return leaveHours;
	}

	public void setLeaveHours(double leaveHours) {
		this.leaveHours = leaveHours;
	}
	
	

}
