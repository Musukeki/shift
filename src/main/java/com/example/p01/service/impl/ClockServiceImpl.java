// ClockServiceImpl.java（重點流程）
package com.example.p01.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.p01.dao.ClockDateDao;
import com.example.p01.dao.ClockDateMyBatisDao;
import com.example.p01.dao.HolidayDao;
import com.example.p01.dto.ClockDate2;
import com.example.p01.dto.ClockDateDto;
import com.example.p01.dto.ClockOffDto;
import com.example.p01.dto.GetMissClockListDto;
import com.example.p01.dto.MissClockApplyDto;
import com.example.p01.dto.response.ApiRes;
import com.example.p01.entity.ClockDate;
import com.example.p01.service.ifs.ClockDateService;
import com.example.p01.vo.ClockDateVo.*;
import com.example.p01.vo.headVo.BasicRes;

import jakarta.transaction.Transactional;

@Service
public class ClockServiceImpl implements ClockDateService {

	@Autowired
	private ClockDateDao clockDateDao;

	@Autowired
	private HolidayDao holidayDao;

	@Autowired
	private ClockDateMyBatisDao clockDateMyBatisDao;

	@Override
	public BasicRes clockOff2(ClockOffDto dto) throws Exception {

		try {
			// 取該員工的最新一筆資料出來
			ClockDateDto lastData = clockDateMyBatisDao.selectClockDate(dto.getEmployeeId());

			// 判斷下班時間有沒有跨日
			// 如果有跨日 日期減一
			LocalDate checkDate;
			if (dto.getClockOff().isAfter(lastData.getClockOn())) {

				checkDate = LocalDate.now();

			} else {

				checkDate = LocalDate.now().minusDays(1);

			}

			// 取最新的一筆資料出來 檢查上班打卡日期有沒有符合當下時間
//			if(lastData.getWorkDate()!=checkDate) {
//				return new BasicRes(400, "資料庫找不到符合的資料，請先確認是否有忘記打卡");
//			}

			ClockOffDto anw = new ClockOffDto(dto.getClockOff(), //
					dto.getScore(), dto.getEmployeeId());

			// 打下班卡紀錄時間 只更新最新的一筆資料
			clockDateMyBatisDao.clockOff(anw);

			// 更新後重新查一次最新資料
			lastData = clockDateMyBatisDao.selectClockDate(dto.getEmployeeId());

			double hours;
			// LocalTime.MAX = 23:59:59.999999999
			// LocalTime.MIDNIGHT = 00:00
			// 跨日和沒跨日的狀況
			if (lastData.getClockOff().isBefore(lastData.getClockOn())) {
				hours = Duration.between(lastData.getClockOn(), LocalTime.MAX).toMinutes()
						+ Duration.between(LocalTime.MIDNIGHT, lastData.getClockOff()).toMinutes();
			} else {
				hours = Duration.between(lastData.getClockOn(), lastData.getClockOff()).toMinutes();
			}

			// 計算暫離的時間
			double leaveTime;
			if (lastData.getRestStart() != null && lastData.getRestEnd() != null) {

				leaveTime = Duration.between(lastData.getRestStart(), //
						lastData.getRestEnd()).toMinutes();

				hours = hours - leaveTime;
			}

			// 向下取整數 例: 2.7->2 -2.7->-3
			hours = Math.floor(hours / 60.0 * 2) / 2.0;

			anw.setTotalHour(hours);
			// 計算完工時，返回資料庫
			clockDateMyBatisDao.clockOff(anw);

		} catch (Exception e) {
			throw e;
		}

		return new BasicRes(200, "success");
	}

	// ================================================================================

	// 查詢個別員工當日打卡紀錄
	@Override
	public ApiRes<List<ClockDate>> getSingleClock(String employeeId, LocalDate workDate) {

		List<ClockDate> result = clockDateDao.getSingleClock(employeeId, workDate);

		if (result.isEmpty()) {
			return new ApiRes<>(400, "查無打卡資料", null);
		}

		for (ClockDate cd : result) {

			Integer cls = clockDateDao.findShiftClass(cd.getEmployeeId(), cd.getWorkDate(), cd.getClockOn());
			cd.setClazz(cls);
		}

		return new ApiRes<>(200, "success", result);
	}

