package com.example.p01.vo.EmployeeNotifyVo;

import com.example.p01.entity.EmployeeNotify;

import java.util.List;

public class EmployeeNotifyRes {
    private int code;
    private String message;
    private EmployeeNotify employeeNotify;
    private List<EmployeeNotify> employeeNotifyList;

    public EmployeeNotifyRes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public EmployeeNotifyRes(int code, String message, EmployeeNotify employeeNotify) {
        this.code = code;
        this.message = message;
        this.employeeNotify = employeeNotify;
    }

    public EmployeeNotifyRes(int code, String message, List<EmployeeNotify> employeeNotifyList) {
        this.code = code;
        this.message = message;
        this.employeeNotifyList = employeeNotifyList;
    }

    public EmployeeNotify getEmployeeNotify() {
        return employeeNotify;
    }

    public void setEmployeeNotify(EmployeeNotify employeeNotify) {
        this.employeeNotify = employeeNotify;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<EmployeeNotify> getEmployeeNotifyList() {
        return employeeNotifyList;
    }

    public void setEmployeeNotifyList(List<EmployeeNotify> employeeNotifyList) {
        this.employeeNotifyList = employeeNotifyList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
