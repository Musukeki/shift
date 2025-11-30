package com.example.p01.dao;

import com.example.p01.entity.Notify;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotifyDao extends JpaRepository<Notify,Integer> {

    //新增通知
    @Modifying
    @Transactional
    @Query(value = "insert into notify " +
            "(title,message,created_date,link_url,is_publish) values " +
            "(?1,?2,?3,?4,?5)",nativeQuery = true)
    public void addNotify(String title, String message, LocalDate createdDate, String linkUrl,boolean isPublish);

    //更新通知
    @Modifying
    @Transactional
    @Query(value = "update notify set " +
            "title = case when ?2 is null then title else ?2 end, " +
            "message = case when ?3 is null then message else ?3 end, " +
            "created_date = case when ?4 is null then created_date else ?4 end, " +
            "link_url = case when ?5 is null then link_url else ?5 end, " +
            "is_publish = case when ?6 is null then is_publish else ?6 end " +
            "where id = ?1",nativeQuery = true)
    public void updateNotify(int id, String title, String message, LocalDate createdDate, String linkUrl,boolean isPublish);

    //刪除通知
    @Modifying
    @Transactional
    @Query(value = "delete from notifty where id = ?1",nativeQuery = true)
    public void deleteNotify(int id);

    @Query(value = "select * from notify where id = ?1",nativeQuery = true)
    public Notify getNotifyById(int id);

    @Query(value = "select * from notify order by created_date desc",nativeQuery = true)
    public List<Notify> getAllNotify();

    @Query(value = "select * from notify where is_publish = true " +
            "and created_date between ?1 and ?2 " +
            "order by created_date desc",nativeQuery = true)
    public List<Notify> getTrueNotify(LocalDate startDate, LocalDate endDate);

}
