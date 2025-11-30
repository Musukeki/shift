package com.example.p01.vo.staffVo;

import java.util.List;

public class PreScheduleUpdateReq {

	private List<PreSchduleUpdateVo> preSchduleUpdateVo;

	public PreScheduleUpdateReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PreScheduleUpdateReq(List<PreSchduleUpdateVo> preSchduleUpdateVo) {
		super();
		this.preSchduleUpdateVo = preSchduleUpdateVo;
	}

	public List<PreSchduleUpdateVo> getPreSchduleUpdateVo() {
		return preSchduleUpdateVo;
	}

	public void setPreSchduleUpdateVo(List<PreSchduleUpdateVo> preSchduleUpdateVo) {
		this.preSchduleUpdateVo = preSchduleUpdateVo;
	}

}
