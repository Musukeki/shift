package com.example.p01.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SalaryId implements Serializable {

    private String employeeId;

    private String yearMonth;

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
}
