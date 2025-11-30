package com.example.p01.vo.schedule;

import java.time.LocalTime;

import com.example.p01.vo.headVo.BasicRes;

public class ShiftDetail {

	private int shiftWorkId;

	private boolean accept;

	private LocalTime startTime;

	private LocalTime endTime;

	public ShiftDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShiftDetail(int shiftWorkId, boolean accept, LocalTime startTime, LocalTime endTime) {
		super();
		this.shiftWorkId = shiftWorkId;
		this.accept = accept;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getShiftWorkId() {
		return shiftWorkId;
	}

	public void setShiftWorkId(int shiftWorkId) {
		this.shiftWorkId = shiftWorkId;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
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
