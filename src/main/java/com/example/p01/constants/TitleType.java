package com.example.p01.constants;

public enum TitleType {

	MANAGER("manager"),//
	FULL_TIME("full_time"),//
	PART_TIME("part_time");
	
	
	
	private  String  title ;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private TitleType(String title) {
		this.title = title;
	}
	
	
	
	
	
}
