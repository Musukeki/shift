// HolidayDao.java（維持）
package com.example.p01.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import com.example.p01.entity.Holiday;

public interface HolidayDao extends JpaRepository<Holiday, Integer> {

    @Query(value = "SELECT h.holiday_date AS holidayDate, h.name AS name FROM holiday h", nativeQuery = true)
    List<HolidayRow> selectHoliday();

    interface HolidayRow {
        LocalDate getHolidayDate();
        String getName();
    }
    
    @Query(value="select count(*) from holiday where holiday_date = ?1",nativeQuery = true)
    public int selectDate(LocalDate date);
}
