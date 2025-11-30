package com.example.p01.vo.staffVo;

import java.time.LocalDate;

public class PreSchduleUpdateVo {

	private String employeeId;

	private LocalDate applyDate;

	private int shiftWorkId;

	private boolean accept;

	public PreSchduleUpdateVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PreSchduleUpdateVo(String employeeId, LocalDate applyDate, int shiftWorkId, boolean accept) {
		super();
		this.employeeId = employeeId;
		this.applyDate = applyDate;
		this.shiftWorkId = shiftWorkId;
		this.accept = accept;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDate applyDate) {
		this.applyDate = applyDate;
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
