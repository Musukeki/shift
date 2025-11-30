package com.example.p01.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "pre_schedule")
@IdClass(value = PreScheduleId.class)
public class PreSchedule {

	@Id
	@Column(name = "employee_id")
	@NotBlank(message = "employee_id 不能為null、空字串或全空白字串")
	private String employeeId;

	@Id
	@Column(name = "apply_date")
	private LocalDate applyDate;

	@Column(name = "shift_work_id")
	private int shiftWorkId;

	@Column(name = "is_accept")
	private boolean isAccept;

	public PreSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PreSchedule(@NotBlank(message = "employee_id 不能為null、空字串或全空白字串") String employeeId, LocalDate applyDate,
			int shiftWorkId, boolean isAccept) {
		super();
		this.employeeId = employeeId;
		this.applyDate = applyDate;
		this.shiftWorkId = shiftWorkId;
		this.isAccept = isAccept;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDate applyDate) {
		this.applyDate = applyDate;
	}

	public int getShiftWorkId() {
		return shiftWorkId;
	}

	public void setShiftWorkId(int shiftWorkId) {
		this.shiftWorkId = shiftWorkId;
	}

	public boolean isAccept() {
		return isAccept;
	}

	public void setAccept(boolean isAccept) {
		this.isAccept = isAccept;
	}

}
