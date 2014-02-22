package com.weiminw.web.exceptions;

import javax.ws.rs.core.Response.Status;

public enum ExceptionMessages {
	USER_NOT_FOUND(Status.NO_CONTENT.getStatusCode(),"用户不存在"), AUTHENTICATION_FAILD(Status.FORBIDDEN.getStatusCode(),"用户名密码不匹配");
	
	private int code;
	private String message;
	ExceptionMessages(int code,String message){
		this.code = code;
		this.message = message;
	}
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
