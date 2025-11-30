package com.example.p01.controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.p01.dto.ClockDate2;
import com.example.p01.dto.response.ApiRes;
import com.example.p01.entity.ClockDate;
import com.example.p01.service.ifs.ClockDateService;
import com.example.p01.vo.ClockDateVo.AddClockOffReq;
import com.example.p01.vo.ClockDateVo.AddClockOnReq;
import com.example.p01.vo.ClockDateVo.AddRestEndReq;
import com.example.p01.vo.ClockDateVo.AddRestStartReq;
import com.example.p01.vo.ClockDateVo.ReClockReq;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClockDateController {

    @Autowired
    private ClockDateService clockDateService;

    // ? 查詢個別員工"固定日期"打卡紀錄
    @GetMapping("/single/date")
    public ApiRes<List<ClockDate>> getSingleClock(
            @RequestParam("employeeId") String employeeId, //
            @RequestParam("workDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate workDate) {
        return clockDateService.getSingleClock(employeeId, workDate);
    }

    // ? 查詢單一員工歷史打卡紀錄
    @GetMapping("/single/history")
    public ApiRes<List<ClockDate>> getSingleHistoryClock(
            @RequestParam("employeeId") String employeeId,
            @RequestParam("start") LocalDate startDate,
            @RequestParam("end") LocalDate endDate) {
        return clockDateService.getSingleHistoryClock(employeeId,startDate,endDate);
    }
    
    
    // ? 查詢單一員工歷史打卡紀錄
    @GetMapping("/single/history2")
    public List<ClockDate2> getSingleHistoryClock2(
            @RequestParam("employeeId") String employeeId,
            @RequestParam("start") LocalDate startDate,
            @RequestParam("end") LocalDate endDate) {
        return clockDateService.getSingleHistoryClock2(employeeId,startDate,endDate);
    }
   

    // ? 查詢所有員工歷史打卡紀錄
    @GetMapping("/all")
    public ApiRes<List<ClockDate>> getAllClock() {
        return clockDateService.getAllClock();
    }

    // ? 上班打卡
    @PostMapping("/on")
    public ApiRes<ClockDate> addClockOn(@RequestBody AddClockOnReq req) {
        return clockDateService.addClockOn(req);
    }

    // ? 下班打卡
    @PostMapping("/off")
    public ApiRes<ClockDate> addClockOff(@RequestBody AddClockOffReq req) {
        return clockDateService.addClockOff(req);
    }

    // ? 休息開始
    @PostMapping("/rest/start")
    public ApiRes<ClockDate> addRestStart(@RequestBody AddRestStartReq req) {
        return clockDateService.addRestStart(req);
    }

    // ? 休息結束
    @PostMapping("/rest/end")
    public ApiRes<ClockDate> addRestEnd(@RequestBody AddRestEndReq req) {
        return clockDateService.addRestEnd(req);
    }

    // ? 補打卡(新增)
    @PostMapping("/rec/all")
    public ApiRes<ClockDate> reClockAll(@RequestBody ReClockReq req) {
        return clockDateService.reClockAll(req);
    }

    // ? 補打卡(更新)
    @PostMapping("/rec/part")
    public ApiRes<ClockDate> reClockPart(@RequestBody ReClockReq req) {
        return clockDateService.reClockPart(req);
    }
}