package com.example.p01.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class LeaveDetailDto {

	// 流水號
	private int id;

	private int leaveId;

	private LocalDate leaveDate;

	private LocalTime startTime;

	private LocalTime endTime;

	private double leaveHours;

	public LeaveDetailDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveDetailDto(int id, int leaveId, LocalDate leaveDate, LocalTime startTime, LocalTime endTime,
			double leaveHours) {
		super();
		this.id = id;
		this.leaveId = leaveId;
		this.leaveDate = leaveDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.leaveHours = leaveHours;
	}

	public LeaveDetailDto(int leaveId, LocalDate leaveDate, LocalTime startTime, LocalTime endTime, double leaveHours) {
		super();
		this.leaveId = leaveId;
		this.leaveDate = leaveDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.leaveHours = leaveHours;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
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
