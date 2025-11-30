package com.example.p01.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.p01.dto.DeleteScheduleDto;
import com.example.p01.dto.GetCanSupEmployeeDto;
import com.example.p01.dto.GetStaffCanWorkDayDto;
import com.example.p01.dto.GetThisDayScheduleDto;
import com.example.p01.dto.Schedule;
import com.example.p01.dto.ShiftWorkDto;
import com.example.p01.entity.PreSchedule;
import com.example.p01.vo.headVo.BasicRes;
import com.example.p01.vo.schedule.PrettySchedule;
import com.example.p01.vo.schedule.SelectScheduleByIdAndDateRes;
import com.example.p01.vo.schedule.ShiftDetail;
import com.example.p01.vo.staffVo.GetCanSupEmployeeReq;
import com.example.p01.vo.staffVo.GetScheduleRes;
import com.example.p01.vo.staffVo.PreScheduleUpdateReq;

public interface PreScheduleService {

	public BasicRes preScheduleCreate(String employeeId) throws Exception;

	public BasicRes preScheduleUpdate(PreScheduleUpdateReq req) throws Exception;

	public BasicRes createAllPreSchedule() throws Exception;

	public GetScheduleRes getScheduleByEmployeeId(String employeeId);

	public GetScheduleRes getAcceptScheduleByEmployeeId(String employeeId);

	public GetScheduleRes getAllSchedule();

	// 取得可支援上班的員工
	public List<GetCanSupEmployeeDto> getCanSupEmployee(GetCanSupEmployeeReq req);

	// 取得當天所有人的班表
	public List<GetThisDayScheduleDto> getThisDaySchedule(LocalDate thisDay);

	// 取得員工能上班的時間
	public GetStaffCanWorkDayDto getStaffCanWorkDay(String employeeId);

	// 新增班別(sfift_work)
	public BasicRes addShiftWork(ShiftWorkDto shiftWorkDto) throws Exception;

	// 更新班別(sfift_work)
	public BasicRes updateShiftWork(ShiftWorkDto shiftWorkDto) throws Exception;

	// 刪除班別(sfift_work)
	public BasicRes deleteShiftWork(int shiftWorkId) throws Exception;

	// 已排過的班表
	public List<Schedule> schedule();

	// 整理所有班表
	public PrettySchedule prettySchedule();

	// 沒有休假的
	public PrettySchedule prettyScheduleNotLeave(LocalDate start,LocalDate end);

	// 新增班表
	public BasicRes addSchedule(PreScheduleUpdateReq req) throws Exception;

	// 刪除班表
	public BasicRes deleteSchedule(DeleteScheduleDto req) throws Exception;

	// 整理所有班表用月份搜尋
//	public PrettySchedule prettyScheduleByMonth(LocalDate start ,LocalDate end);
	
	// 補打卡要用的
	public SelectScheduleByIdAndDateRes selectScheduleByIdAndDate(String employeeId ,LocalDate date);


}
