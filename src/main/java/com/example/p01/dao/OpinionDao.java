package com.example.p01.dao;

import com.example.p01.entity.Opinion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OpinionDao extends JpaRepository<Opinion,Integer> {

    //新增意見
    @Modifying
    @Transactional
    @Query(value = "insert into opinion " +
            "(employee_id,title,message,created_date) values " +
            "(?1,?2,?3,?4)",nativeQuery = true)
    public void addOpinion(String employeeId,String title, String message, LocalDate createdDate);

    //更新意見
    @Modifying
    @Transactional
    @Query(value = "update opinion set " +
            "title = case when ?2 is null or ?2 = '' then title else ?2 end, " +
            "message = case when ?3 is null or ?3 = '' then message else ?3 end, " +
            "where employee_id = ?1 and created_date = ?4",nativeQuery = true)
    public void updateOpinion(String employeeId,String title, String message, LocalDate createdDate);

    //刪除意見
    @Modifying
    @Transactional
    @Query(value = "delete from opinion where employee_id = ?1 and created_date = ?2",nativeQuery = true)
    public void deleteOpinion(String employeeId,LocalDate createdDate);

    //查詢單筆員工意見
    @Query(value = "select * from opinion where employee_id = ?1 and created_date = ?2",nativeQuery = true)
    public Opinion getOpinionById(String employeeId,LocalDate createdDate);

    //查詢該員工所有意見
    @Query(value = "select * from opinion where employee_id = ?1",nativeQuery = true)
    public List<Opinion> getOpinionById(String employeeId);

    @Query(value = "select title,message from opinion where employee_id = ?1 and created_date between ?2 and ?3",nativeQuery = true)
    public List<String> getOpinionById(String employeeId,LocalDate startDate,LocalDate endDate);

    //查詢所有員工意見
    @Query(value = "select * from opinion",nativeQuery = true)
    public List<Opinion> getAllOpinion();
}
