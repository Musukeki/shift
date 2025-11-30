package com.example.p01.vo.staffVo;

public class CreateAllPreSchduleReq {

	private int year;

	private int month;

	public CreateAllPreSchduleReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateAllPreSchduleReq(int year, int month) {
		super();
		this.year = year;
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

}
