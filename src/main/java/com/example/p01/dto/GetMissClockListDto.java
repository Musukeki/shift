package com.example.p01.dto;

import java.time.LocalDate;

public class GetMissClockListDto {

	private int id;

	private String employeeId;

	private String name;

	private LocalDate workDate;

	public GetMissClockListDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetMissClockListDto(int id, String employeeId, String name, LocalDate workDate) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.name = name;
		this.workDate = workDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDate getWorkDate() {
		return workDate;
	}

	public void setWorkDate(LocalDate workDate) {
		this.workDate = workDate;
	}

}
