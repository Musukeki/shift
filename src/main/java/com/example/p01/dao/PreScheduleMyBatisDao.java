package com.example.p01.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.p01.dto.DeleteScheduleDto;
import com.example.p01.dto.GetCanSupEmployeeDto;
import com.example.p01.dto.GetThisDayScheduleDto;
import com.example.p01.dto.ShiftWorkDto;
import com.example.p01.entity.PreSchedule;
import com.example.p01.dto.Schedule;

@Mapper
public interface PreScheduleMyBatisDao {

	public List<GetThisDayScheduleDto> getThisDaySchedule(//
			@Param("thisDay") LocalDate thisDay);

	public List<LocalDate> getStaffCanWorkDay(//
			@Param("id") String employeeId);

	// 下面三個是班別的新增、修改、刪除
	public int addShiftWork(ShiftWorkDto shiftWorkDto);

	public void updateShiftWork(ShiftWorkDto shiftWorkDto);

	public void deleteShiftWork(@Param("shiftWorkId") int shiftWorkId);

	public int countShiftWorkId(@Param("shiftWorkId") int shiftWorkId);
	
	public ShiftWorkDto selectShiftWork(@Param("shiftWorkId") int shiftWorkId);

	// 找可以支援的員工
	public List<GetCanSupEmployeeDto> getCanSupEmployee(//
			@Param("canSupDate") LocalDate canSupDate, //
			@Param("startTime") LocalTime startTime, //
			@Param("endTime") LocalTime endTime//
	);

	// 已排過的班表
	public List<Schedule> schedule();

	// 用員工 Id 和日期取的當天班表
	public List<Schedule> getLeaveDateSchedule(//
			@Param("employeeId") String employeeId, //
			@Param("applyDate") LocalDate applyDate);

	public int countShiftWorkByApplyDate(//
			@Param("employeeId") String employeeId, //
			@Param("applyDate") LocalDate applyDate);
	
	public int addSchdule(PreSchedule preSchedule);
	
	public int countPreScheDule(//
			@Param("employeeId") String employeeId, //
			@Param("applyDate") LocalDate applyDate,//
			@Param("shiftWorkId") int shiftWorkId);
	
	public int deleteSchedule(DeleteScheduleDto dto);
	
	List<PreSchedule> findAllByApplyDateBetween(@Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}
