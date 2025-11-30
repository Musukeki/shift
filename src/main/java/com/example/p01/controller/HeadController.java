package com.example.p01.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.p01.entity.Employee;
import com.example.p01.service.ifs.HeadService;
import com.example.p01.vo.headVo.BasicRes;
import com.example.p01.vo.headVo.EmployeeCreateReq;
import com.example.p01.vo.headVo.EmployeeUpdateReq;
import com.example.p01.vo.headVo.GetAllEmployeeRes;
import com.example.p01.vo.headVo.GetAllEmployeeRes2;
import com.example.p01.vo.headVo.SearchRes;
import com.example.p01.vo.headVo.ChangePasswordReq;
import com.example.p01.vo.headVo.LoginReq;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HeadController {

	@Autowired
	private HeadService headService;

	// 創建職員個人資料
	@PostMapping(value = "/head/create")
	public BasicRes employeeCreate(@Valid @RequestBody EmployeeCreateReq req) throws Exception {
		return headService.employeeCreate(req);
	}

	// 透過 id 取得職員資訊
	@GetMapping(value = "/head/search")
	public SearchRes employeeSearch(@RequestParam("id") String id) {
		return headService.employeeSearch(id);
	}

	// 取得所有職員資訊
	@GetMapping(value = "/head/searchAll")
	public GetAllEmployeeRes employeesSearch() {
		return headService.employeesSearch();
	}

	// 更新職員個人資料
	@PostMapping(value = "/head/update")
	public BasicRes employeeUpdate(@Valid @RequestBody EmployeeUpdateReq req) throws Exception {
		return headService.employeeUpdate(req);
	}

	// 修改
	@PostMapping(value = "/head/changePassword")
	public BasicRes changePassword(@Valid @RequestBody ChangePasswordReq req) {
		return headService.changePassword(req);
	}

	// 修改後登入文振
	@PostMapping(value = "/head/login")
	public BasicRes login(@Valid @RequestBody LoginReq req) {
		return headService.login(req);
	}

	// 取得在職中的員工
	@GetMapping(value = "/head/getemployeeNotresign")
	public List<Employee> getemployeeNotresign() {
		return headService.getemployeeNotresign();
	}

	@GetMapping("/head/searchAllNotResign")
	public GetAllEmployeeRes2 employeeSearch() {
		return headService.employeeAllNotResign();
	}
	
	//查這個月員工是不是這個月入職
	@GetMapping("/head/checkJoindayByEmployeeId")
	public BasicRes checkJoindayByEmployeeId(@RequestParam("employeeId") String employeeId) {			
		return headService.checkJoindayByEmployeeId(employeeId);
	}

}
