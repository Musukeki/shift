package com.example.p01.dao;

import com.example.p01.entity.Salary;
import com.example.p01.entity.SalaryId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryDao extends JpaRepository<Salary, SalaryId> {
    @Transactional
    @Modifying
    @Query(value = "insert into salary " +
            "(`employee_id`,`year_month`,`base_salary`,`hourlyRate`,`overtime_pay`,`deduction`,`insurance_fee`,`total_salary`) values " +
            "(?1,?2,?3,?4,?5,?6,?7,?8)",nativeQuery = true)
    public void addSalary(String employeeId,String yearMonth,int baseSalary,int hourlyRate,int overtimePay,
                          int deduction,int insuranceFee,int totalSalary);

    @Query(value = "select * from salary where `year_month` = ?1",nativeQuery = true)
    public List<Salary> getSalaryById(String yearMonth);
}
