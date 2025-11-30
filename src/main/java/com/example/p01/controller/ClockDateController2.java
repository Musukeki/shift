package com.example.p01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.p01.dao.ClockDateDao;
import com.example.p01.dto.ClockOffDto;
import com.example.p01.dto.GetMissClockListDto;
import com.example.p01.dto.MissClockApplyDto;
import com.example.p01.service.ifs.ClockDateService;
import com.example.p01.service.impl.ClockServiceImpl;
import com.example.p01.vo.ClockDateVo.AddClockDateRes;
import com.example.p01.vo.ClockDateVo.AddClockOffReq;
import com.example.p01.vo.ClockDateVo.AddClockOnReq;
import com.example.p01.vo.ClockDateVo.FixClockReq;
import com.example.p01.vo.ClockDateVo.GetOneRes;
import com.example.p01.vo.headVo.BasicRes;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClockDateController2 {

	@Autowired
	private ClockDateService clockDateService;

	@Autowired
	private ClockDateDao clockDateDao;

	// 下班打卡2
	@PostMapping(value = "/clock/off2")
	public BasicRes clickOff2(@RequestBody ClockOffDto dto) throws Exception {
		return clockDateService.clockOff2(dto);
	}

	// 補打卡申請
	@PostMapping(value = "/clock/missClockApply")
	public BasicRes missClockApply(@RequestBody List<MissClockApplyDto> req) throws Exception {
		return clockDateService.missClockApply(req);
	}

	// 補打卡核准
	@PostMapping(value = "/clock/missClockApprove")
	public BasicRes missClockApprove(//
			@RequestParam("id") int id, //
			@RequestParam("accept") boolean accept) {
		return clockDateService.missClockApprove(id, accept);
	}

	// 補打卡核准列表
	@GetMapping(value = "/clock/getMissClockList")
	public List<GetMissClockListDto> getMissClockList() {
		return clockDateService.getMissClockList();
	}
	
	// 取單筆補打卡申請資料
	@GetMapping(value = "/clock/getMissClockById")
	public MissClockApplyDto getMissClockById(@RequestParam("id") int id) {
		return clockDateService.getMissClock(id);
	}
}
