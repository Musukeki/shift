package com.example.p01.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@IdClass(ClockDateId.class)
@Table(name = "clock_date")
public class ClockDate {

    @Id
    @Column(name = "employee_id")
    private String employeeId;

    @Id
    @Column(name = "work_date")
    private LocalDate workDate;

    @Id
    @Column(name = "clock_on")
    private LocalTime clockOn;

    @Column(name = "clock_off")
    private LocalTime clockOff;

    @Column(name = "rest_start")
    private LocalTime restStart;

    @Column(name = "rest_end")
    private LocalTime restEnd;
    
    @Column(name = "total_hour")
    private double totalHour;

    @Column(name = "has_double")
    private boolean hasDouble;

    @Column(name = "score")
    private int score;

    @Transient
    @JsonProperty("shiftWorkId")
    private Integer clazz;

    public Integer getClazz() {
        return clazz;
    }

    public void setClazz(Integer clazz) {
        this.clazz = clazz;
    }

    public ClockDate() {
    }

    public ClockDate(String employeeId,
            LocalDate workDate, LocalTime clockOn, LocalTime clockOff, LocalTime restStart,
            LocalTime restEnd, double totalHour, boolean hasDouble, int score) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.clockOn = clockOn;
        this.clockOff = clockOff;
        this.restStart = restStart;
        this.restEnd = restEnd;
        this.totalHour = totalHour;
        this.hasDouble = hasDouble;
        this.score = score;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public LocalTime getClockOn() {
        return clockOn;
    }

    public void setClockOn(LocalTime clockOn) {
        this.clockOn = clockOn;
    }

    public LocalTime getClockOff() {
        return clockOff;
    }

    public void setClockOff(LocalTime clockOff) {
        this.clockOff = clockOff;
    }

    public LocalTime getRestStart() {
        return restStart;
    }

    public void setRestStart(LocalTime restStart) {
        this.restStart = restStart;
    }

    public LocalTime getRestEnd() {
        return restEnd;
    }

    public void setRestEnd(LocalTime restEnd) {
        this.restEnd = restEnd;
    }

    public double getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(double totalHour) {
        this.totalHour = totalHour;
    }

    public boolean isHasDouble() {
        return hasDouble;
    }

    public void setHasDouble(boolean hasDouble) {
        this.hasDouble = hasDouble;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
