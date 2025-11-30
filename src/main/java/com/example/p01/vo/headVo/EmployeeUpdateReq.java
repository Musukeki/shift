package com.example.p01.vo.headVo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EmployeeUpdateReq {

	private String id;

	@NotBlank(message = "employee_name 不能為null、空字串或全空白字串")
	private String name;

	@NotBlank(message = "job 不能為null、空字串或全空白字串")
	private String title;

	@NotBlank(message = "employment_status 不能為null、空字串或全空白字串")
	private String employmentStatus;

	@Pattern(regexp = "09[0-9]{8}", message = "phone 格式錯誤")
	@NotBlank(message = "phone")
	private String phone;

	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "email 格式錯誤")
	@NotBlank(message = "email 不能為空")
	private String email;

	@NotBlank(message = "branch_name 不能為null、空字串或全空白字串")
	private String department;

	@NotBlank(message = "password 不能為null、空字串或全空白字串")
	private String password;

	public EmployeeUpdateReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeUpdateReq(String id, @NotBlank(message = "employee_name 不能為null、空字串或全空白字串") String name,
			@NotBlank(message = "job 不能為null、空字串或全空白字串") String title,
			@NotBlank(message = "employment_status 不能為null、空字串或全空白字串") String employmentStatus,
			@Pattern(regexp = "09[0-9]{8}", message = "phone 格式錯誤") @NotBlank(message = "phone") String phone,
			@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "email 格式錯誤") @NotBlank(message = "email 不能為空") String email,
			@NotBlank(message = "branch_name 不能為null、空字串或全空白字串") String department,
			@NotBlank(message = "password 不能為null、空字串或全空白字串") String password) {
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
