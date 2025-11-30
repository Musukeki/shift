package com.example.p01.vo.staffVo;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class PreScheduleVo {

	private LocalDate applyDate;

	private boolean working;

	private int shiftWorkId;
	
	private boolean accept;

	public PreScheduleVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PreScheduleVo(LocalDate applyDate, boolean working, int shiftWorkId, boolean accept) {
		super();
		this.applyDate = applyDate;
		this.working = working;
		this.shiftWorkId = shiftWorkId;
		this.accept = accept;
	}

	public LocalDate getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDate applyDate) {
		this.applyDate = applyDate;
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean working) {
		this.working = working;
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

	
}