	// 查詢所有員工歷史打卡紀錄
	@Override
	public ApiRes<List<ClockDate>> getAllClock() {

		List<ClockDate> result = clockDateDao.getAllClock();

		for (ClockDate cd : result) {

			Integer cls = clockDateDao.findShiftClass(cd.getEmployeeId(), cd.getWorkDate(), cd.getClockOn());
			cd.setClazz(cls);
		}

		return new ApiRes<>(200, "success", result);
	}

	// 查詢個別員工歷史打卡紀錄
	@Override
	public ApiRes<List<ClockDate>> getSingleHistoryClock(String employeeId, LocalDate startDate, LocalDate endDate) {

		List<ClockDate> result = clockDateDao.getSingleHistoryClock(employeeId, startDate, endDate);

		if (result.isEmpty()) {
			return new ApiRes<>(400, "查無打卡資料", null);
		}

		for (ClockDate cd : result) {

			Integer cls = clockDateDao.findShiftClass(cd.getEmployeeId(), cd.getWorkDate(), cd.getClockOn());
			cd.setClazz(cls);
		}

		return new ApiRes<>(200, "success", result);
	}

	// 上班打卡
	@Transactional
	@Override
	public ApiRes<ClockDate> addClockOn(AddClockOnReq req) {

		boolean hasPre = clockDateDao.checkAcceptPre(req.getEmployeeId(), req.getWorkDate(), req.getClockOn()) == 1;

		if (!hasPre) {
			return new ApiRes<>(400, "該時段未排班", null);
		}

		// boolean hasExistsOn = dao.checkExistsOn(req.getEmployeeId(),
		// req.getWorkDate(), req.getClockOn()) == 1;

		boolean isContinuous = clockDateDao.hasContinuousShift(req.getEmployeeId(), req.getWorkDate()) == 1;
		if (isContinuous) {
			boolean hasAnyOnToday = clockDateDao.hasClockOn(req.getEmployeeId(), req.getWorkDate()) == 1;
			if (hasAnyOnToday) {
				return new ApiRes<>(400, "該時段已有打卡紀錄", null);
			}
		} else {
			// 非連續班同一班別不可重複
			boolean hasExistsOn = clockDateDao.checkExistsOn2(req.getEmployeeId(), req.getWorkDate(),
					req.getClockOn()) == 1;
			if (hasExistsOn) {
				return new ApiRes<>(400, "該時段已有打卡紀錄", null);
			}
		}

		int changed = clockDateDao.addClockOnWhenNotContinuous(req.getEmployeeId(), req.getWorkDate(),
				req.getClockOn());

		if (changed == 1) {

			boolean continuous = clockDateDao.hasContinuousShift(req.getEmployeeId(), req.getWorkDate()) == 1;

			if (continuous) {
				return new ApiRes<>(200, "打卡成功", null);
			} else {
				return new ApiRes<>(200, "打卡成功", null);
			}

		} else {

			return new ApiRes<>(500, "未知錯誤", null);
		}

	}

	// 下班打卡
	@Transactional
	@Override
	public ApiRes<ClockDate> addClockOff(AddClockOffReq req) {

		return null;
	}

	// 休息開始
	@Transactional
	@Override
	public ApiRes<ClockDate> addRestStart(AddRestStartReq req) {

		boolean continuous = clockDateDao.hasContinuousShift(req.getEmployeeId(), req.getWorkDate()) == 1;

		if (!continuous) {
			return new ApiRes<>(200, "非連續時段班無法休息", null);
		}

		boolean checkRangeOfRest = clockDateDao.canStartRest(req.getEmployeeId(), req.getWorkDate(),
				req.getRestStart()) == 1;

		if (!checkRangeOfRest) {
			return new ApiRes<>(400, "未在上班時段之間打卡", null);
		}

		boolean hasRestStart = clockDateDao.existsRestStart(req.getEmployeeId(), req.getWorkDate(),
				req.getRestStart()) == 1;

		if (hasRestStart) {
			return new ApiRes<>(400, "已有休息開始紀錄", null);
		}

		boolean row = clockDateDao.addRestStart(req.getEmployeeId(), req.getWorkDate(), req.getRestStart()) == 1;

		if (!row) {
			return new ApiRes<>(400, "找不到對應的上班打卡紀錄", null);
		}

		return new ApiRes<>(200, "休息開始", null);
	}

