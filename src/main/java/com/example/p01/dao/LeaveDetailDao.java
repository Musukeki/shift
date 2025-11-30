package com.example.p01.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.p01.dto.LeaveDetailDto;

@Mapper // 標註此介面為 MyBatis 用
public interface LeaveDetailDao {

	public void addLeaveDetail(List<LeaveDetailDto> leaveClassDto);

	public int countLeaveTimeByIdandDate(//
			@Param("employeeId") String employeeId, //
			@Param("startDate") LocalDate startDate, //
			@Param("endDate") LocalDate endDate);

}
