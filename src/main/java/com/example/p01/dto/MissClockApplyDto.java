package com.example.p01.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class MissClockApplyDto {

	private int id;

	private String employeeId;

	private LocalDate workDate;

	private LocalTime clockOn;

	private LocalTime clockOff;

	private LocalTime restStart;

	private LocalTime restEnd;

	private Double totalHour;

	private boolean hasDouble;

	private int score;

	private String description;

	private String prove;

	private boolean accept;

	public MissClockApplyDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public MissClockApplyDto(String employeeId, LocalDate workDate, LocalTime clockOn, LocalTime clockOff,
			LocalTime restStart, LocalTime restEnd, int score, String description, String prove) {
		super();
		this.employeeId = employeeId;
		this.workDate = workDate;
		this.clockOn = clockOn;
		this.clockOff = clockOff;
		this.restStart = restStart;
		this.restEnd = restEnd;
		this.score = score;
		this.description = description;
		this.prove = prove;
	}

	public MissClockApplyDto(String employeeId, LocalDate workDate, LocalTime clockOn, LocalTime clockOff,
			LocalTime restStart, LocalTime restEnd, int score, String description) {
		super();
		this.employeeId = employeeId;
		this.workDate = workDate;
		this.clockOn = clockOn;
		this.clockOff = clockOff;
		this.restStart = restStart;
		this.restEnd = restEnd;
		this.score = score;
		this.description = description;
	}

	public MissClockApplyDto(String employeeId, LocalDate workDate, LocalTime clockOn, LocalTime clockOff, int score,
			String description, String prove) {
		super();
		this.employeeId = employeeId;
		this.workDate = workDate;
		this.clockOn = clockOn;
		this.clockOff = clockOff;
		this.score = score;
		this.description = description;
		this.prove = prove;
	}

	public MissClockApplyDto(String employeeId, LocalDate workDate, LocalTime clockOn, LocalTime clockOff, int score,
			String description) {
		super();
		this.employeeId = employeeId;
		this.workDate = workDate;
		this.clockOn = clockOn;
		this.clockOff = clockOff;
		this.score = score;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Double getTotalHour() {
		return totalHour;
	}

	public void setTotalHour(Double totalHour) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProve() {
		return prove;
	}

	public void setProve(String prove) {
		this.prove = prove;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

}