	// 休息結束
	@Transactional
	@Override
	public ApiRes<ClockDate> addRestEnd(AddRestEndReq req) {

		boolean continuous = clockDateDao.hasContinuousShift(req.getEmployeeId(), req.getWorkDate()) == 1;

		if (!continuous) {
			return new ApiRes<>(200, "非連續時段班無法休息", null);
		}

		boolean checkHasOn = clockDateDao.hasClockOn(req.getEmployeeId(), req.getWorkDate()) == 1;

		if (!checkHasOn) {
			return new ApiRes<>(400, "找不到對應的上班打卡紀錄", null);
		}

		boolean checkRestStart = clockDateDao.hasRestStart(req.getEmployeeId(), req.getWorkDate()) == 1;

		if (!checkRestStart) {
			return new ApiRes<>(400, "尚未打休息開始，請補打卡", null);
		}

		boolean hasRestEnd = clockDateDao.existsRestEnd(req.getEmployeeId(), req.getWorkDate(), req.getRestEnd()) == 1;

		if (hasRestEnd) {
			return new ApiRes<>(400, "已有休息結束紀錄", null);
		}

		boolean checkRangeOfRest = clockDateDao.canEndRest(req.getEmployeeId(), req.getWorkDate(),
				req.getRestEnd()) == 1;

		if (!checkRangeOfRest) {
			return new ApiRes<>(400, "未在上班時段之間打卡", null);
		}

		boolean row = clockDateDao.addRestEnd(req.getEmployeeId(), req.getWorkDate(), req.getRestEnd()) == 1;

		if (row) {
			return new ApiRes<>(200, "休息結束", null);
		}

		return new ApiRes<>(500, "未知錯誤", null);
	}

	// 補打卡(新增)
	@Transactional
	@Override
	public ApiRes<ClockDate> reClockAll(ReClockReq req) {

		// 判斷打卡資訊是否完整
		if ((req.getClockOn() == null && req.getClockOff() != null)
				|| (req.getClockOn() != null && req.getClockOff() == null)
				|| (req.getClockOn() == null && req.getClockOff() == null)) {
			return new ApiRes<>(400, "請輸入完整打卡資訊", null);
		}

		// 檢查是否有排班
		boolean hasPre = clockDateDao.checkAcceptPre(req.getEmployeeId(), req.getWorkDate(), req.getClockOn()) == 1;
		if (!hasPre) {
			return new ApiRes<>(400, "該時段未排班", null);
		}

		// 判斷是否有休息
		boolean hasRest = (req.getRestStart() != null && req.getRestEnd() != null);

		// 判斷非連續禁止休息
		boolean continuous = clockDateDao.hasContinuousShift(req.getEmployeeId(), req.getWorkDate()) == 1;
		if (!continuous && hasRest) {
			return new ApiRes<>(200, "非連續時段班無法休息", null);
		}

		// 判斷休息資訊是否完整
		if ((req.getRestStart() == null && req.getRestEnd() != null)
				|| (req.getRestStart() != null && req.getRestEnd() == null)) {
			return new ApiRes<>(400, "請輸入完整休息資訊", null);
		}
		// 只有有給休息才檢查休息是否在上班時段
		if (hasRest) {
			boolean checkRangeOfRest = clockDateDao.canStartRest(req.getEmployeeId(), req.getWorkDate(),
					req.getRestStart()) == 1;
			if (!checkRangeOfRest) {
				return new ApiRes<>(400, "休息必須在上班時段之間", null);
			}

			if (req.getRestEnd().isBefore(req.getRestStart())) {
				return new ApiRes<>(400, "休息結束不能早於休息開始", null);
			}
		}

		int restTime = 0;
		if (req.getRestStart() != null && req.getRestEnd() != null) {
			restTime = (req.getRestEnd().toSecondOfDay() - req.getRestStart().toSecondOfDay() + 86400) % 86400;
		}

		Double totalHour = Math.max(0,
				(req.getClockOff().toSecondOfDay() - req.getClockOn().toSecondOfDay() + 86400) % 86400 - restTime)
				/ 3600.0;

		totalHour = Math.floor(totalHour * 2.0 + 1e-9) / 2.0;

		int changed = clockDateDao.reClockAll(req.getEmployeeId(), req.getWorkDate(), req.getClockOn(),
				req.getClockOff(), req.getRestStart(), req.getRestEnd(), totalHour, req.getScore());

		if (changed >= 1) {
			return new ApiRes<>(200, "補打卡成功", null);
		}
		return new ApiRes<>(500, "未知錯誤", null);
	}

