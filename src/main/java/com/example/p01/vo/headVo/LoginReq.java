package com.example.p01.vo.headVo;

import com.example.p01.constants.constantsMessage;

import jakarta.validation.constraints.NotBlank;

public class LoginReq {

	
	@NotBlank(message=constantsMessage.ID_ERROR)
	private String  id ;
	
	@NotBlank(message=constantsMessage.PASSWORD_ERROR)
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
	
}
