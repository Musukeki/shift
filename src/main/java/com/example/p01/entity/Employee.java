package com.example.p01.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// 哈哈是我啦

@Entity
@Table(name="employee")
public class Employee {

	// 員工ID：部門號(兩個大寫英文字母)+職稱(兩個大寫英文字母)+員工流水號(4位數) ex:AAPT0001
	@Pattern(regexp = "^[A-Z]{2}[A-Z]{2,3}[0-9]{4}$",message = "employee_id 格式錯誤")
	@NotBlank(message = "employee_id 不能為null、空字串或全空白字串")
	@Id
	@Column(name="id")
	private String id;
	
	@NotBlank(message = "employee_name 不能為null、空字串或全空白字串")
	@Column(name="name")
	private String name;
	
	@NotBlank(message = "job 不能為null、空字串或全空白字串")
	@Column(name="title")
	private String title;
	
	@NotBlank(message = "employment_status 不能為null、空字串或全空白字串")
	@Column(name="employment_status")
	private String employmentStatus;
	
	@Pattern(regexp = "09[0-9]{8}",message = "phone 格式錯誤")
	@NotBlank(message = "phone")
	@Column(name="phone")
	private String phone;
	
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",message = "email 格式錯誤")
	@NotBlank(message = "")
	@Column(name="email")
	private String email;
	
	@NotBlank(message = "branch_name 不能為null、空字串或全空白字串")
	@Column(name="department")
	private String department;
	
	@NotBlank(message = "password 不能為null、空字串或全空白字串")
	@Column(name="password")
	private String password;
	
	@Column(name="`join_date`")
	private LocalDate joinDate;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Employee(
			@Pattern(regexp = "^[A-Z]{2}[A-Z]{2,3}[0-9]{4}$", message = "employee_id 格式錯誤") @NotBlank(message = "employee_id 不能為null、空字串或全空白字串") String id,
			@NotBlank(message = "employee_name 不能為null、空字串或全空白字串") String name,
			@NotBlank(message = "job 不能為null、空字串或全空白字串") String title,
			@NotBlank(message = "employment_status 不能為null、空字串或全空白字串") String employmentStatus,
			@Pattern(regexp = "09[0-9]{8}", message = "phone 格式錯誤") @NotBlank(message = "phone") String phone,
			@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "email 格式錯誤") @NotBlank(message = "") String email,
			@NotBlank(message = "branch_name 不能為null、空字串或全空白字串") String department) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.employmentStatus = employmentStatus;
		this.phone = phone;
		this.email = email;
		this.department = department;
	}


	public Employee(
			@Pattern(regexp = "^[A-Z]{2}[A-Z]{2,3}[0-9]{4}$", message = "employee_id 格式錯誤") @NotBlank(message = "employee_id 不能為null、空字串或全空白字串") String id,
			@NotBlank(message = "employee_name 不能為null、空字串或全空白字串") String name,
			@NotBlank(message = "job 不能為null、空字串或全空白字串") String title,
			@NotBlank(message = "employment_status 不能為null、空字串或全空白字串") String employmentStatus,
			@Pattern(regexp = "09[0-9]{8}", message = "phone 格式錯誤") @NotBlank(message = "phone") String phone,
			@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "email 格式錯誤") @NotBlank(message = "") String email,
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


	public LocalDate getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}



	
	
}