	// 補打卡(更新)
	@Transactional
	@Override
	public ApiRes<ClockDate> reClockPart(ReClockReq req) {

		// 輸入正確資訊
		if ((req.getEmployeeId() == null && req.getWorkDate() != null)
				|| (req.getEmployeeId() != null && req.getWorkDate() == null)
				|| (req.getEmployeeId() == null && req.getWorkDate() == null)) {
			return new ApiRes<>(400, "請輸入正確員工編號及補卡日期", null);
		}

		if (req.getClockOn() == null || req.getClockOff() == null) {
			return new ApiRes<>(400, "請輸入完整打卡資訊", null);
		}

		// 檢查是否有上班打卡
		int checkExists = clockDateDao.checkReClockExists(req.getEmployeeId(), req.getWorkDate(), req.getClockOn());
		if (checkExists == 0) {
			return new ApiRes<>(200, "查無該筆上班打卡紀錄，請確認上班打卡時間是否無誤，或新增(補)完整打卡資訊", null);
		}

		// 判斷是否有休息
		boolean hasRest = (req.getRestStart() != null && req.getRestEnd() != null);

		// 判斷非連續禁止休息
		boolean continuous = clockDateDao.hasContinuousShift(req.getEmployeeId(), req.getWorkDate()) == 1;
		if (!continuous && hasRest) {
			return new ApiRes<>(200, "非連續時段班無法休息", null);
		}

		if ((req.getRestStart() == null && req.getRestEnd() != null)
				|| (req.getRestStart() != null && req.getRestEnd() == null)) {
			return new ApiRes<>(400, "請輸入完整休息資訊", null);
		}

		if (hasRest && req.getRestStart().isAfter(req.getRestEnd())) {
			return new ApiRes<>(400, "休息結束不可早於開始", null);
		}

		// 計算時數
		final int DAY = 86400;

		int restSec = 0;
		if (hasRest) {
			restSec = (req.getRestEnd().toSecondOfDay() - req.getRestStart().toSecondOfDay() + DAY) % DAY;
		}

		int workSec = (req.getClockOff().toSecondOfDay() - req.getClockOn().toSecondOfDay() + DAY) % DAY;

		double totalHour = Math.max(0, workSec - restSec) / 3600.0;
		totalHour = Math.floor(totalHour * 2.0 + 1e-9) / 2.0;

		if (req.getScore() == null) {
			return new ApiRes<>(400, "score 不可為空", null);
		}

		boolean changed = clockDateDao.reClockPart(req.getEmployeeId(), req.getWorkDate(), req.getClockOn(),
				req.getClockOff(), req.getRestStart(), req.getRestEnd(), totalHour, req.getScore()) == 1;

		if (changed) {
			return new ApiRes<>(200, "補打卡成功", null);
		}
		return new ApiRes<>(500, "未知錯誤", null);
	}

	@Override
	public List<ClockDate2> getSingleHistoryClock2(String employeeId, LocalDate startDate, LocalDate endDate) {

		List<ClockDate2> clockDate2 = clockDateMyBatisDao.getSingleHistoryClock2(employeeId, startDate, endDate);

		// 以 employeeId + workDate 分組（避免不同日期的資料被誤合併）
		Map<String, List<ClockDate2>> employeeClockDateData = clockDate2.stream()
				.collect(Collectors.groupingBy(dto -> dto.getEmployeeId() + "_" + dto.getWorkDate()));

		List<ClockDate2> resultList = new ArrayList<>();

		for (Map.Entry<String, List<ClockDate2>> entry : employeeClockDateData.entrySet()) {
			List<ClockDate2> clockDate2List = entry.getValue();

			// 根據 shiftWorkId 排序
			clockDate2List.sort(Comparator.comparingInt(ClockDate2::getShiftWorkId));

			// 只有一筆 → 直接回傳那筆
			if (clockDate2List.size() == 1) {
				resultList.add(clockDate2List.getFirst());
				continue;
			}

			ClockDate2 first = clockDate2List.get(0);
			ClockDate2 last = clockDate2List.get(clockDate2List.size() - 1);

			boolean isContinuous = true;
			// 檢查 shiftWorkId 是不是連續
			for (int i = 1; i < clockDate2List.size(); i++) {
				if (clockDate2List.get(i).getShiftWorkId() - clockDate2List.get(i - 1).getShiftWorkId() != 1) {
					isContinuous = false;
					break;
				}
			}

			if (isContinuous) {
				// shiftWorkId 連續 → 合併成一筆
				ClockDate2 merged = new ClockDate2();
				merged.setEmployeeId(first.getEmployeeId());
				merged.setWorkDate(first.getWorkDate());
				merged.setClockOn(first.getClockOn());
				merged.setClockOff(first.getClockOff());
				merged.setRestStart(first.getRestStart());
				merged.setRestEnd(first.getRestEnd());
				merged.setTotalHour(first.getTotalHour());
				merged.setHasDouble(first.isHasDouble());
				merged.setShiftWorkId(first.getShiftWorkId());
				merged.setStartTime(first.getStartTime());
				merged.setEndTime(last.getEndTime());
				resultList.add(merged);
			} else {
				// shiftWorkId 不連續 → 只取第一筆與第三筆
				resultList.add(first);
				if (clockDate2List.size() >= 2) {
					resultList.add(clockDate2List.get(3));
				}
			}
		}

		return resultList;
	}

