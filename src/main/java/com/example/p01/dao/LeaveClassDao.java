package com.example.p01.dao;

import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.p01.dto.LeaveClassDto;

@Mapper // 標註此介面為 MyBatis 用
public interface LeaveClassDao {

	public void addLeaveClass(LeaveClassDto leaveClassDto);

//	public void addLeaveClass(//
//			@Param("leaveDate") LocalDate leaveDate, //
//			@Param("startTime") LocalTime startTime, //
//			@Param("endTime") LocalTime endTime, //
//			@Param("leaveHours") double leaveHours);
	



	
}
