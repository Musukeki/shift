package com.example.p01.vo.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EmployeeList {

	private String employeeId;

	private String name;

	private String title;

	private String employeeStatus;

	private List<ApplyDate> date;

	public EmployeeList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeList(String employeeId, String name, String title, String employeeStatus, List<ApplyDate> date) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.title = title;
		this.employeeStatus = employeeStatus;
		this.date = date;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public List<ApplyDate> getDate() {
		return date;
	}

	public void setDate(List<ApplyDate> date) {
		this.date = date;
	}

}
