package com.example.p01.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ShiftWorkId implements Serializable {

	private int id;

	private int shiftWorkId;

	public ShiftWorkId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShiftWorkId(int id, int shiftWorkId) {
		super();
		this.id = id;
		this.shiftWorkId = shiftWorkId;
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

}
