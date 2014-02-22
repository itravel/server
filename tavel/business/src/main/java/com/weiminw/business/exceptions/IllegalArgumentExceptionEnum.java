package com.weiminw.business.exceptions;

public enum IllegalArgumentExceptionEnum {
	ID_INVALID("ID±ØÐë´óÓÚ0");
	
	private String message;

	IllegalArgumentExceptionEnum(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
}
