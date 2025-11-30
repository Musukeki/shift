package com.example.p01.dao;

import com.example.p01.entity.EmployeeNotify;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeNotifyDao extends JpaRepository<EmployeeNotify, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into employee_notify " +
            "(employee_id,title,message,link_url,created_date) " +
            "values " +
            "(?1,?2,?3,?4,?5)",nativeQuery = true)
    public void addEmployeeNotify(String employeeId,
                                  String title,
                                  String message,
                                  String linkUrl,
                                  LocalDate createdDate);

    @Transactional
    @Modifying
    @Query(value = "update employee_notify set " +
            "employee_id = case when ?2 = '' or ?2 is null then employee_id else ?2 end, " +
            "title = case when ?3 = '' or ?3 is null then title else ?3 end, " +
            "message = case when ?4 = '' or ?4 is null then message else ?4 end, " +
            "link_url = case when ?5 = '' or ?5 is null then link_url else ?5 end, " +
            "created_date = case when ?6 = '' or ?6 is null then created_date else ?6 end " +
            "where id = ?1 ",nativeQuery = true)
    public void updateEmployeeNotify(int id,
                                     String employeeId,
                                     String title,
                                     String message,
                                     String linkUrl,
                                     LocalDate createdDate);

    @Query(value = "select * from employee_notify where employee_id = ?1 ",nativeQuery = true)
    public List<EmployeeNotify> getEmployeeNotifyByEmployeeId(String employeeId);

    @Query(value = "select * from employee_notify where id = ?1",nativeQuery = true)
    public EmployeeNotify getEmployeeNotifyById(int id);

    @Query(value = "select * from employee_notify order by created_date desc",nativeQuery = true)
    public List<EmployeeNotify> getAllEmployeeNotify();

}