	// 補打卡申請
	@Override
	public BasicRes missClockApply(List<MissClockApplyDto> req) throws Exception {

		try {

			for (MissClockApplyDto dto : req) {
				// 計算小時
				double hours;
				// 處理證明圖片
				// 圖片檔案
				String base64 = dto.getProve();

				if (base64 != null && base64.contains(",")) {
					base64 = base64.substring(base64.indexOf(',') + 1);
				}

				// 計算時間

				// LocalTime.MAX = 23:59:59.999999999
				// LocalTime.MIDNIGHT = 00:00
				// 跨日和沒跨日的狀況
				if (dto.getClockOff().isBefore(dto.getClockOn())) {
					hours = Duration.between(dto.getClockOn(), LocalTime.MAX).toMinutes()
							+ Duration.between(LocalTime.MIDNIGHT, dto.getClockOff()).toMinutes();
				} else {
					hours = Duration.between(dto.getClockOn(), dto.getClockOff()).toMinutes();
				}
				
				

				// 檢查需不需要修息
				if (Math.floor(hours / 60.0 * 2) / 2.0 > 4 && dto.getRestStart() == null && dto.getRestEnd() == null) {
					return new BasicRes(400, "超過時數需要休息時間");
				}
				

				// 計算暫離的時間
				double leaveTime;
				if (dto.getRestStart() != null && dto.getRestEnd() != null) {

					leaveTime = Duration.between(dto.getRestStart(), //
							dto.getRestEnd()).toMinutes();

					hours = hours - leaveTime;
				}
				// 向下取整數 例: 2.7->2 -2.7->-3
				hours = Math.floor(hours / 60.0 * 2) / 2.0;

				int count = holidayDao.selectDate(dto.getWorkDate());
				if (count == 1) {
					dto.setHasDouble(true);
				} else {
					dto.setHasDouble(false);
				}
				dto.setProve(base64);
				dto.setTotalHour(hours);

			}

			clockDateMyBatisDao.missClockApply(req);

		} catch (Exception e) {
			throw e;
		}

		return new BasicRes(200, "success");
	}

	@Override
	public BasicRes missClockApprove(int id, boolean accept) {

		clockDateMyBatisDao.missClockApprove(id, accept);

		// 如果同意新增進打卡紀錄
		// 取資料補打卡資料
		MissClockApplyDto MissClockData = clockDateMyBatisDao.getMissClockById(id);

		if (accept == true) {
			clockDateDao.reClockAll(MissClockData.getEmployeeId(), MissClockData.getWorkDate(),
					MissClockData.getClockOn(), MissClockData.getClockOff(), MissClockData.getRestStart(),
					MissClockData.getRestEnd(), MissClockData.getTotalHour(), MissClockData.getScore());
		}

		return new BasicRes(200, "success");
	}

	// 補打卡核准列表
	@Override
	public List<GetMissClockListDto> getMissClockList() {

		List<GetMissClockListDto> DB = clockDateMyBatisDao.getMissClockList();

		return DB;
	}

	 // 取單筆補打卡申請資料
	 @Override
	 public MissClockApplyDto getMissClock(int id) {

	  MissClockApplyDto DB =  clockDateMyBatisDao.getMissClockById(id);
	  
	  String DBbase64 = DB.getProve();
	  
	  DBbase64 = "data:image/png;base64," + DBbase64;
	  
	  DB.setProve(DBbase64);
	  
	  return DB;
	 }

}
