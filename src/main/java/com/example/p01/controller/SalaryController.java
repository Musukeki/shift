package com.example.p01.controller;

import com.example.p01.service.ifs.SalaryService;
import com.example.p01.vo.SalaryVo.SalaryRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping("/getMonthOfSalary")
    public SalaryRes getSalaryById(@RequestParam("yearMonth") String yearMonth) throws Exception{
        return salaryService.getSalaryById(yearMonth);
    }
}
