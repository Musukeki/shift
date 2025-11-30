package com.example.p01.vo.OpinionVo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class OpinionCreateReq {

    @NotBlank(message = "員工ID為空")
    private String employeeId;

    @NotBlank(message = "意見標題為空")
    private String title;

    @NotBlank(message = "意見內容為空")
    private String message;

    @NotNull(message = "新增意見日期為空")
    private LocalDate createdDate;
    
    

    public OpinionCreateReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OpinionCreateReq(String employeeId, String title, String message, LocalDate createdDate) {
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
