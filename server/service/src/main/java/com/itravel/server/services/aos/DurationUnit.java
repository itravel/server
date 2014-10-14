package com.itravel.server.services.aos;

public enum DurationUnit {
	INVALID(0),YEAR(1),SESSON(2),MONTH(3),DAY(4),HOUR(5);
	
	private int value;

	DurationUnit(int value){
		this.value = value;
	}
	
	public static DurationUnit fromInt(int value){
		switch(value){
			case 1: return YEAR;
			case 2: return SESSON;
			case 3: return MONTH;
			case 4: return DAY;
			case 5: return HOUR;
			default:return INVALID;
		}
	}
	
	String stringValue(){
		switch(this.value){
			case 1: return "年";
			case 2: return "季";
			case 3: return "月";
			case 4: return "天";
			case 5: return "小时";
			default:return "无效";
		}
	}
	
}
