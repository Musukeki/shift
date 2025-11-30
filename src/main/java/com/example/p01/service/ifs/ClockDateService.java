// ClockDateService.java（介面）
package com.example.p01.service.ifs;


import java.time.LocalDate;
import java.util.List;

import com.example.p01.dto.ClockDate2;
import com.example.p01.dto.ClockOffDto;
import com.example.p01.dto.GetMissClockListDto;
import com.example.p01.dto.MissClockApplyDto;
import com.example.p01.dto.response.ApiRes;
import com.example.p01.entity.ClockDate;
import com.example.p01.vo.ClockDateVo.AddClockOffReq;
import com.example.p01.vo.ClockDateVo.AddClockOnReq;
import com.example.p01.vo.ClockDateVo.AddRestEndReq;
import com.example.p01.vo.ClockDateVo.AddRestStartReq;
import com.example.p01.vo.ClockDateVo.FixClockReq;
import com.example.p01.vo.ClockDateVo.GetOneRes;
import com.example.p01.vo.ClockDateVo.ReClockReq;
import com.example.p01.vo.headVo.BasicRes;

public interface ClockDateService {

    
    BasicRes clockOff2(ClockOffDto dto) throws Exception;
    
    //
    
    public ApiRes<List<ClockDate>> getSingleClock(String employeeId, LocalDate workDate);

    public ApiRes<List<ClockDate>> getAllClock();

    public ApiRes<List<ClockDate>> getSingleHistoryClock(String employeeId,LocalDate startDate,LocalDate endDate);
    
    public List<ClockDate2> getSingleHistoryClock2(String employeeId,LocalDate startDate,LocalDate endDate);

    public ApiRes<ClockDate> addClockOn(AddClockOnReq req);

    public ApiRes<ClockDate> addClockOff(AddClockOffReq req);

    public ApiRes<ClockDate> addRestStart(AddRestStartReq req);

    public ApiRes<ClockDate> addRestEnd(AddRestEndReq req);
    
    public ApiRes<ClockDate> reClockAll(ReClockReq req);

    public ApiRes<ClockDate> reClockPart(ReClockReq req);
    
    // 補打卡申請
    public BasicRes missClockApply(List<MissClockApplyDto> req) throws Exception;

    // 補打卡核准
    public BasicRes missClockApprove(int id,boolean accept);
    
    // 補打卡核准列表
    public List<GetMissClockListDto> getMissClockList();
    
    // 取單筆補打卡申請資料
    public MissClockApplyDto getMissClock(int id);
}
