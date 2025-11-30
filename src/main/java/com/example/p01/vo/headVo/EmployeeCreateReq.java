package com.example.p01.vo.headVo;

public class EmployeeCreateReq {

	private String id;

	private String name;

	private String title;

	private String employmentStatus;

	private String phone;

	private String email;

	private String department;

	private String password;

	public EmployeeCreateReq() {
		super();
	}

	public EmployeeCreateReq(String id, String name, String title, String employmentStatus, String phone, String email,
			String department, String password) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.employmentStatus = employmentStatus;
		this.phone = phone;
		this.email = email;
		this.department = department;
		this.password = password;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
