package com.example.p01.controller;

import com.example.p01.service.ifs.EmployeeNotifyService;
import com.example.p01.vo.EmployeeNotifyVo.AddEmployeeNotifyReq;
import com.example.p01.vo.EmployeeNotifyVo.EmployeeNotifyRes;
import com.example.p01.vo.EmployeeNotifyVo.UpdateEmployeeNotifyReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class EmployeeNotifyController {

    @Autowired
    EmployeeNotifyService employeeNotifyService;

    @GetMapping("/getAll/employeeNotify")
    public EmployeeNotifyRes getAllEmployeeNotify() throws Exception{
        return employeeNotifyService.getAllEmployeeNotify();
    }

    @GetMapping("/get/employeeNotify")
    public EmployeeNotifyRes getEmployeeNotifyById(@RequestParam("employeeId") String employeeId) throws Exception{
        return employeeNotifyService.getEmployeeNotifyById(employeeId);
    }

    @GetMapping("/get/employeeNotify/id")
    public EmployeeNotifyRes getEmployeeNotifyById(@RequestParam("id") int id) throws Exception{
        return employeeNotifyService.getEmployeeNotifyById(id);
    }

    @PostMapping("/add/employeeNotify")
    public EmployeeNotifyRes addEmployeeNotify(@RequestBody AddEmployeeNotifyReq addEmployeeNotifyReq) throws Exception{
        return employeeNotifyService.addEmployeeNotify(addEmployeeNotifyReq);
    }

    @PutMapping("/update/employeeNotify")
    public EmployeeNotifyRes updateEmployeeNotfiy(@RequestBody UpdateEmployeeNotifyReq updateEmployeeNotifyReq) throws Exception{
        return employeeNotifyService.updateEmployeeNotify(updateEmployeeNotifyReq);
    }
}
