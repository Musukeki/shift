package com.example.p01.service.ifs;

import java.util.List;

import com.example.p01.entity.Employee;
import com.example.p01.entity.Salary;
import com.example.p01.vo.headVo.BasicRes;
import com.example.p01.vo.headVo.EmployeeCreateReq;
import com.example.p01.vo.headVo.EmployeeUpdateReq;
import com.example.p01.vo.headVo.GetAllEmployeeRes;
import com.example.p01.vo.headVo.GetAllEmployeeRes2;
import com.example.p01.vo.headVo.SearchRes;
import com.example.p01.vo.headVo.ChangePasswordReq;
import com.example.p01.vo.headVo.CheckJoinDateRes;
import com.example.p01.vo.headVo.LoginReq;

public interface HeadService {
	
	public BasicRes employeeCreate(EmployeeCreateReq req) throws Exception ;

	public SearchRes employeeSearch(String id);
	
	public GetAllEmployeeRes employeesSearch();
	
	public BasicRes employeeUpdate(EmployeeUpdateReq req) throws Exception;
	
	public BasicRes changePassword(ChangePasswordReq req);
	
	public BasicRes login(LoginReq req);
	
	public BasicRes mail();
	
	public List<Employee> getemployeeNotresign();
	
	public GetAllEmployeeRes2 employeeAllNotResign();
	
	//查這個月員工是不是這個月入職
	public CheckJoinDateRes checkJoindayByEmployeeId(String employeeId);

}
