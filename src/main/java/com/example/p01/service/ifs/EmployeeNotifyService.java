package com.example.p01.service.ifs;

import com.example.p01.vo.EmployeeNotifyVo.AddEmployeeNotifyReq;
import com.example.p01.vo.EmployeeNotifyVo.EmployeeNotifyRes;
import com.example.p01.vo.EmployeeNotifyVo.UpdateEmployeeNotifyReq;

import java.time.LocalDate;

public interface EmployeeNotifyService {

    public EmployeeNotifyRes addEmployeeNotify(AddEmployeeNotifyReq addEmployeeNotifyReq) throws Exception;

    public EmployeeNotifyRes updateEmployeeNotify(UpdateEmployeeNotifyReq updateEmployeeNotifyReq) throws Exception;

    public EmployeeNotifyRes getEmployeeNotifyById(String employeeId) throws Exception;

    public EmployeeNotifyRes getEmployeeNotifyById(int  id) throws Exception;

    public EmployeeNotifyRes getAllEmployeeNotify() throws Exception;
}
