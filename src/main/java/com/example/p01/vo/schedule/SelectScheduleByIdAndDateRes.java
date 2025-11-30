package com.example.p01.vo.schedule;

import java.util.List;

import com.example.p01.vo.headVo.BasicRes;

public class SelectScheduleByIdAndDateRes extends BasicRes {

	private List<ShiftDetail> ShiftDetailList;

	public SelectScheduleByIdAndDateRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SelectScheduleByIdAndDateRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public SelectScheduleByIdAndDateRes(int code, String message, List<ShiftDetail> shiftDetailList) {
		super(code, message);
		ShiftDetailList = shiftDetailList;
	}

	public List<ShiftDetail> getShiftDetailList() {
		return ShiftDetailList;
	}

	public void setShiftDetailList(List<ShiftDetail> shiftDetailList) {
		ShiftDetailList = shiftDetailList;
	}

}
