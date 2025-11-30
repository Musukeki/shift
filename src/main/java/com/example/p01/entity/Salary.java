package com.example.p01.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "salary")
@IdClass(SalaryId.class)
public class Salary {
	@Id
	@Column(name = "employee_id")
	private String employeeId;

	@Id
	@Column(name = "`year_month`")
	private String yearMonth;

	@Column(name = "base_salary")
	private int baseSalary;

	@Column(name = "hourlyrate")
	private Integer hourlyRate;

	@Column(name = "overtime_pay")
	private int overtimePay;

	@Column(name = "deduction")
	private int deduction;

	@Column(name = "insurance_fee")
	private int insuranceFee;

	@Column(name = "total_salary")
	private int totalSalary;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public int getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Integer getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(Integer hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public int getOvertimePay() {
		return overtimePay;
	}

	public void setOvertimePay(int overtimePay) {
		this.overtimePay = overtimePay;
	}

	public int getDeduction() {
		return deduction;
	}

	public void setDeduction(int deduction) {
		this.deduction = deduction;
	}

	public int getInsuranceFee() {
		return insuranceFee;
	}

	public void setInsuranceFee(int insuranceFee) {
		this.insuranceFee = insuranceFee;
	}

	public int getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(int totalSalary) {
		this.totalSalary = totalSalary;
	}

}