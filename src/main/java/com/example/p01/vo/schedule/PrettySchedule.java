package com.example.p01.vo.schedule;

import java.util.List;

import com.example.p01.vo.headVo.BasicRes;

public class PrettySchedule extends BasicRes {

	private List<EmployeeList> employeeList;

	public PrettySchedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrettySchedule(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public PrettySchedule(int code, String message, List<EmployeeList> employeeList) {
		super(code, message);
		this.employeeList = employeeList;
	}

	public List<EmployeeList> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeList> employeeList) {
		this.employeeList = employeeList;
	}

}
