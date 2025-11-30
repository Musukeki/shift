package com.example.p01.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.p01.dto.ScheduleDto;
import com.example.p01.entity.PreSchedule;
import com.example.p01.entity.PreScheduleId;

import jakarta.transaction.Transactional;

@Repository
public interface PreScheduleDao extends JpaRepository<PreSchedule, PreScheduleId> {

	@Transactional
	@Modifying
	@Query(value = "insert into pre_schedule(employee_id,apply_date, "//
			+ " shift_work_id) values " //
			+ " (?1,?2,?3); ", nativeQuery = true)
	public void insert(String employeeId, LocalDate applyDate, //
			 int shiftWorkId);
	
	
	@Query(value = "select count(leave_date) "//
			+ " from leave_detail ld "//
			+ " join leave_application la on ld.leave_id = la.leave_id "//
			+ " where ld.leave_date = ?1 and la.employee_id = ?2", nativeQuery = true)
	public int countApplyDate(LocalDate applyDate,String employeeId);

	@Transactional
	@Modifying
	@Query(value = "update pre_schedule set "//
			+ " shift_work_id = ?3, "//
			+ " is_accept     = ?4 "//
			+ " where employee_id = ?1 and apply_date = ?2 ", nativeQuery = true)
	public void update(String employeeId, LocalDate applyDate, //
			 int shiftWorkId, boolean accept);

	@Query(value = "select apply_date from pre_schedule where employee_id = ?1", nativeQuery = true)
	public List<LocalDate> selectApplyDateById(String employeeId);
	
	
	@Query(value = "select * from pre_schedule where apply_date = ?2 and employee_id = ?1", nativeQuery = true)
	public PreSchedule selectByApplyDateAndEmployeeId(String employeeId,LocalDate applyDate);

	@Query(value = "select new com.example.p01.dto.ScheduleDto "//
			+ " ( "//
			+ " pre.employeeId, "//
			+ " em.name, em.title, em.employmentStatus, "//
			+ " pre.applyDate, pre.shiftWorkId, pre.isAccept, "//
			+ " sh.startTime, sh.endTime "//
			+ " ) "//
			+ " from PreSchedule as pre "//
			+ " join Employee as em "//
			+ " on pre.employeeId = em.id "//
			+ " join ShiftWork as sh "//
			+ " on pre.shiftWorkId = sh.shiftWorkId "//
			+ " where pre.employeeId = ?1", nativeQuery = false)
	public List<ScheduleDto> getScheduleByEmployeeId(String employeeId);

	@Query(value = "select new com.example.p01.dto.ScheduleDto "//
			+ " ( "//
			+ " pre.employeeId, "//
			+ " em.name, em.title, em.employmentStatus, "//
			+ " pre.applyDate, pre.shiftWorkId, pre.isAccept, "//
			+ " sh.startTime, sh.endTime "//
			+ " ) "//
			+ " from PreSchedule as pre "//
			+ " left join Employee as em "//
			+ " on pre.employeeId = em.id "//
			+ " left join ShiftWork as sh "//
			+ " on pre.shiftWorkId = sh.shiftWorkId "//
			+ " where pre.employeeId = ?1 and pre.isAccept is true", nativeQuery = false)
	public List<ScheduleDto> getAcceptScheduleByEmployeeId(String employeeId);

	@Query(value = "select new com.example.p01.dto.ScheduleDto "//
			+ " ( "//
			+ " pre.employeeId, "//
			+ " em.name, em.title, em.employmentStatus, "//
			+ " pre.applyDate, pre.shiftWorkId, pre.isAccept, "//
			+ " sh.startTime, sh.endTime "//
			+ " ) "//
			+ " from PreSchedule as pre "//
			+ " join Employee as em "//
			+ " on pre.employeeId = em.id "//
			+ " join ShiftWork as sh "//
			+ " on pre.shiftWorkId = sh.shiftWorkId"
			+ " order by pre.shiftWorkId asc "//
			, nativeQuery = false)
	public List<ScheduleDto> getAllSchedule();
	
	@Query(value = "select new com.example.p01.dto.ScheduleDto "//
			+ " ( "//
			+ " pre.employeeId, "//
			+ " em.name, em.title, em.employmentStatus, "//
			+ " pre.applyDate, pre.shiftWorkId, pre.isAccept, "//
			+ " sh.startTime, sh.endTime "//
			+ " ) "//
			+ " from PreSchedule as pre "//
			+ " join Employee as em "//
			+ " on pre.employeeId = em.id "//
			+ " join ShiftWork as sh "//
			+ " on pre.shiftWorkId = sh.shiftWorkId "//
			+ " where pre.shiftWorkId != 0 "
			+ " and pre.applyDate between ?1 and ?2 "
			+ " and pre.isAccept = true "//
			, nativeQuery = false)
	public List<ScheduleDto> getAllScheduleNotLeave(LocalDate start,LocalDate end);
	
	@Query(value = "select new com.example.p01.dto.ScheduleDto "//
			+ " ( "//
			+ " pre.employeeId, "//
			+ " em.name, em.title, em.employmentStatus, "//
			+ " pre.applyDate, pre.shiftWorkId, pre.isAccept, "//
			+ " sh.startTime, sh.endTime "//
			+ " ) "//
			+ " from PreSchedule as pre "//
			+ " join Employee as em "//
			+ " on pre.employeeId = em.id "//
			+ " join ShiftWork as sh "//
			+ " on pre.shiftWorkId = sh.shiftWorkId "//
			+ " where pre.shiftWorkId != 0 "
			+ " and pre.employeeId = ?1"
			+ " and pre.applyDate = ?2 "
			+ " and pre.isAccept = true "//
			, nativeQuery = false)
	public List<ScheduleDto> getAllScheduleNotLeaveByIdAndDate(String employeeId,LocalDate date);

}
