package com.itravel.server.services.aos;

public enum AreaType {
	country(1),province(2),city(3);
	
	private int value;
	AreaType(int value){
		this.value = value;
	}
	
	public int intValue(){
		return this.value;
	}
	
}
