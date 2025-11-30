package com.example.p01.vo.ClockDateVo;

import com.example.p01.vo.headVo.BasicRes;

public class AddClockDateRes extends BasicRes {
	
	private ClockDateInfoRes clockDateInfo;

	public AddClockDateRes() {
		super();
	}

	public AddClockDateRes(int code, String message) {
		super(code, message);
	}

	public AddClockDateRes(int code, String message, ClockDateInfoRes clockDateInfo) {
		super(code, message);
		this.clockDateInfo = clockDateInfo;
	}

	public ClockDateInfoRes getClockDateInfo() {
		return clockDateInfo;
	}

	public void setClockDateInfo(ClockDateInfoRes clockDateInfo) {
		this.clockDateInfo = clockDateInfo;
	}

	
	
	
}
