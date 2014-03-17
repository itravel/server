package com.itravel.server.services.utils;

public enum ImageCategory {
	ACTIVITIES("activities"),
	USER_AVATAR("user_avatar"),
	TRAVEL_NOTE("travel_note");
	
	private String category;

	ImageCategory(String category){
		this.category = category;
	}
}
