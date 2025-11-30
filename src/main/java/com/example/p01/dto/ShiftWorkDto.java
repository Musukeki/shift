package com.example.p01.dto;

import java.time.LocalTime;

public class ShiftWorkDto {

	private int id;

	private int shiftWorkId;

	private LocalTime startTime;

	private LocalTime endTime;

	public ShiftWorkDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShiftWorkDto(int shiftWorkId, LocalTime startTime, LocalTime endTime) {
		super();
		this.shiftWorkId = shiftWorkId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public ShiftWorkDto(int id, int shiftWorkId, LocalTime startTime, LocalTime endTime) {
		super();
		this.id = id;
		this.shiftWorkId = shiftWorkId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public ShiftWorkDto(LocalTime startTime, LocalTime endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
