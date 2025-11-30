package com.example.p01.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.p01.entity.Employee;
import com.example.p01.vo.headVo.BasicRes;
import com.example.p01.vo.headVo.SearchRes;

import jakarta.transaction.Transactional;

@Repository
public interface HeadDao extends JpaRepository<Employee, String> {

	// 新增員工資訊
	@Transactional
	@Modifying
	@Query(value = "insert into employee(id,name,title,employment_status,phone,email,department, "
			+ " password,`join_date`) values(?1,?2,?3,?4,?5,?6,?7,?8,?9); ", nativeQuery = true)
	public void insert(String id, String name, String title, String employmentStatus, //
			String phone, String email, String department, String password, LocalDate joinDate);

	// 取全部資訊
	@Query(value = "select * from  employee  where id = ?1", nativeQuery = true)
	public Employee getAll(String id);

	// 取全部資訊
	@Query(value = "select * from  employee  where id = ?1", nativeQuery = true)
	public Employee getEmployeeById(String id);

	@Query(value = "select id, name, title, employment_status, "//
			+ " phone, email, department from employee where id = ?1", nativeQuery = true)
	public SearchRes selectByIdWithoutPwd(String id);

	@Query(value = "select id, name, title, employment_status, "//
			+ " phone, email, department from employee", nativeQuery = true)
	public List<SearchRes> selectAllWithoutPwd();

	@Transactional
	@Modifying
	@Query(value = "update employee set name = ?2, title = ?3, employment_status = ?4, phone = ?5, "//
			+ " email =?6, department = ?7, password = ?8 where id = ?1;", nativeQuery = true)
	public int employeeUpdate(String id, String name, String title, String employmentStatus, //
			String phone, String email, String department, String password);

	@Query(value = "SELECT * FROM employee; ", nativeQuery = true)
	public List<Employee> getAllEmployee();

	@Query(value = "SELECT * FROM employee where employment_status = '在職中'; ", nativeQuery = true)
	public List<Employee> getemployeeNotresign();

	@Query(value = "SELECT COUNT(*) FROM employee WHERE id = ?1; ", nativeQuery = true)
	public int countEmployeeId(String employeeId);

	@Query(value = "select * from employee where employment_status = '在職中' " + "order by case title "
			+ " WHEN '主管' THEN 1 " + " WHEN '正職員工' THEN 2 " + "end", nativeQuery = true)
	public List<Employee> getAllNotResign();

	// 查這個月員工是不是這個月入職
	@Query(value = "select join_date from employee where id = ?1 ", nativeQuery = true)
	public LocalDate checkJoindayByEmployeeId(String employeeId);

}
