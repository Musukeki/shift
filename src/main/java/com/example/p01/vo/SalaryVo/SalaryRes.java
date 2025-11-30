package com.example.p01.vo.SalaryVo;

import com.example.p01.entity.Salary;

import java.util.List;

public class SalaryRes {
    private int code;
    private String message;
    private List<Salary> salaryList;

    public SalaryRes() {
    }

    public SalaryRes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public SalaryRes(int code, String message, List<Salary> salaryList) {
        this.code = code;
        this.message = message;
        this.salaryList = salaryList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Salary> getSalaryList() {
        return salaryList;
    }

    public void setSalaryList(List<Salary> salaryList) {
        this.salaryList = salaryList;
    }
}
