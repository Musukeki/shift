package com.example.p01.constants;

public enum ResCodeMessage {

	
	SUCCESS(200,"Success"),//
	PASSWORD_ERROR(400,"Password Error"),//
	EMAIL_EXISTS(400,"Email Exists"),//
	ADD_INFO_ERROR(400,"Add_Info Error"),//
	NOT_FOUND(404,"Not Found"),//
	PASSWORD_MISMATCH(400, "Password Mismatch!!"),//
	CREATE_ERROR(400,"Quiz Create Error"),//
	DATE_fORMAT_ERROR(400,"Date Format Error" ),//
	TEXT_HAS_OPTIONS_ERROR(400, "Text Has Options Error!!"),//
	UPDATE_FALED(400,"Quiz Update Faled"),//
	ID_ERROR(400,"Quiz Id Error"),//
	ANSWER_REQUIRED(400,"Answer Required"),//
	QUESTION_TYPE_IS_SINGLE(400,"Question Type Is Single"),//
	OPTION_ANSWER_MISMATCH(400, "Options Answer  Mismatch!!"),//
	EMAIL_DUPLICATED(400,"Email Duplicated"),//
	OBJECTMAPPER_PROCESSING_ERROR(400, "ObjectMapper Processing Error!!"),//
	PLEASE_LOGIN_FIRST(400,"Please Login First"),//
	PIEASE_CHANGE_PASSWORD(400,"Please Change Password"),//
	NOT_0000(400,"Not 0000"),//
	CANNOT_USE_DEFAULT(400,"Cannot Use Default");
	
	
    private int code;
	private String message;
	
	//enum沒有預設建構方法
	//帶有參數的建構方法一定要有
	private ResCodeMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	

	public String getMessage() {
		return message;
	}
	
}
