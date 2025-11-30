package com.example.p01.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.p01.dto.GetAllLeaveDto;
import com.example.p01.dto.GetAllLeaveForSalaryDto;
import com.example.p01.dto.GetApplicationAndNameDto;
import com.example.p01.service.ifs.LeaveService;
import com.example.p01.vo.headVo.BasicRes;
import com.example.p01.vo.staffVo.LeaveApplyByDateReq;
import com.example.p01.vo.staffVo.LeaveApprovedReq;
import com.example.p01.vo.staffVo.LeaveTimeRes;
import com.example.p01.vo.staffVo.leaveApplyReq;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	@PostMapping(value = "/leave/create")
	public BasicRes leaveApply(@RequestBody leaveApplyReq req) throws Exception {
		return leaveService.leaveApply(req);
	}

	@PostMapping(value = "/leave/leaveApproved")
	public BasicRes leaveApproved(@RequestBody LeaveApprovedReq req) {
		return leaveService.leaveApproved(req);
	}

	@GetMapping(value = "/leave/getLeaveByMonth")
	public List<GetAllLeaveDto> getAllLeave(//
			@RequestParam("startDate") LocalDate startDate, //
			@RequestParam("endDate") LocalDate endDate, //
			@RequestParam("employeeId") String employeeId, //
			@RequestParam("leaveId") int leaveId) {
		return leaveService.getAllLeave(startDate, endDate, employeeId, leaveId);
	}

	@GetMapping(value = "/leave/getAllApplication")
	public List<GetApplicationAndNameDto> getApplication() {
		return leaveService.getApplication();
	}

	@GetMapping(value = "/leave/getApprovedLeave")
	public List<GetApplicationAndNameDto> getApprovedLeave(@RequestParam("start") LocalDate startDate,
			@RequestParam("end") LocalDate endDate) {
		return leaveService.getApprovedLeave(startDate, endDate);
	}

	// 請整天的假
	@PostMapping(value = "/leave/leaveApplyByDate")
	public BasicRes leaveApplyByDate(@RequestBody LeaveApplyByDateReq req) throws Exception {
		return leaveService.leaveApplyByDate(req);
	}

	@GetMapping(value = "/leave/getAllLeaveForSalary")
	public List<GetAllLeaveForSalaryDto> getAllLeaveForSalary(@RequestParam("start") LocalDate startDate,
			@RequestParam("end") LocalDate endDate, @RequestParam("employeeId") String employeeId) {
		return leaveService.getAllLeaveForSalary(startDate, endDate, employeeId);
	}

	@GetMapping(value = "/leave/getLeaveByLeaveId")
	public List<GetAllLeaveForSalaryDto> getLeaveByLeaveId(//
			@RequestParam("leaveId") int leaveId) {
		return leaveService.getLeaveByLeaveId(leaveId);
	}

}
