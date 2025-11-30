package com.example.p01.vo.schedule;

import java.time.LocalDate;
import java.util.List;

public class ApplyDate {

	private LocalDate applyDate;

	private List<ShiftDetail> shiftDetailList;

	public ApplyDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplyDate(LocalDate applyDate, List<ShiftDetail> shiftDetailList) {
		super();
		this.applyDate = applyDate;
		this.shiftDetailList = shiftDetailList;
	}

	public LocalDate getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDate applyDate) {
		this.applyDate = applyDate;
	}

	public List<ShiftDetail> getShiftDetailList() {
		return shiftDetailList;
	}

	public void setShiftDetailList(List<ShiftDetail> shiftDetailList) {
		this.shiftDetailList = shiftDetailList;
	}

}
