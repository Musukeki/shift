package com.example.p01.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.p01.dao.LeaveApplicationDao;
import com.example.p01.dao.LeaveDetailDao;
import com.example.p01.dao.PreScheduleDao;
import com.example.p01.dao.PreScheduleMyBatisDao;
import com.example.p01.dto.GetAllLeaveDto;
import com.example.p01.dto.GetAllLeaveForSalaryDto;
import com.example.p01.dto.GetApplicationAndNameDto;
import com.example.p01.dto.LeaveApplicationDto;
import com.example.p01.dto.LeaveDetailDto;
import com.example.p01.dto.Schedule;
import com.example.p01.entity.PreSchedule;
import com.example.p01.service.ifs.LeaveService;
import com.example.p01.vo.headVo.BasicRes;
import com.example.p01.vo.staffVo.LeaveApplyByDateReq;
import com.example.p01.vo.staffVo.LeaveApprovedReq;
import com.example.p01.vo.staffVo.LeaveTimeRes;
import com.example.p01.vo.staffVo.leaveApplyReq;

import jakarta.transaction.Transactional;

@Service
public class leaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveApplicationDao leaveApplicationDao;

	@Autowired
	private LeaveDetailDao leaveDetailDao;

	@Autowired
	private PreScheduleDao preScheduleDao;

	@Autowired
	private PreScheduleMyBatisDao preScheduleMyBatisDao;

	@Override
	@Transactional(rollbackOn = Exception.class) // 讓執行不是全成功就是全失敗
	public BasicRes leaveApply(leaveApplyReq req) throws Exception {

		try {
			// 請假的申請req
			List<LeaveDetailDto> leaveDetailList = req.getLeaveDetails();

			// 圖片檔案
			String base64 = req.getLeaveProve();

			if (base64 != null && base64.contains(",")) {
				base64 = base64.substring(base64.indexOf(',') + 1);
			}

			// 主表
			LeaveApplicationDto leaveApplication = new LeaveApplicationDto(req.getEmployeeId(), req.getLeaveType(),
					req.getLeaveDescription(), base64, null);

			leaveApplicationDao.addLeaveApplication(leaveApplication);
			int leaveId = leaveApplicationDao.getLatestleaveId();

			// 取出已申請且未批准的假
			List<GetAllLeaveDto> leaveList = leaveApplicationDao.getAllLeaveByEmpolyeeId(req.getEmployeeId());

			for (LeaveDetailDto dto : leaveDetailList) {

				PreSchedule preschedule = preScheduleDao.selectByApplyDateAndEmployeeId(req.getEmployeeId(),
						dto.getLeaveDate());

				// 檢查當天有沒有上班
				if (preschedule == null || preschedule.getShiftWorkId() == 0 && preschedule.isAccept() == true//
				) {
					throw new Exception("當天沒有上班");
				}

				// 檢查請假時段有沒有重複
				for (GetAllLeaveDto leave : leaveList) {

					if (leave.getApplyDate().equals(dto.getLeaveDate()) && //
							leave.getStartTime().equals(dto.getStartTime()) && //
							leave.getEndTime().equals(dto.getEndTime())) {
						throw new Exception("申請時段重複");
					}
				}

				// 回填 leaveId
				dto.setLeaveId(leaveId);
			}

			// 一次批次 insert
			leaveDetailDao.addLeaveDetail(leaveDetailList);

		} catch (

		Exception e) {
			throw e;
		}

		return new BasicRes(200, "おめでとうございます！！！");
	}

	@Override
	public BasicRes leaveApplyByDate(LeaveApplyByDateReq req) throws Exception {
		try {

			String base64 = req.getLeaveProve();
			if (base64 != null) {
				base64 = base64.trim();
				if (!base64.isEmpty()) {
					int idx = base64.indexOf(',');
					if (idx >= 0) {
						base64 = base64.substring(idx + 1);
					}
				} else {
					base64 = null; // 沒上傳就存 null（依你的欄位需求）
				}
			}
			// 主表
			LeaveApplicationDto leaveApplication = new LeaveApplicationDto(req.getEmployeeId(), req.getLeaveType(),
					req.getLeaveDescription(), base64, null);

			leaveApplicationDao.addLeaveApplication(leaveApplication);
			int leaveId = leaveApplicationDao.getLatestleaveId();

			// 請假日期
			List<LocalDate> ApplyDateList = req.getLeaveDate();

			// 請假明細
			List<LeaveDetailDto> leaveDetailDtoList = new ArrayList<>();

			// 取出已申請且未批准的假
			List<GetAllLeaveDto> leaveList = leaveApplicationDao.getAllLeaveByEmpolyeeId(req.getEmployeeId());

			for (LocalDate date : ApplyDateList) {

				// 數當天日期有幾個班別
				int countShiftWork = preScheduleMyBatisDao.countShiftWorkByApplyDate(req.getEmployeeId(), date);

				PreSchedule preschedule = preScheduleDao.selectByApplyDateAndEmployeeId(req.getEmployeeId(), date);

				// 檢查當天有沒有上班
				if (preschedule == null || preschedule.getShiftWorkId() == 0 && preschedule.isAccept() == true//
				) {
					throw new Exception("當天沒有上班");
				}
				// 檢查請假時段有沒有重複
				for (GetAllLeaveDto leave : leaveList) {

					if (leave.getApplyDate().equals(date)) {
						throw new Exception("申請日期重複");
					}
				}

				// 用請假日期取得班表
				List<Schedule> scheduleList = preScheduleMyBatisDao.getLeaveDateSchedule(req.getEmployeeId(), date);

				for(Schedule schedule : scheduleList) {
					
				
				// 新增副表
				LeaveDetailDto leaveDetailDto = new LeaveDetailDto();
				leaveDetailDto.setLeaveId(leaveId);
				leaveDetailDto.setLeaveDate(date);
				leaveDetailDto.setStartTime(schedule.getStartTime());
				leaveDetailDto.setEndTime(schedule.getEndTime());
				leaveDetailDto.setLeaveHours(4);

				leaveDetailDtoList.add(leaveDetailDto);
				}
			}
			// 新增副表
			leaveDetailDao.addLeaveDetail(leaveDetailDtoList);

		} catch (Exception e) {
			throw e;
		}
		return new BasicRes(200, "おめでとうございます！！！");
	}

	@Override
	public BasicRes leaveApproved(LeaveApprovedReq req) {

		try {
			// 更新請假同意
			leaveApplicationDao.updateLeaveApproved(req.getLeaveId(), req.isApproved());

		} catch (Exception e) {
			return new BasicRes(400, "更新失敗");
		}

		return new BasicRes(200, "おめでとうございます！！！");
	}

	@Override
	public List<GetAllLeaveDto> getAllLeave(LocalDate startDate, LocalDate endDate, String employeeId, int leaveId) {

		List<GetAllLeaveDto> DBlist = leaveApplicationDao.getAllLeaveApplication(startDate, endDate, employeeId,
				leaveId);

		return DBlist;
	}

	@Override
	public List<GetApplicationAndNameDto> getApplication() {

		List<GetApplicationAndNameDto> DBlist = leaveApplicationDao.getApplication();

		for (GetApplicationAndNameDto list : DBlist) {
			String DBbase64 = list.getLeaveProve();
			String base64 = "data:image/png;base64," + DBbase64;

			list.setLeaveProve(base64);
		}

		return DBlist;
	}

	@Override
	public List<GetApplicationAndNameDto> getApprovedLeave(LocalDate startDate, LocalDate endDate) {
		List<GetApplicationAndNameDto> DBlist = leaveApplicationDao.getApprovedLeave(startDate, endDate);

		for (GetApplicationAndNameDto list : DBlist) {
			String DBbase64 = list.getLeaveProve();
			String base64 = "data:image/png;base64," + DBbase64;

			list.setLeaveProve(base64);
		}
		return DBlist;
	}

	@Override
	public List<GetAllLeaveForSalaryDto> getAllLeaveForSalary(LocalDate startDate, LocalDate endDate,
			String employeeId) {

		List<GetAllLeaveForSalaryDto> DBlist = leaveApplicationDao.getAllLeaveForSalary(startDate, endDate, employeeId);

		for (GetAllLeaveForSalaryDto list : DBlist) {

			String DBbase64 = list.getLeaveProve();
			String base64 = "data:image/png;base64," + DBbase64;

			list.setLeaveProve(base64);
		}

		return DBlist;
	}

	@Override
	public List<GetAllLeaveForSalaryDto> getLeaveByLeaveId(int leaveId) {
		
		List<GetAllLeaveForSalaryDto> DBlist = leaveApplicationDao.getLeaveByLeaveId(leaveId);

		for (GetAllLeaveForSalaryDto list : DBlist) {
			String DBbase64 = list.getLeaveProve();
			String base64 = "data:image/png;base64," + DBbase64;

			list.setLeaveProve(base64);
		}
		
		return DBlist;
	}

}
