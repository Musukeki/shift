package com.example.p01.dto;

public class SalaryDto {

	
	private double baseSalary;       // 基本工資（8 小時內）
    private double overtimePay;      // 加班費
    private double holidayPay;       // 假日加倍工資
    private double grossSalary;      // 應發薪資 (總和)
    private double laborInsurance;   // 勞保
    private double healthInsurance;  // 健保
    private double netSalary;        // 實領薪資
    
    
	public double getBaseSalary() {
		return baseSalary;
	}
	public double getOvertimePay() {
		return overtimePay;
	}
	public double getHolidayPay() {
		return holidayPay;
	}
	public double getGrossSalary() {
		return grossSalary;
	}
	public double getLaborInsurance() {
		return laborInsurance;
	}
	public double getHealthInsurance() {
		return healthInsurance;
	}
	public double getNetSalary() {
		return netSalary;
	}
	
	
	public SalaryDto(double baseSalary, double overtimePay, double holidayPay, double grossSalary,//
			double laborInsurance, double healthInsurance, double netSalary) {
		super();
		this.baseSalary = baseSalary;
		this.overtimePay = overtimePay;
		this.holidayPay = holidayPay;
		this.grossSalary = grossSalary;
		this.laborInsurance = laborInsurance;
		this.healthInsurance = healthInsurance;
		this.netSalary = netSalary;
	

	
	
	
	
   
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	

