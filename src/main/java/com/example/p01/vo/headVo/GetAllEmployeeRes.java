package com.example.p01.vo.headVo;

import java.util.List;

public class GetAllEmployeeRes extends BasicRes {

	private List<SearchRes> searchResList;

	public GetAllEmployeeRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetAllEmployeeRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public GetAllEmployeeRes(int code, String message, List<SearchRes> searchResList) {
		super(code, message);
		this.searchResList = searchResList;
	}

	public List<SearchRes> getSearchResList() {
		return searchResList;
	}

	public void setSearchResList(List<SearchRes> searchResList) {
		this.searchResList = searchResList;
	}

}
