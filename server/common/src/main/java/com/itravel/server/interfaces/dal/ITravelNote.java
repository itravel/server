package com.itravel.server.interfaces.dal;

import java.util.Collection;

public interface ITravelNote {

	public abstract long getId();

	public abstract void setId(long id);

	public abstract int getCity();

	public abstract void setCity(int city);

	public abstract String getContent();

	public abstract void setContent(String content);

	public abstract String getDestination();

	public abstract void setDestination(String destination);

	public abstract Collection<String> getPictures();

	public abstract void setPictures(String... pictures);

	public abstract void addPicture(String picture);
	
	public abstract int getProvince();

	public abstract void setProvince(int province);

	public abstract String getTitle();

	public abstract void setTitle(String title);

	public abstract String getUserAvatar();

	public abstract void setUserAvatar(String userAvatar);

	public abstract long getUserId();

	public abstract void setUserId(long userId);

	public abstract String getUserName();

	public abstract void setUserName(String userName);

	public abstract void setLatitude(double latitude);

	public abstract void setLongitude(double longitude);

}