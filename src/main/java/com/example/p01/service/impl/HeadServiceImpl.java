package com.example.p01.service.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.p01.dao.HeadDao;
import com.example.p01.dao.SalaryDao;
import com.example.p01.service.ifs.EmailService;
import com.example.p01.service.ifs.HeadService;
import com.example.p01.service.ifs.PreScheduleService;
import com.example.p01.vo.headVo.BasicRes;
import com.example.p01.vo.headVo.EmployeeCreateReq;
import com.example.p01.vo.headVo.EmployeeUpdateReq;
import com.example.p01.vo.headVo.GetAllEmployeeRes;
import com.example.p01.vo.headVo.GetAllEmployeeRes2;
import com.example.p01.vo.headVo.SearchRes;
import com.example.p01.constants.ResCodeMessage;
import com.example.p01.entity.Employee;
import com.example.p01.entity.Salary;
import com.example.p01.vo.headVo.ChangePasswordReq;
import com.example.p01.vo.headVo.CheckJoinDateRes;
import com.example.p01.vo.headVo.LoginReq;

import jakarta.transaction.Transactional;

@Service
public class HeadServiceImpl implements HeadService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private HeadDao headDao;

	@Autowired
	private SalaryDao salaryDao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PreScheduleService preScheduleService;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	// 創建職員個人資料
	@Transactional(rollbackOn = Exception.class) // 讓執行不是全成功就是全失敗
	@Override
	public BasicRes employeeCreate(EmployeeCreateReq req) throws Exception {
		// 格式 Valid 驗證完了
		try {
			// 檢查 id
			Employee employee = headDao.getAll(req.getId());
			if (employee != null) {
				return new BasicRes(400, "員工 ID 已存在");
			}

			headDao.insert(req.getId(), req.getName(), req.getTitle(), //
					req.getEmploymentStatus(), req.getPhone(), req.getEmail(), //
					req.getDepartment(), encoder.encode(req.getPassword()), LocalDate.now());

			// 幫這位新員工新增排班
//			preScheduleService.preScheduleCreate(req.getId());

			return new BasicRes(200, "おめでとうございます！！！");
		} catch (Exception e) {
			throw e;
		}
	}

	// 透過 id 取得職員資訊
	@Override
	public SearchRes employeeSearch(String id) {

		// 參數為 null 或空白時，回傳 400 + 指定訊息
		if (id == null || id.isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id 不可為空");
		}
		// 查詢資料
		SearchRes emp = new SearchRes();
		emp = headDao.selectByIdWithoutPwd(id);

		// 沒有對應職員 id 時，回傳狀態碼 + 指定訊息
		if (emp == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "查無此職員");
		}
		// 回傳查到的內容
		return new SearchRes(emp.getId(), emp.getName(), emp.getTitle(), //
				emp.getEmploymentStatus(), emp.getPhone(), emp.getEmail(), emp.getDepartment());
	}

	@Transactional(rollbackOn = Exception.class) // 讓執行不是全成功就是全失敗
	@Override
	public BasicRes employeeUpdate(EmployeeUpdateReq req) throws Exception {
		try {
			// 檢查 id 是否存在

			SearchRes res = headDao.selectByIdWithoutPwd(req.getId());

			if (res == null) {
				return new BasicRes(400, "員工 id 不存在");
			}
			// 密碼加密
			String password = encoder.encode(req.getPassword());

			headDao.employeeUpdate(req.getId(), req.getName(), req.getTitle(), //
					req.getEmploymentStatus(), req.getPhone(), req.getEmail(), req.getDepartment(), password);

		} catch (Exception e) {
			throw e;
		}
		return new BasicRes(200, "更新成功");
	}

	// 取得所有職員資訊
	@Override
	public GetAllEmployeeRes employeesSearch() {

		List<SearchRes> list = headDao.selectAllWithoutPwd();
		return new GetAllEmployeeRes(200, "資料取得成功", list);
	}

	// 修改密碼
	@Override
	public BasicRes changePassword(ChangePasswordReq req) {
		Employee employee = headDao.getAll(req.getId());
		// 透過id 取得一筆資料，id 不存在時會得null
		if (employee == null) {
			return new BasicRes(ResCodeMessage.NOT_FOUND.getCode(), //
					ResCodeMessage.NOT_FOUND.getMessage());

		}
		// 如果還是預設密碼，就允許直接換掉
		if (encoder.matches("0000", employee.getPassword())) {
			employee.setPassword(encoder.encode(req.getNewPassword()));
			headDao.save(employee);
			return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());
		}
		return new BasicRes(ResCodeMessage.NOT_0000.getCode(), //
				ResCodeMessage.NOT_0000.getMessage());
	}

	// 修改後的登入
	@Override
	public BasicRes login(LoginReq req) {
		// 3. 先透過 id 從 DB 取得資料後再比對密碼
		// 3.1 無法直接使用 id 和 password 從 DB 取得資料，因為相同的 password 值經由
		// BCryptPasswordEncoder 加密後會得到不一樣的值
		Employee employee = headDao.getAll(req.getId());
		// 透過id 取得一筆資料，id 不存在時會得null
		if (employee == null) {
			return new BasicRes(ResCodeMessage.NOT_FOUND.getCode(), //
					ResCodeMessage.NOT_FOUND.getMessage());

		}
		// 判斷資料庫裡是否還是 0000
		if (encoder.matches("0000", employee.getPassword())) {
			return new BasicRes(ResCodeMessage.PIEASE_CHANGE_PASSWORD.getCode(), //
					ResCodeMessage.PIEASE_CHANGE_PASSWORD.getMessage());
		}
		// 比對密碼(前面加!的話代表 encoder.matches==false)
		if (!encoder.matches(req.getPassword(), employee.getPassword())) {
			return new BasicRes(ResCodeMessage.PASSWORD_MISMATCH.getCode(), //
					ResCodeMessage.PASSWORD_MISMATCH.getMessage());

		}
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());

	}

	// 先這樣設定，薪資怎麼算之後還要改
	// 每個月 1 號早上 9 點寄信
