package com.example.p01.vo.headVo;

import java.time.LocalDate;

public class CheckJoinDateRes extends BasicRes {

	private String employeeId;

	private String name;

	private LocalDate joinDate;

	private boolean canMakeSchedule;

	public CheckJoinDateRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckJoinDateRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public CheckJoinDateRes(int code, String message, String employeeId, String name, LocalDate joinDate,
			boolean canMakeSchedule) {
		super(code, message);
		this.employeeId = employeeId;
		this.name = name;
		this.joinDate = joinDate;
		this.canMakeSchedule = canMakeSchedule;
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

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public boolean isCanMakeSchedule() {
		return canMakeSchedule;
	}

	public void setCanMakeSchedule(boolean canMakeSchedule) {
		this.canMakeSchedule = canMakeSchedule;
	}

}
