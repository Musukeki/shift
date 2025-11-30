package com.example.p01.service.ifs;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.p01.dto.GetAllLeaveDto;
import com.example.p01.dto.GetAllLeaveForSalaryDto;
import com.example.p01.dto.GetApplicationAndNameDto;
import com.example.p01.vo.headVo.BasicRes;
import com.example.p01.vo.staffVo.LeaveApplyByDateReq;
import com.example.p01.vo.staffVo.LeaveApprovedReq;
import com.example.p01.vo.staffVo.LeaveTimeRes;
import com.example.p01.vo.staffVo.leaveApplyReq;

public interface LeaveService {

	public BasicRes leaveApply(leaveApplyReq req) throws Exception;
	
	public BasicRes leaveApplyByDate(LeaveApplyByDateReq req) throws Exception;
	
	public BasicRes leaveApproved(LeaveApprovedReq req);
	
	public List<GetAllLeaveDto> getAllLeave(LocalDate startDate, LocalDate endDate,String employeeId,int leaveId);
	
	public List<GetApplicationAndNameDto> getApplication();
	
	public List<GetApplicationAndNameDto> getApprovedLeave(LocalDate startDate, LocalDate endDate);
	
	public List<GetAllLeaveForSalaryDto> getLeaveByLeaveId(int leaveId);
	
	public List<GetAllLeaveForSalaryDto> getAllLeaveForSalary(//
			 LocalDate startDate, //
			 LocalDate endDate,//
			 String employeeId);

}
