package com.example.p01.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClockOffDto {

	private LocalTime clockOff;

	private double totalHour;

	private int score;

	private String employeeId;

	private LocalDate workDate;

	public ClockOffDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ClockOffDto(LocalTime clockOff, int score, String employeeId) {
		super();
		this.clockOff = clockOff;
		this.score = score;
		this.employeeId = employeeId;
	}


	public ClockOffDto(LocalTime clockOff, int score, String employeeId, LocalDate workDate) {
		super();
		this.clockOff = clockOff;
		this.score = score;
		this.employeeId = employeeId;
		this.workDate = workDate;
	}

	public ClockOffDto(LocalTime clockOff, double totalHour, int score, String employeeId, LocalDate workDate) {
		super();
		this.clockOff = clockOff;
		this.totalHour = totalHour;
		this.score = score;
		this.employeeId = employeeId;
		this.workDate = workDate;
	}

	public LocalTime getClockOff() {
		return clockOff;
	}

	public void setClockOff(LocalTime clockOff) {
		this.clockOff = clockOff;
	}

	public double getTotalHour() {
		return totalHour;
	}

	public void setTotalHour(double totalHour) {
		this.totalHour = totalHour;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getWorkDate() {
		return workDate;
	}

	public void setWorkDate(LocalDate workDate) {
		this.workDate = workDate;
	}

}
