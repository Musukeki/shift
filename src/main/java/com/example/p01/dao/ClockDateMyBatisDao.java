package com.example.p01.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.p01.dto.ClockDate2;
import com.example.p01.dto.ClockDateDto;
import com.example.p01.dto.ClockOffDto;
import com.example.p01.dto.GetMissClockListDto;
import com.example.p01.dto.MissClockApplyDto;

@Mapper
public interface ClockDateMyBatisDao {

	// 打下班卡
	public void clockOff(ClockOffDto clockOffDto);
    
	// 找最新一筆打上班卡資訊
	public int checkClockOn(//
			@Param("employeeId") String employeeId, //
			@Param("workDate") LocalDate workDate);
	
	// 找最新一筆打下班卡資訊
	public int checkClockOff(//
			@Param("employeeId") String employeeId, //
			@Param("workDate") LocalDate workDate);
	
	// 最新一筆打卡資訊
	public ClockDateDto selectClockDate(//
			@Param("employeeId") String employeeId);
	
	// 算薪水要用的，取了三張表
	public List<ClockDate2> getSingleHistoryClock2(//
			@Param("employeeId") String employeeId,//
			@Param("startDate") LocalDate startDate,//
			@Param("endDate") LocalDate endDate);
	
	// 補打卡申請
	public int missClockApply(List<MissClockApplyDto> missClockApplyDto);
	
	// 補打卡核准
	public int missClockApprove(//
			@Param("id") int id,//
			@Param("accept") boolean accept);
	
	// 用 id 取補打卡資料
	public MissClockApplyDto getMissClockById(@Param("id")int id);
	
	// 補打卡申請的列表
	public List<GetMissClockListDto> getMissClockList();

}
