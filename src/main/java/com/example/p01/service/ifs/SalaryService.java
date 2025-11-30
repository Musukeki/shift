package com.example.p01.service.ifs;

import com.example.p01.vo.SalaryVo.SalaryRes;


public interface SalaryService {

    public SalaryRes addSalary() throws Exception;

    public SalaryRes getSalaryById(String yearMonth) throws Exception;
    
}
