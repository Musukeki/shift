package com.example.p01.entity;

import java.io.Serializable;
import java.time.LocalDate;

//因為有兩個 PK 所以要另外管理
@SuppressWarnings("serial")
public class PreScheduleId implements Serializable {

	private String employeeId; 
	
	private LocalDate applyDate;

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




	
}
