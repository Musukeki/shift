package com.example.p01.vo.headVo;

import java.util.List;

import com.example.p01.entity.Employee;

public class GetAllEmployeeRes2 extends BasicRes {

	private List<Employee> searchResList;

	public GetAllEmployeeRes2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetAllEmployeeRes2(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public GetAllEmployeeRes2(int code, String message, List<Employee> searchResList) {
		super(code, message);
		this.searchResList = searchResList;
	}

	public List<Employee> getSearchResList() {
		return searchResList;
	}

	public void setSearchResList(List<Employee> searchResList) {
		this.searchResList = searchResList;
	}

}
