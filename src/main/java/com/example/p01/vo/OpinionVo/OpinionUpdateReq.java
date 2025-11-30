package com.example.p01.vo.OpinionVo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class OpinionUpdateReq {

    @NotBlank(message = "員工ID為空")
    private String employeeId;

    private String title;

    private String message;

    @NotNull(message = "更新意見日期為空")
    private LocalDate createdDate;
    
    

    public OpinionUpdateReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OpinionUpdateReq(String employeeId, String title, String message, LocalDate createdDate) {
        this.employeeId = employeeId;
        this.title = title;
        this.message = message;
        this.createdDate = createdDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