//	@Scheduled(fixedRate = 30000)
//	// @Scheduled(cron ="0 0 9 1 * ?" )
//	@Override
//	public BasicRes mail() {
//		try {
//			List<Salary> salaries = salaryDao.getSalary();
//			for (Salary salary : salaries) {
//				// 透過 employeeId 取得對應 Employee
//				Employee emp = headDao.getAll(salary.getEmployeeId());
//				if (emp != null) {
//					String content = String.format("您好 %s，您的薪資為：%s 元（底薪：%s 元，工時：%s 小時）", emp.getName(),
//							salary.getSalary(), salary.getBaseSalary(), salary.getWorkHours());
//					// emp.getEmail() 是收件者
//					emailService.sendMail(emp.getEmail(), "薪資通知", content);
//					System.out.println("已寄出薪資通知給 " + emp.getName() + " <" + emp.getEmail() + ">");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace(); // 把完整錯誤印出來
//
//		}
//		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
//				ResCodeMessage.SUCCESS.getMessage());
//
//	}

	@Override
	public List<Employee> getemployeeNotresign() {

		List<Employee> list = headDao.getemployeeNotresign();

		return list;
	}

	@Override
	public GetAllEmployeeRes2 employeeAllNotResign() {
		List<Employee> employeeList = headDao.getAllNotResign();
		return new GetAllEmployeeRes2(200, "資料取得成功", employeeList);
	}

	@Override
	public BasicRes mail() {
		// TODO Auto-generated method stub
		return null;
	}

	// 查這個月員工是不是這個月入職
	@Override
	public CheckJoinDateRes checkJoindayByEmployeeId(String employeeId) {

		Employee e = headDao.getEmployeeById(employeeId);

		LocalDate joinday = headDao.checkJoindayByEmployeeId(employeeId);

		YearMonth joindayYm = YearMonth.from(joinday);

		YearMonth ym = YearMonth.now();

		CheckJoinDateRes res = new CheckJoinDateRes();

		res.setEmployeeId(employeeId);
		res.setName(e.getName());
		res.setJoinDate(e.getJoinDate());

		if (joindayYm.equals(ym)) {

			res.setCanMakeSchedule(false);

		} else {

			res.setCanMakeSchedule(true);

		}
		res.setCode(200);
		res.setMessage("success");;
		return res;
	}

}
