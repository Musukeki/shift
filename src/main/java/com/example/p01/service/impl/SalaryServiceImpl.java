package com.example.p01.service.impl;

import com.example.p01.dao.*;
import com.example.p01.dto.ClockDate2;
import com.example.p01.dto.GetAllLeaveForSalaryDto;
import com.example.p01.entity.Employee;
import com.example.p01.entity.Salary;
import com.example.p01.service.ifs.ClockDateService;
import com.example.p01.service.ifs.SalaryService;
import com.example.p01.vo.SalaryVo.SalaryRes;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableScheduling
@Service
public class SalaryServiceImpl implements SalaryService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	LeaveApplicationDao leaveApplicationDao;

	@Autowired
	HeadDao headDao;

	@Autowired
	ClockDateService clockDateService;

	@Autowired
	SalaryDao salaryDao;

	@Scheduled(cron = "0 0 1 1 * ?") // 每個月1號
//    @Scheduled(cron = "0 * * * * ?") //每分鐘測試用
	@Transactional(rollbackOn = Exception.class)
	@Override
	public SalaryRes addSalary() throws Exception {
		// === 勞健保與稅率設定 ===
		final double LABOR_RATE = 0.11; // 勞保費率
		final double HEALTH_RATE = 0.0517; // 健保費率
		final double SUPPLEMENT_RATE = 0.0211; // 健保補充保費率

		try {
			// === 基本薪資表 ===
			Map<String, Integer> baseSalaryMap = new HashMap<>();
			baseSalaryMap.put("正職人員", 32000);
			baseSalaryMap.put("計時人員", 0);
			baseSalaryMap.put("主管", 50000);

			// === 查出所有在職員工 ===
			List<Employee> employeeList = headDao.getAllNotResign();

			// === 各項紀錄 Map ===
			Map<String, Integer> hourlyRateMap = new HashMap<>();
			Map<String, Integer> totalSalaryMap = new HashMap<>();
			Map<String, Integer> overtimeMap = new HashMap<>();
			Map<String, Integer> insuranceMap = new HashMap<>();
			Map<String, Integer> deductionMap = new HashMap<>();

			// === 設定期間 ===
			YearMonth yearMonth = YearMonth.now().minusMonths(1);
			LocalDate start = yearMonth.atDay(1);
			LocalDate end = yearMonth.atEndOfMonth();

			// === 逐一處理每位員工 ===
			for (Employee employee : employeeList) {
				String empId = employee.getId();
				String title = employee.getTitle();

				int baseSalary = baseSalaryMap.get(title);
				int hourlyRate = (baseSalary == 0) ? 190 : baseSalary / 30 / 8;
				int totalSalary = baseSalary;
				int overtimePay = 0;
				int lateDeduction = 0;
				int leaveDeduction = 0;

				// === 取得打卡紀錄 ===
				List<ClockDate2> clockDate2List = clockDateService.getSingleHistoryClock2(empId, start, end);
				for (ClockDate2 clockDate2 : clockDate2List) {

					// === 計算加班費 ===
					double overPay = 0.0;
					if (!clockDate2.getEmployeeId().equals(empId))
						continue;
					if (clockDate2.getClockOn() == null || clockDate2.getClockOff() == null)
						continue;
					if (title.equals("計時人員")) {
						if (!clockDate2.isHasDouble()) {
							totalSalary += hourlyRate * clockDate2.getTotalHour();
						} else {
							totalSalary += (hourlyRate * clockDate2.getTotalHour()) * 2;
						}
					}

					if (clockDate2.isHasDouble()) {
						overPay += hourlyRate * 2;
						if (clockDate2.getTotalHour() > 8) {
							double over = clockDate2.getTotalHour() - 8.0;

							double firstTwo = Math.min(over, 2.0);
							double rest = Math.max(0, over - 2.0);

							overPay = overPay + (((hourlyRate * 1.34) * firstTwo) + ((hourlyRate * 1.67) * rest)) * 2;

							overtimePay += (int) Math.round(overPay);
						}
					}

					if (!clockDate2.isHasDouble() && clockDate2.getTotalHour() > 8) {
						double over = clockDate2.getTotalHour() - 8.0;

						double firstTwo = Math.min(over, 2.0);
						double rest = Math.max(0, over - 2.0);

						overPay = overPay + ((hourlyRate * 1.34) * firstTwo) + ((hourlyRate * 1.67) * rest);

						overtimePay += (int) Math.round(overPay);
					}

//                    System.out.println("員工ID: "+clockDate2.getEmployeeId());
//                    System.out.println("工作日: "+clockDate2.getWorkDate());
//                    System.out.println("班別ID: "+clockDate2.getShiftWorkId());
//                    System.out.println("總時數: "+clockDate2.getTotalHour());
//                    System.out.println("實際上班: "+clockDate2.getClockOn());
//                    System.out.println("實際下班: "+clockDate2.getClockOff());
//                    System.out.println("預計上班: "+clockDate2.getStartTime());
//                    System.out.println("預計下班: "+clockDate2.getEndTime());
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

					// === 遲到扣薪 ===
					int latePay = 0;
					if (!empId.equals(clockDate2.getEmployeeId()))
						continue;
					if (clockDate2.getClockOn().isAfter(clockDate2.getStartTime())) {

						long min = Duration.between(clockDate2.getStartTime(), clockDate2.getClockOn()).toMinutes();
						if (min > 10 && min < 30) {
							latePay += hourlyRate * 0.5;
						} else if (min > 30 && min < 60) {
							latePay += hourlyRate;
						} else if (min > 60) {
							latePay += hourlyRate * 4;
						}
					}
					lateDeduction += latePay;
				}

				totalSalary += overtimePay;
				totalSalary -= lateDeduction;

				// === 請假扣薪 ===
				List<GetAllLeaveForSalaryDto> leaveList = leaveApplicationDao.getAllLeaveForSalary(start, end, empId);
				for (GetAllLeaveForSalaryDto leave : leaveList) {
					if (!empId.equals(leave.getEmployeeId()))
						continue;
					if (leave.getApproved() == null || leave.getApproved() == false)
						continue;

					switch (leave.getLeaveType()) {
					case "事假":
						leaveDeduction += hourlyRate * leave.getLeaveHours();
						break;
					case "病假":
						leaveDeduction += (int) Math.round(hourlyRate * leave.getLeaveHours() * 0.5); // 半薪
						break;
					}
				}
				totalSalary -= leaveDeduction;

				// === 勞健保計算 ===
				int laborInsurance = employee.getTitle().equals("計時人員") ? 604
						: (int) Math.round(baseSalary * LABOR_RATE * 0.2);
				int healthInsurance = employee.getTitle().equals("計時人員") ? 426
						: (int) Math.round(baseSalary * HEALTH_RATE * 0.3);
				int supplementInsurance = (int) Math.round(overtimePay * SUPPLEMENT_RATE);
				int totalInsurance = laborInsurance + healthInsurance + supplementInsurance;
				totalSalary -= totalInsurance;

				// === 結果紀錄 ===
				hourlyRateMap.put(empId, hourlyRate);
				overtimeMap.put(empId, overtimePay);
				insuranceMap.put(empId, totalInsurance);
				deductionMap.put(empId, lateDeduction + leaveDeduction);
				totalSalaryMap.put(empId, totalSalary);
			}

			// === 新增薪資記錄 ===
			for (Employee employee : employeeList) {
				String empId = employee.getId();
				salaryDao.addSalary(empId, yearMonth.toString(), baseSalaryMap.getOrDefault(employee.getTitle(), 0),
						hourlyRateMap.getOrDefault(empId, 0), overtimeMap.getOrDefault(empId, 0),
						deductionMap.getOrDefault(empId, 0), insuranceMap.getOrDefault(empId, 0),
						totalSalaryMap.getOrDefault(empId, 0));
			}

			return new SalaryRes(200, "新增成功");
		} catch (Exception e) {
			logger.error("新增薪資失敗：" + e.getMessage(), e);
			throw e;
		}
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public SalaryRes getSalaryById(String yearMonth) throws Exception {
		try {
			List<Salary> salaryList = salaryDao.getSalaryById(yearMonth);
			if (salaryList == null || salaryList.isEmpty()) {
				return new SalaryRes(404, "找不到薪資列表");
			}
			return new SalaryRes(200, "查詢成功", salaryList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
}