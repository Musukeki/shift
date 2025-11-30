package com.example.p01.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.p01.dto.GetAllLeaveDto;
import com.example.p01.dto.GetAllLeaveForSalaryDto;
import com.example.p01.dto.GetApplicationAndNameDto;
import com.example.p01.dto.LeaveApplicationDto;

@Mapper
public interface LeaveApplicationDao {

	public void addLeaveApplication(LeaveApplicationDto LeaveApplicationDto);

	public int getLatestleaveId();

	public void updateLeaveApproved(//
			@Param("inputLeaveId") int leaveId, //
			@Param("inputApproved") boolean approved);

	public List<GetAllLeaveDto> getAllLeaveApplication(//
			@Param("startDate") LocalDate startDate, //
			@Param("endDate") LocalDate endDate,//
			@Param("employeeId") String employeeId,//
			@Param("leaveId") int leaveId);

	public List<GetAllLeaveDto> getAllLeaveByEmpolyeeId(@Param("employeeId") String employeeId);

	public List<GetApplicationAndNameDto> getApplication();

	public List<GetApplicationAndNameDto> getApprovedLeave(//
			@Param("startDate") LocalDate startDate, //
			@Param("endDate") LocalDate endDate);
	
	public List<GetAllLeaveForSalaryDto> getAllLeaveForSalary(//
			@Param("startDate") LocalDate startDate, //
			@Param("endDate") LocalDate endDate,//
			@Param("employeeId") String employeeId);
	
	public List<GetAllLeaveForSalaryDto> getLeaveByLeaveId(//
			@Param("leaveId") int leaveId);

}
