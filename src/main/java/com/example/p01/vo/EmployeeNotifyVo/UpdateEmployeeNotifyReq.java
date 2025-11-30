package com.example.p01.vo.EmployeeNotifyVo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UpdateEmployeeNotifyReq {

    @NotNull(message = "id為空")
    private int id;

    @NotBlank(message = "員工ID為空")
    private String employeeId;

    @NotNull(message = "創建日期為空")
    private LocalDate createdDate;

    private String title;

    private String message;

    private String linkUrl;

    public UpdateEmployeeNotifyReq() {
    }

    public UpdateEmployeeNotifyReq(int id, String employeeId, String title, String message, String linkUrl,LocalDate createdDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.createdDate = createdDate;
        this.title = title;
        this.message = message;
        this.linkUrl = linkUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
