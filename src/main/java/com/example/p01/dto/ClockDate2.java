package com.example.p01.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClockDate2 {

	private String employeeId;

	private LocalDate workDate;

	private LocalTime clockOn;

	private LocalTime clockOff;

	private LocalTime restStart;

	private LocalTime restEnd;

	private double totalHour;

	private boolean hasDouble;

	private int score;

	private int shiftWorkId;

	private LocalTime startTime;

	private LocalTime endTime;

	public ClockDate2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClockDate2(String employeeId, LocalDate workDate, LocalTime clockOn, LocalTime clockOff, LocalTime restStart,
			LocalTime restEnd, double totalHour, boolean hasDouble, int score, int shiftWorkId, LocalTime startTime,
			LocalTime endTime) {
		super();
		this.employeeId = employeeId;
		this.workDate = workDate;
		this.clockOn = clockOn;
		this.clockOff = clockOff;
		this.restStart = restStart;
		this.restEnd = restEnd;
		this.totalHour = totalHour;
		this.hasDouble = hasDouble;
		this.score = score;
		this.shiftWorkId = shiftWorkId;
		this.startTime = startTime;
		this.endTime = endTime;
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

	public LocalTime getClockOn() {
		return clockOn;
	}

	public void setClockOn(LocalTime clockOn) {
		this.clockOn = clockOn;
	}

	public LocalTime getClockOff() {
		return clockOff;
	}

	public void setClockOff(LocalTime clockOff) {
		this.clockOff = clockOff;
	}

	public LocalTime getRestStart() {
		return restStart;
	}

	public void setRestStart(LocalTime restStart) {
		this.restStart = restStart;
	}

	public LocalTime getRestEnd() {
		return restEnd;
	}

	public void setRestEnd(LocalTime restEnd) {
		this.restEnd = restEnd;
	}

	public double getTotalHour() {
		return totalHour;
	}

	public void setTotalHour(double totalHour) {
		this.totalHour = totalHour;
	}

	public boolean isHasDouble() {
		return hasDouble;
	}

	public void setHasDouble(boolean hasDouble) {
		this.hasDouble = hasDouble;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getShiftWorkId() {
		return shiftWorkId;
	}

	public void setShiftWorkId(int shiftWorkId) {
		this.shiftWorkId = shiftWorkId;
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
