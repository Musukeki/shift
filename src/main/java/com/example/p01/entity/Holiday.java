package com.example.p01.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "holiday")
public class Holiday {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "holiday_date")
	private LocalDate holidayDate;

	@Column(name = "name")
	private String name;

	@Column(name = "has_double")
	private boolean hasDouble;

	@Column(name = "has_workday")
	private boolean hasWorkday;

	public Holiday() {
		super();
	}

	public Holiday(int id, LocalDate holidayDate, String name, boolean hasDouble, boolean hasWorkday) {
		super();
		this.id = id;
		this.holidayDate = holidayDate;
		this.name = name;
		this.hasDouble = hasDouble;
		this.hasWorkday = hasWorkday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(LocalDate holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHasDouble() {
		return hasDouble;
	}

	public void setHasDouble(boolean hasDouble) {
		this.hasDouble = hasDouble;
	}

	public boolean isHasWorkday() {
		return hasWorkday;
	}

	public void setHasWorkday(boolean hasWorkday) {
		this.hasWorkday = hasWorkday;
	}
}
