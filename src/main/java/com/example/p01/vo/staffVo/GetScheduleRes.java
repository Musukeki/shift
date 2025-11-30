package com.example.p01.vo.staffVo;

import java.util.List;

import com.example.p01.dto.ScheduleDto;
import com.example.p01.vo.headVo.BasicRes;

public class GetScheduleRes extends BasicRes {

	private List<ScheduleDto> preScheduleList;

	public GetScheduleRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetScheduleRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public GetScheduleRes(int code, String message, List<ScheduleDto> preScheduleList) {
		super(code, message);
		this.preScheduleList = preScheduleList;
	}

	public List<ScheduleDto> getPreScheduleList() {
		return preScheduleList;
	}

	public void setPreScheduleList(List<ScheduleDto> preScheduleList) {
		this.preScheduleList = preScheduleList;
	}

}
