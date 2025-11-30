package com.example.p01.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(value = ShiftWorkId.class)
@Table(name = "shift_work")
public class ShiftWork {

	@Id
	@Column(name = "id")
	private int id;

	@Id
	@Column(name = "shift_work_id")
	private int shiftWorkId;

	@Column(name = "start_time")
	private LocalTime startTime;

	@Column(name = "end_time")
	private LocalTime endTime;

	public ShiftWork() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShiftWork(int id, int shiftWorkId, LocalTime startTime, LocalTime endTime) {
		super();
		this.id = id;
		this.shiftWorkId = shiftWorkId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShiftWorkId() {
		return shiftWorkId;
	}

	public void setShiftWorkId(int shiftWorkId) {
		this.shiftWorkId = shiftWorkId;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

}
