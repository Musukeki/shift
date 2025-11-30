package com.example.p01.vo.staffVo;

import java.time.LocalDate;
import java.util.List;


public class PreSchduleCreateListReq extends PreScheduleVo {

	private String employeeId;

	private List<PreScheduleVo> preScheduleVo;

	public PreSchduleCreateListReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PreSchduleCreateListReq(LocalDate applyDate, boolean working, int shiftWorkId, boolean accept) {
		super(applyDate, working, shiftWorkId, accept);
		// TODO Auto-generated constructor stub
	}

	public PreSchduleCreateListReq(String employeeId, List<PreScheduleVo> preScheduleVo) {
		super();
		this.employeeId = employeeId;
		this.preScheduleVo = preScheduleVo;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public List<PreScheduleVo> getPreScheduleVo() {
		return preScheduleVo;
	}

	public void setPreScheduleVo(List<PreScheduleVo> preScheduleVo) {
		this.preScheduleVo = preScheduleVo;
	}

	

}
