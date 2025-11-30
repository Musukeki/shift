package com.example.p01.service.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.p01.dao.HeadDao;
import com.example.p01.dao.PreScheduleDao;
import com.example.p01.dao.PreScheduleMyBatisDao;
import com.example.p01.dto.DeleteScheduleDto;
import com.example.p01.dto.GetCanSupEmployeeDto;
import com.example.p01.dto.GetStaffCanWorkDayDto;
import com.example.p01.dto.GetThisDayScheduleDto;
import com.example.p01.dto.Schedule;
import com.example.p01.dto.ScheduleDto;
import com.example.p01.dto.ShiftWorkDto;
import com.example.p01.entity.Employee;
import com.example.p01.entity.PreSchedule;
import com.example.p01.service.ifs.PreScheduleService;
import com.example.p01.vo.headVo.BasicRes;
import com.example.p01.vo.schedule.ApplyDate;
import com.example.p01.vo.schedule.EmployeeList;
import com.example.p01.vo.schedule.PrettySchedule;
import com.example.p01.vo.schedule.SelectScheduleByIdAndDateRes;
import com.example.p01.vo.schedule.ShiftDetail;
import com.example.p01.vo.staffVo.GetCanSupEmployeeReq;
import com.example.p01.vo.staffVo.GetScheduleRes;
import com.example.p01.vo.staffVo.PreSchduleUpdateVo;
import com.example.p01.vo.staffVo.PreScheduleUpdateReq;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class PreScheduleServiceImpl implements PreScheduleService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private HeadDao headDao;

	@Autowired
	private PreScheduleDao preScheduleDao;

	@Autowired
	private PreScheduleMyBatisDao preScheduleMyBatisDao;

	@Transactional(rollbackOn = Exception.class) // 讓執行不是全成功就是全失敗
	@Override
	public BasicRes preScheduleCreate(String employeeId) throws Exception {
		try {
			List<PreSchedule> preScheduleList = new ArrayList<>();
			int year = LocalDate.now().getYear();
			int month = LocalDate.now().getMonthValue();
			int howManyDay = YearMonth.of(year, month).lengthOfMonth();
			LocalDate date = LocalDate.of(year, month, 1);
			for (int i = 0; i < howManyDay; i++) {
				PreSchedule preSchedule = new PreSchedule();
				preSchedule.setEmployeeId(employeeId);
				preSchedule.setApplyDate(date.plusDays(i));
				preSchedule.setAccept(false);

				preScheduleList.add(preSchedule);
			}
			if (howManyDay - LocalDate.now().getDayOfMonth() <= 7) {
				howManyDay = YearMonth.of(year, month + 1).lengthOfMonth();
				date = LocalDate.of(year, month + 1, 1);
				for (int i = 0; i < howManyDay; i++) {
					PreSchedule preSchedule = new PreSchedule();
					preSchedule.setEmployeeId(employeeId);
					preSchedule.setApplyDate(date.plusDays(i));
					preSchedule.setAccept(false);

					preScheduleList.add(preSchedule);
				}
			}

			for (int i = 0; i < preScheduleList.size(); i++) {
				entityManager.persist(preScheduleList.get(i));
			}
			entityManager.flush();
			entityManager.clear();

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}

		return new BasicRes(200, "おめでとうございます！！！");
	}

	@Transactional(rollbackOn = Exception.class) // 讓執行不是全成功就是全失敗
	@Override
	public BasicRes preScheduleUpdate(PreScheduleUpdateReq req) throws Exception {

		try {
			List<PreSchduleUpdateVo> updateList = req.getPreSchduleUpdateVo();

			for (PreSchduleUpdateVo vo : updateList) {

				preScheduleDao.update(vo.getEmployeeId(), vo.getApplyDate(), //
						vo.getShiftWorkId(), vo.isAccept());
			}
		} catch (Exception e) {
			throw e;
		}
		return new BasicRes(200, "おめでとうございます！！！");
	}

	@Scheduled(cron = "0 0 0 ? * 1#2")
	@Transactional(rollbackOn = Exception.class) // 讓執行不是全成功就是全失敗
	@Override
	public BasicRes createAllPreSchedule() throws Exception {
		// 建立容器裝所有的預設排班
		List<PreSchedule> preScheduleList = new ArrayList<>();

		List<Employee> AllemployeeData = headDao.getAllEmployee();

		int year = LocalDate.now().getYear();
		int month = LocalDate.now().getMonthValue();
		if (month == 12) {
			year = year + 1;
			month = 1;
		} else {
			month = month + 1;
		}

		try {
			int howManyDay = YearMonth.of(year, month).lengthOfMonth();
			LocalDate date = LocalDate.of(year, month, 1);

			for (int i = 0; i < howManyDay; i++) {
				for (Employee employee : AllemployeeData) {

					if (employee.getEmploymentStatus().equals("已離職")) {
						continue;

					}
					PreSchedule preSchedule = new PreSchedule();

					preSchedule.setEmployeeId(employee.getId());
					preSchedule.setApplyDate(date.plusDays(i));
					preSchedule.setAccept(false);

					preScheduleList.add(preSchedule);

				}

			}

			for (int i = 0; i < preScheduleList.size(); i++) {
				entityManager.persist(preScheduleList.get(i));
			}
			entityManager.flush();
			entityManager.clear();
		} catch (Exception e) {
			throw e;
		}

		return new BasicRes(200, "おめでとうございます！！！");
	}

	@Override
	public GetScheduleRes getScheduleByEmployeeId(String employeeId) {

		// 檢查員工Id是否存在
		if (headDao.selectByIdWithoutPwd(employeeId) == null) {
			return new GetScheduleRes(400, "找不到員工Id");
		}

		// 資料庫拿出來的
		List<ScheduleDto> DBlist = preScheduleDao.getScheduleByEmployeeId(employeeId);

		List<ScheduleDto> list = DBlist.stream().map(ps -> new ScheduleDto(//
				ps.getEmployeeId(), ps.getEmployeeName(), ps.getEmployeeTitle(), //
				ps.getEmployeeStatus(), ps.getApplyDate(), //
				ps.getShiftWorkId(), ps.isAccept(), ps.getStartTime(), ps.getEndTime())).toList();

		// 回傳
		return new GetScheduleRes(200, "おめでとうございます！！！", list);

	}

	@Override
	public GetScheduleRes getAcceptScheduleByEmployeeId(String employeeId) {
		// 檢查員工Id是否存在
		if (headDao.selectByIdWithoutPwd(employeeId) == null) {
			return new GetScheduleRes(400, "找不到員工Id");
		}

		// 資料庫拿出來的
		List<ScheduleDto> DBlist = preScheduleDao.getAcceptScheduleByEmployeeId(employeeId);

		List<ScheduleDto> list = DBlist.stream().map(ps -> new ScheduleDto(//
				ps.getEmployeeId(), ps.getEmployeeName(), ps.getEmployeeTitle(), //
				ps.getEmployeeStatus(), ps.getApplyDate(), //
				ps.getShiftWorkId(), ps.isAccept(), ps.getStartTime(), ps.getEndTime())).toList();

		// 回傳
		return new GetScheduleRes(200, "おめでとうございます！！！", list);
	}

	@Override
	public GetScheduleRes getAllSchedule() {
		// 資料庫拿出來的
		List<ScheduleDto> DBlist = preScheduleDao.getAllSchedule();

		List<ScheduleDto> list = DBlist.stream().map(ps -> new ScheduleDto(//
				ps.getEmployeeId(), ps.getEmployeeName(), ps.getEmployeeTitle(), //
				ps.getEmployeeStatus(), ps.getApplyDate(), //
				ps.getShiftWorkId(), ps.isAccept(), ps.getStartTime(), ps.getEndTime())).toList();

		// 回傳
		return new GetScheduleRes(200, "おめでとうございます！！！", list);
	}

	@Override
	public List<GetThisDayScheduleDto> getThisDaySchedule(LocalDate thisDay) {
		// 資料庫拿出來的
		List<GetThisDayScheduleDto> DBlist = preScheduleMyBatisDao.getThisDaySchedule(thisDay);

		List<GetThisDayScheduleDto> list = DBlist.stream().map(gs -> new GetThisDayScheduleDto(//
				gs.getEmployeeId(), gs.getName(), gs.getTitle(), gs.getDepartment(), gs.getApplyDate(),
				gs.getShiftWorkId(), //
				gs.getStartTime(), gs.getEndTime())).toList();

		return list;
	}

	@Override
	public GetStaffCanWorkDayDto getStaffCanWorkDay(String employeeId) {
		// 資料庫拿出來的 可上班日期
		List<LocalDate> applyDateList = preScheduleMyBatisDao.getStaffCanWorkDay(employeeId);

		GetStaffCanWorkDayDto getStaffCanWorkDay = new GetStaffCanWorkDayDto();

		getStaffCanWorkDay.setEmployeeId(employeeId);
		getStaffCanWorkDay.setApplyDate(applyDateList);

		return getStaffCanWorkDay;
	}

	@Transactional(rollbackOn = Exception.class) // 讓執行不是全成功就是全失敗
	@Override
	public BasicRes addShiftWork(ShiftWorkDto shiftWorkDto) throws Exception {
		try {

			// 檢查是否有此班別
			int count = preScheduleMyBatisDao.countShiftWorkId(//
					shiftWorkDto.getShiftWorkId());

			if (count == 1) {
				return new BasicRes(400, "此班別 ID 已存在");
			}
			// 檢查日期
			if (shiftWorkDto.getStartTime().isAfter(shiftWorkDto.getEndTime())) {
				return new BasicRes(400, "結束日期不能小於開始日期");
			}

			int sw = preScheduleMyBatisDao.addShiftWork(shiftWorkDto);
		} catch (Exception e) {
			throw e;
		}

		return new BasicRes(200, "おめでとうございます！！！");
	}

	@Transactional(rollbackOn = Exception.class) // 讓執行不是全成功就是全失敗
	@Override
	public BasicRes updateShiftWork(ShiftWorkDto shiftWorkDto) throws Exception {
		try {

			// 檢查是否有此班別
			int count = preScheduleMyBatisDao.countShiftWorkId(//
					shiftWorkDto.getShiftWorkId());

			if (count != 1) {
				return new BasicRes(400, "無此班別ID");
			}

			// 檢查日期
			if (shiftWorkDto.getStartTime().isAfter(shiftWorkDto.getEndTime())) {
				return new BasicRes(400, "結束日期不能小於開始日期");
			}

			preScheduleMyBatisDao.updateShiftWork(shiftWorkDto);

		} catch (Exception e) {
			throw e;
		}

		return new BasicRes(200, "おめでとうございます！！！");
	}

	@Transactional(rollbackOn = Exception.class) // 讓執行不是全成功就是全失敗
	@Override
	public BasicRes deleteShiftWork(int shiftWorkId) throws Exception {
		try {
			// 檢查是否有此班別
			int count = preScheduleMyBatisDao.countShiftWorkId(shiftWorkId);

			if (count != 1) {
				return new BasicRes(400, "無此班別ID");
			}

			preScheduleMyBatisDao.deleteShiftWork(shiftWorkId);

		} catch (Exception e) {
			throw e;
		}
		return new BasicRes(200, "おめでとうございます！！！");
	}

	@Override
	public List<GetCanSupEmployeeDto> getCanSupEmployee(GetCanSupEmployeeReq req) {

		return null;
	}

	@Override
	public List<Schedule> schedule() {

		return preScheduleMyBatisDao.schedule();
	}

	@Override
	public PrettySchedule prettySchedule() {
		// 資料庫拿出來的一整包資料
		List<ScheduleDto> DBlist = preScheduleDao.getAllSchedule();

		// 分類整理
		// collect 收集
		// Collectors.groupingBy 依照指定條件分組
		// 回傳成 String, List<ScheduleDto>

		// ScheduleDto :: getEmployeeId
		// Lambda 表達式，代表呼叫 ScheduleDto 中的 getEmployeeId 這個方法
		// 等同於 dto -> dto.getEmployeeId() ，dto 由自己命名 代表 DBlist
		Map<String, List<ScheduleDto>> employeeData = DBlist.stream().//
				collect(Collectors.groupingBy(dto -> dto.getEmployeeId()));

		List<EmployeeList> employeeList = new ArrayList<>();

		// Map.Entry → 取得每一筆紀錄（有欄位 key 與 value）
		// entrySet() → 把整張表轉成多筆紀錄讓你可以逐筆讀取
		// 遍歷每個員工
		for (Map.Entry<String, List<ScheduleDto>> employee : employeeData.entrySet()) {
			List<ScheduleDto> empSchedule = employee.getValue();

			// 從這位員工的多筆班表中，取出一筆代表性資料 ，不然會有重複的資料
			ScheduleDto first = empSchedule.get(0);

			// 員工底下再依日期分組
			Map<LocalDate, List<ScheduleDto>> dateMap = empSchedule.stream()
					.collect(Collectors.groupingBy(dto -> dto.getApplyDate()));

			List<ApplyDate> dateList = new ArrayList<>();

			// 遍歷每個日期
			for (Map.Entry<LocalDate, List<ScheduleDto>> dateEntry : dateMap.entrySet()) {
				// 取得每個日期
				LocalDate applyDate = dateEntry.getKey();

				// 取得日期底下每個班別
				List<ScheduleDto> shiftList = dateEntry.getValue();
				List<ShiftDetail> shiftDetail = shiftList.stream().//
						map(list -> new ShiftDetail(list.getShiftWorkId(), //
								list.isAccept(), //
								list.getStartTime(), //
								list.getEndTime()))
						.collect(Collectors.toList());
				// 塞日期跟班別
				dateList.add(new ApplyDate(applyDate, shiftDetail));
			}
			// 塞回去最外面那層
			EmployeeList emp = new EmployeeList(first.getEmployeeId(), first.getEmployeeName(),
					first.getEmployeeTitle(), first.getEmployeeStatus(), dateList);
			employeeList.add(emp);
		}

		return new PrettySchedule(200, "success", employeeList);
	}

	@Override
	public PrettySchedule prettyScheduleNotLeave(LocalDate start, LocalDate end) {
		// 資料庫拿出來的一整包資料
		List<ScheduleDto> DBlist = preScheduleDao.getAllScheduleNotLeave(start, end);

		// 分類整理
		// collect 收集
		// Collectors.groupingBy 依照指定條件分組
		// 回傳成 String, List<ScheduleDto>

		// ScheduleDto :: getEmployeeId
		// Lambda 表達式，代表呼叫 ScheduleDto 中的 getEmployeeId 這個方法
		// 等同於 dto -> dto.getEmployeeId() ，dto 由自己命名 代表 DBlist
		Map<String, List<ScheduleDto>> employeeData = DBlist.stream().//
				collect(Collectors.groupingBy(dto -> dto.getEmployeeId()));

		List<EmployeeList> employeeList = new ArrayList<>();

		// Map.Entry → 取得每一筆紀錄（有欄位 key 與 value）
		// entrySet() → 把整張表轉成多筆紀錄讓你可以逐筆讀取
		// 遍歷每個員工
		for (Map.Entry<String, List<ScheduleDto>> employee : employeeData.entrySet()) {
			List<ScheduleDto> empSchedule = employee.getValue();

			// 從這位員工的多筆班表中，取出一筆代表性資料 ，不然會有重複的資料
			ScheduleDto first = empSchedule.get(0);

			// 員工底下再依日期分組
			Map<LocalDate, List<ScheduleDto>> dateMap = empSchedule.stream()
					.collect(Collectors.groupingBy(dto -> dto.getApplyDate()));

			List<ApplyDate> dateList = new ArrayList<>();

			// 遍歷每個日期
			for (Map.Entry<LocalDate, List<ScheduleDto>> dateEntry : dateMap.entrySet()) {
				// 取得每個日期
				LocalDate applyDate = dateEntry.getKey();

				// 取得日期底下每個班別
				List<ScheduleDto> shiftList = dateEntry.getValue();
				List<ShiftDetail> shiftDetail = shiftList.stream().//
						map(list -> new ShiftDetail(list.getShiftWorkId(), //
								list.isAccept(), //
								list.getStartTime(), //
								list.getEndTime()))
						.collect(Collectors.toList());
				// 塞日期跟班別
				dateList.add(new ApplyDate(applyDate, shiftDetail));
			}
			// 塞回去最外面那層
			EmployeeList emp = new EmployeeList(first.getEmployeeId(), first.getEmployeeName(),
					first.getEmployeeTitle(), first.getEmployeeStatus(), dateList);
			employeeList.add(emp);
		}

		return new PrettySchedule(200, "success", employeeList);
	}

	@Override
	public BasicRes addSchedule(PreScheduleUpdateReq preSchedule) throws Exception {

		List<PreSchduleUpdateVo> updateList = preSchedule.getPreSchduleUpdateVo();
		try {

			for (PreSchduleUpdateVo vo : updateList) {

				if (vo.getApplyDate().isBefore(LocalDate.now())) {
					return new BasicRes(400, "日期小於今天");
				}

				if (vo.getShiftWorkId() > 4 || vo.getShiftWorkId() < 0) {
					return new BasicRes(400, "無此班別");
				}

				int count = headDao.countEmployeeId(vo.getEmployeeId());
				if (count != 1) {
					return new BasicRes(400, "無此員工ID");
				}
				int count2 = preScheduleMyBatisDao.countPreScheDule(vo.getEmployeeId(), //
						vo.getApplyDate(), vo.getShiftWorkId());
				if (count2 > 0) {
					return new BasicRes(400, "此員工日期和班別重複");
				}

				PreSchedule req = new PreSchedule(vo.getEmployeeId(), //
						vo.getApplyDate(), vo.getShiftWorkId(), //
						vo.isAccept());

				preScheduleMyBatisDao.addSchdule(req);

			}

		} catch (Exception e) {
			throw e;
		}

		return new BasicRes(200, "success");

	}

	@Override
	public BasicRes deleteSchedule(DeleteScheduleDto req) throws Exception {

		DeleteScheduleDto dto = new DeleteScheduleDto(req.getEmployeeId(), //
				req.getApplyDate(), req.getShiftWorkId());

		try {

			preScheduleMyBatisDao.deleteSchedule(dto);

		} catch (Exception e) {

			throw e;

		}

		return new BasicRes(200, "success");
	}

	@Override
	public SelectScheduleByIdAndDateRes selectScheduleByIdAndDate(String employeeId, LocalDate date) {

		List<ScheduleDto> DBList = preScheduleDao.getAllScheduleNotLeaveByIdAndDate(employeeId, date);

		List<ShiftDetail> ShiftDetailList = new ArrayList<>();

		for (ScheduleDto i : DBList) {

			ShiftDetail shiftDetail = new ShiftDetail();

			shiftDetail.setShiftWorkId(i.getShiftWorkId());
			shiftDetail.setAccept(i.isAccept());
			shiftDetail.setStartTime(i.getStartTime());
			shiftDetail.setEndTime(i.getEndTime());

			ShiftDetailList.add(shiftDetail);

		}
		if (!DBList.isEmpty()) {

			return new SelectScheduleByIdAndDateRes(200, "success", ShiftDetailList);
		} else {
			return new SelectScheduleByIdAndDateRes(400, "查無資料");
		}

	}

}
