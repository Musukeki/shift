package com.example.p01.dto;

import java.time.LocalDate;
import java.util.List;

public class GetStaffCanWorkDayDto {

	private String employeeId;

	private List<LocalDate> applyDate;

	public GetStaffCanWorkDayDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetStaffCanWorkDayDto(String employeeId, List<LocalDate> applyDate) {
		super();
		this.employeeId = employeeId;
		this.applyDate = applyDate;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public List<LocalDate> getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(List<LocalDate> applyDate) {
		this.applyDate = applyDate;
	}



	

}
