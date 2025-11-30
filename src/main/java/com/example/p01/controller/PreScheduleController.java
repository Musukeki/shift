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

import com.example.p01.dao.PreScheduleMyBatisDao;
import com.example.p01.dto.DeleteScheduleDto;
import com.example.p01.dto.GetStaffCanWorkDayDto;
import com.example.p01.dto.GetThisDayScheduleDto;
import com.example.p01.dto.Schedule;
import com.example.p01.dto.ShiftWorkDto;
import com.example.p01.entity.PreSchedule;
import com.example.p01.service.ifs.HeadService;
import com.example.p01.service.ifs.PreScheduleService;
import com.example.p01.vo.headVo.BasicRes;
import com.example.p01.vo.schedule.PrettySchedule;
import com.example.p01.vo.schedule.SelectScheduleByIdAndDateRes;
import com.example.p01.vo.schedule.ShiftDetail;
import com.example.p01.vo.staffVo.CreateAllPreSchduleReq;
import com.example.p01.vo.staffVo.GetScheduleRes;
import com.example.p01.vo.staffVo.PreSchduleCreateListReq;
import com.example.p01.vo.staffVo.PreScheduleUpdateReq;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PreScheduleController {
	@Autowired
	private HeadService headService;

	@Autowired
	private PreScheduleService preScheduleService;

	@Autowired
	private PreScheduleMyBatisDao preScheduleMyBatisDao;

	// 員工預排班
	@PostMapping(value = "/PreSchedule/create")
	BasicRes preScheduleCreate(String employeeId) throws Exception {
		return preScheduleService.preScheduleCreate(employeeId);
	}

	@PostMapping(value = "/PreSchedule/update")
	public BasicRes preScheduleUpdate(@RequestBody PreScheduleUpdateReq req) throws Exception {
		return preScheduleService.preScheduleUpdate(req);
	}

//	@PostMapping(value = "/PreSchedule/createAllPreSchedule")
//	public BasicRes createAllPreSchedule(@RequestBody CreateAllPreSchduleReq req) throws Exception {
//		return preScheduleService.createAllPreSchedule(req);
//	}

	@GetMapping(value = "/PreSchedule/getScheduleByEmployeeId")
	public GetScheduleRes getScheduleByEmployeeId(@RequestParam("employeeId") String employeeId) {
		return preScheduleService.getScheduleByEmployeeId(employeeId);
	}

	@GetMapping(value = "/PreSchedule/getAcceptScheduleByEmployeeId")
	public GetScheduleRes getAcceptScheduleByEmployeeId(@RequestParam("employeeId") String employeeId) {
		return preScheduleService.getAcceptScheduleByEmployeeId(employeeId);
	}

	// 取得全部班表
	@GetMapping(value = "/PreSchedule/getAllSchedule")
	public GetScheduleRes getAllSchedule() {
		return preScheduleService.getAllSchedule();
	}

	// 取得當天所有人班表 員工用
	@GetMapping(value = "/PreSchedule/getThisDaySchedule")
	public List<GetThisDayScheduleDto> getThisDaySchedule(@RequestParam("thisDay") LocalDate thisDay) {
		return preScheduleMyBatisDao.getThisDaySchedule(thisDay);
	}

	// 取得員工能上班的時間
	@GetMapping(value = "/PreSchedule/getStaffCanWorkDay")
	public GetStaffCanWorkDayDto getStaffCanWorkDay(@RequestParam("employeeId") String employeeId) {
		return preScheduleService.getStaffCanWorkDay(employeeId);
	}

	// 取得可支援上班的員工

	// 新增班別(sfift_work)
	@PostMapping(value = "/PreSchedule/addShiftWork")
	public BasicRes addShiftWork(@RequestBody ShiftWorkDto shiftWorkDto) throws Exception {
		return preScheduleService.addShiftWork(shiftWorkDto);
	}

	// 更新班別(sfift_work)
	@PostMapping(value = "/PreSchedule/updateShiftWork")
	public BasicRes updateShiftWork(@RequestBody ShiftWorkDto shiftWorkDto) throws Exception {
		return preScheduleService.updateShiftWork(shiftWorkDto);
	}

	// 刪除班別(sfift_work)
	@PostMapping(value = "/PreSchedule/deleteShiftWork")
	public BasicRes deleteShiftWork(@RequestParam("shiftWorkId") int shiftWorkId) throws Exception {
		return preScheduleService.deleteShiftWork(shiftWorkId);
	}

	// 已排過的班表
	@GetMapping(value = "/PreSchedule/schedule")
	public List<Schedule> schdule() {
		return preScheduleService.schedule();
	}

	@GetMapping(value = "/PreSchedule/prettySchedule")
	public PrettySchedule prettySchedule() {
		return preScheduleService.prettySchedule();
	}

	// 沒有休假的
	@GetMapping(value = "/PreSchedule/prettyScheduleNotLeave")
	public PrettySchedule prettyScheduleNotLeave(@RequestParam("start")LocalDate start,@RequestParam("end")LocalDate end) {
		return preScheduleService.prettyScheduleNotLeave(start,end);
	}

	// 新增班表
	@PostMapping(value = "/PreSchedule/addSchedule")
	public BasicRes addSchedule(@RequestBody PreScheduleUpdateReq preSchedule) throws Exception {
		return preScheduleService.addSchedule(preSchedule);
	}

	// 刪除班表
	@PostMapping(value = "/PreSchedule/deleteSchedule")
	public BasicRes deleteSchedule(@RequestBody DeleteScheduleDto req) throws Exception {
		return preScheduleService.deleteSchedule(req);
	}
	
	@GetMapping(value = "/PreSchedule/selectScheduleByIdAndDate")
	public SelectScheduleByIdAndDateRes selectScheduleByIdAndDate(//
			@RequestParam("employeeId")String employeeId, //
			@RequestParam("date")LocalDate date) {
		return preScheduleService.selectScheduleByIdAndDate(employeeId, date);
	}

}
