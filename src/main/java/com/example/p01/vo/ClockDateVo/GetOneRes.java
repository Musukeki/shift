package com.example.p01.vo.ClockDateVo;

import java.util.List;

import com.example.p01.vo.headVo.BasicRes;

public class GetOneRes extends BasicRes {

	private List<ClockDateInfoRes> clockDateInfoResList;

	public GetOneRes() {
		super();
	}

	public GetOneRes(int code, String message) {
		super(code, message);
	}

	public GetOneRes(int code, String message, List<ClockDateInfoRes> clockDateInfoResList) {
		super(code, message);
		this.clockDateInfoResList = clockDateInfoResList;
	}

	public List<ClockDateInfoRes> getClockDateInfoResList() {
		return clockDateInfoResList;
	}

	public void setClockDateInfoResList(List<ClockDateInfoRes> clockDateInfoResList) {
		this.clockDateInfoResList = clockDateInfoResList;
	}

}
